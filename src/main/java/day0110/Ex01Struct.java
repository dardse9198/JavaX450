package day0110;

public class Ex01Struct {
    public static void main(String[] args) {
        Student student = new Student();
        student.id = 3;
        student.name = "조재영";
        student.korean = 80;
        student.english = 80;
        student.math = 81;
    }

    public static void printInfo(Student student){
        System.out.println("번호: " + student.id);
        System.out.println("이름: " + student.name);
    }
}
