# oop-fundamentals-
repo about tp in object oriented programming classes 
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int moveX[] = {2, 1, -1, -2, -2, -1, 1, 2};
int moveY[] = {1, 2, 2, 1, -1, -2, -2, -1};

typedef struct { int x, y; } Square;
typedef struct { Square squares[8]; int count; } ListSquares;
typedef struct { Square* data; int top; int capacity; } StackVisited;
typedef struct { ListSquares* data; int top; int capacity; } StackLists;

int getMaxMoves() { return sizeof(moveX) / sizeof(moveX[0]); }
bool isInside(int x, int y, int N) { return x >= 0 && x < N && y >= 0 && y < N; }

bool isVisited(StackVisited *s, int x, int y) {
    for (int i = 0; i <= s->top; i++)
        if (s->data[i].x == x && s->data[i].y == y) return true;
    return false;
}

ListSquares getAvailableSquares(StackVisited *visitedStack, Square from, int N) {
    ListSquares list; list.count = 0;
    for (int i = 0; i < getMaxMoves(); i++) {
        int nx = from.x + moveX[i];
        int ny = from.y + moveY[i];
        if (isInside(nx, ny, N) && !isVisited(visitedStack, nx, ny)) {
            list.squares[list.count].x = nx;
            list.squares[list.count].y = ny;
            list.count++;
        }
    }
    return list;
}

void pushVisited(StackVisited *s, Square sq) { s->data[++s->top] = sq; }
Square popVisited(StackVisited *s) { return s->data[s->top--]; }
void pushList(StackLists *s, ListSquares list) { s->data[++s->top] = list; }
ListSquares popList(StackLists *s) { return s->data[s->top--]; }

void printBoard(StackVisited *visited, int N) {
    int **board = (int**) malloc(N * sizeof(int*));
    for (int i = 0; i < N; i++) {
        board[i] = (int*) malloc(N * sizeof(int));
        for (int j = 0; j < N; j++) board[i][j] = 0;
    }
    for (int k = 0; k <= visited->top; k++)
        board[visited->data[k].x][visited->data[k].y] = k + 1;

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) printf("%3d ", board[i][j]);
        printf("\n");
    }
    for (int i = 0; i < N; i++) free(board[i]);
    free(board);
}

bool solveKnightTour(Square start, int N) {
    StackVisited visited;
    StackLists lists;
    visited.capacity = N * N;
    lists.capacity = N * N;
    visited.data = (Square*) malloc(visited.capacity * sizeof(Square));
    lists.data = (ListSquares*) malloc(lists.capacity * sizeof(ListSquares));
    visited.top = -1; lists.top = -1;

    pushVisited(&visited, start);
    pushList(&lists, getAvailableSquares(&visited, start, N));

    while (visited.top != -1) {
        if (visited.top == N * N - 1) {
            printBoard(&visited, N);
            free(visited.data); free(lists.data);
            return true;
        }
        if (lists.data[lists.top].count > 0) {
            Square next = lists.data[lists.top].squares[0];
            for (int i = 0; i < lists.data[lists.top].count - 1; i++)
                lists.data[lists.top].squares[i] = lists.data[lists.top].squares[i+1];
            lists.data[lists.top].count--;
            pushVisited(&visited, next);
            pushList(&lists, getAvailableSquares(&visited, next, N));
        } else {
            popVisited(&visited);
            popList(&lists);
        }
    }
    free(visited.data); free(lists.data);
    return false;
}

int main() {
    int N;
    Square start;

    printf("Enter N: ");
    scanf("%d", &N);
    printf("Enter start x y: ");
    scanf("%d %d", &start.x, &start.y);

    if (!solveKnightTour(start, N)) {
        printf("No solution\n");
    }
    return 0;
}