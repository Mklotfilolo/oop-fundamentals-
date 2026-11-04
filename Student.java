public class Student {
    // attributs
    String name ;
    int id;
    String major;
    String email;
    // meyhod
    public void displayInfo() {
        System.out.println("student nam :"+name);
        System.out.println("student  id :"+id);
        System.out.println("student major :"+major);
        System.out.println("student email :"+email);
        System.out.println("-------------------------------------");
    }
    public void study() {
        System.out.println("the student "+ name+" is currently studying OOP");
    }
}
