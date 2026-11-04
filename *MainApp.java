public class MainApp {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Student student1 =new Student();
        student1.name="Ahmed";
        student1.id=2026001;
        student1.major="comuter engineering";
          // second
        Student student2 =new Student();
        student2.name="sara";
        student2.id=2026002;
        student2.major="Cybersecurity";
        //therd
        Student student3 =new Student();
        student3.name="lotfi";
        student3.id=2026004;
        student3.major="comuter student";
        student3.email="lotfi200@yahoo.fr";
        // display information of students
        student1.displayInfo();
        student2.displayInfo();
        student3.displayInfo();
        student1.major="Artificail Intelligence";
        student1.displayInfo();
        student3.study();

    };

}
