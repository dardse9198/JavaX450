package day0110;

import util.ScannerUtil;

import java.util.Scanner;

// 무한루프를 사용하여
// 사용자가 입력을 누를 때마다 새로운 학생의 정보가 입력이 되고
// 출력을 누를때마다 맨 마지막으로 입력된 학생의 정보가 출력되는 프로그램을
// 작성해보시오.
public class Ex04Gradebook02 {
    public static void main(String[] args) {
        Student student = new Student();
        Scanner scanner = new Scanner(System.in);
        boolean inputSwitch = false;

        while (true) {
            int select;
            System.out.println("정보 입력: 1 / 정보 출력: 2 / 마치기 : 0");
            int userChoice = scanner.nextInt();

            if (userChoice==1) {
                insertInfo(scanner, student);
                inputSwitch = true;
            }
            else if(userChoice==2) {
                if(inputSwitch) {
                    printInfo(student);
                } else {
                    System.out.println("아직 입력된 학생이 없습니다.");
                }
            }
            else {
                System.out.println("감사합니다");
                break;
            }
        }

        scanner.close();
    }
    public static void insertInfo(Scanner scanner, Student student) {
        String message;

        message = "학생의 번호를 입력해주세요";
        student.id = ScannerUtil.nextInt(scanner, message);

        message = "학생의 이름를 입력해주세요";
        student.name = ScannerUtil.nextLine(scanner, message);

        message = "학생의 국어 점수를 입력해주세요";
        student.korean = ScannerUtil.nextInt(scanner, message, 0, 100);

        message = "학생의 영어 점수를 입력해주세요";
        student.english = ScannerUtil.nextInt(scanner, message, 0, 100);

        message = "학생의 수학 점수를 입력해주세요";
        student.math = ScannerUtil.nextInt(scanner, message, 0, 100);
    }
    public static void printInfo(Student s) {
        System.out.printf("번호: %d번 이름: %s\n", s.id, s.name);
        System.out.printf("국어: %d점 영어: %d점 수학: %d점\n", s.korean, s.english, s.math);
        System.out.printf("총점: %d점 평균: %.2f점\n", calculateSum(s), calculateAverage(s));
    }

    public static int calculateSum(Student s) {
        return s.korean + s.english + s.math;
    }

    public static double calculateAverage(Student s) {
        return (double) calculateSum(s) / 3;
    }
}