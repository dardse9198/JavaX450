package day0111;

import day0110.Ex12Gradebook03_ex;
import util.ScannerUtil;

import java.util.Scanner;

// 클래스 상속 : 재사용성 위해
// 모든 클래스 java.lang.object 상속하고 있음
// 기본 생성자 : 기본형 데이터 타입 0으로 참조형 데이터 타입은 null로 초기화함
public class Ex01Gradebook01 {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final int STUDENT_SIZE = 5;
    public static final int SCORE_MIN = 0;
    public static final int SCORE_MAX = 100;
    public static Student[] studentArray = new Student[STUDENT_SIZE];

    public static void main(String[] args) {
        Student s = new Student();
        Ex01Gradebook01 ex1 = new Ex01Gradebook01();

//        s.id = 1;
//        s.name = "조재영";
//        s.korean = 80;
//        s.english = 80;
//        s.math = 81;
//
//        s.print();
//
//        Student s2 = new Student();
//        s2.id = 2;
//        s2.name = "조재영2";
//        s2.korean = 90;
//        s2.english = 90;
//        s2.math = 91;
//
//        s2.print();
//
//        Student s3 = new Student();
//        s3.id = 2;
//        s3.name = "조재영2";
//        s3.korean = 90;
//        s3.english = 90;
//        s3.math = 91;
//
//        String a = "Abc";
//        String b = new String("Abc");
//
//        System.out.println(a == b);  // false -> 참조형 데이터 타입이어서 주소값 비교
//        System.out.println(a.equals(b));
//
//        s2.print();
//        s3.print();
//
//        System.out.println(s2.equals(s3));
//        // equals 함수는 return (this==obj)함
//        // equals는 파라미터가 Object -> 오브젝트의 주소값 비교만
//        // s2.equals에서 this는
//        // 오버라이드 : 부모클래스 자식클래스가 재정의

        ex1.showMenu();

        SCANNER.close();
    }
    public void showMenu() {
        while (true) {
            String message = "1. 입력 2. 출력 3. 종료";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            if (userChoice == 1) {
                insertStudent();
            } else if (userChoice == 2) {
                printStudent();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    public void insertStudent() {
        int index = findNextIndex();
        if (index == -1) {
            moveElement();
            index = 4;
        }
        Student s = new Student();
        String message;

        message = "학생의 번호를 입력해주세요.";
        s.setId(ScannerUtil.nextInt(SCANNER, message));

        message = "학생의 이름을 입력해주세요.";
        s.setName(ScannerUtil.nextLine(SCANNER, message));

        message = "학생의 국어 점수를 입력해주세요.";
        s.setKorean(ScannerUtil.nextInt(SCANNER, message, SCORE_MIN, SCORE_MAX));

        message = "학생의 영어 점수를 입력해주세요.";
        s.setEnglish(ScannerUtil.nextInt(SCANNER, message, SCORE_MIN, SCORE_MAX));

        message = "학생의 수학 점수를 입력해주세요.";
        s.setMath(ScannerUtil.nextInt(SCANNER, message, SCORE_MIN, SCORE_MAX));

        studentArray[index] = s;

    }

    public void moveElement(){
        for(int i = 0; i < studentArray.length - 1; i++){
            studentArray[i] = studentArray[i+1];
        }
    }

    public void printStudent() {
        if (findNextIndex() == 0) {
            System.out.println("아직 입력된 학생이 존재하지 않습니다.");
        } else {
            int lastIndex = findNextIndex();
            if(lastIndex == -1){
                lastIndex = 5;
            }
            for (int i = 0; i < lastIndex; i++) {
                Student s = studentArray[i];
                System.out.println("번호: " + s.getId());
            }
        }
    }

    public int findNextIndex() {
        for (int i = 0; i < studentArray.length; i++) {
            if (studentArray[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
