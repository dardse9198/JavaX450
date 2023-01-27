package day0110;
// 1. 5명의 학생을 성적을 관리하는 프로그램을 작성하시오.
//    단, 5명을 모두 입력한 후에는 더이상 입력할수 없도록 코드를 작성하시오.
// 2. 5명의 학생 성적을 관리하는 프로그램을 작성하시오.
//    단, 5명을 모두 입력한 후에 새로운 학생 정보를 입력할 시에는
//    가장 오래된 기록을 제거하고 새로운 학생 정보를 입력되도록 코드를 작성하시오.

import util.ScannerUtil;

import java.util.Scanner;

public class Ex12Gradebook03 {
    public static final int STUDENT_LENGTH = 5;
    public static final int INFO = 5;
    public static final Scanner SCANNER = new Scanner(System.in);


    public static void main(String[] args) {
        String[][] student = new String[STUDENT_LENGTH][INFO];
        int i=0;

        //1번
        while(true) {
            String message = "1:입력 / 2:출력 / 3:종료";
            int selectNumber = ScannerUtil.nextInt(SCANNER, message);

            if(selectNumber == 1) {
                if(i>4) {
                    System.out.println("더이상 입력할 수 없습니다.");
                } else {
                    insertInfo(SCANNER, student[i]);
                    i+=1;
                }
            } else if (selectNumber == 2) {
                printInfo(student, i);
            } else {
                break;
            }
        }

        i=0;
        //2번
        while(true) {
            String message = "1:입력 / 2:출력 / 3:종료";
            int selectNumber = ScannerUtil.nextInt(SCANNER, message);

            if(selectNumber == 1) {
                if(i>4) {
                    pushInfo(student, i);
                } else {
                    insertInfo(SCANNER, student[i]);
                    i+=1;
                }
            } else if (selectNumber == 2) {
                printInfo(student, i);
            } else {
                break;
            }
        }
    }
    public static void insertInfo(Scanner scanner, String[] student) {
        String message;

        message = "학생의 번호를 입력해주세요";
        student[0] = ScannerUtil.nextLine(scanner, message);

        message = "학생의 이름를 입력해주세요";
        student[1] = ScannerUtil.nextLine(scanner, message);

        message = "학생의 국어 점수를 입력해주세요";
        student[2] = ScannerUtil.nextLine(scanner, message);

        message = "학생의 영어 점수를 입력해주세요";
        student[3] = ScannerUtil.nextLine(scanner, message);

        message = "학생의 수학 점수를 입력해주세요";
        student[4] = ScannerUtil.nextLine(scanner, message);
    }
    public static void pushInfo(String[][] student, int now) {
        for(int i=now-1; i>=1; i--) {
            for(int j=0; j<student.length; j++) {
                student[i][j] = student[i-1][j];
            }
        }
        insertInfo(SCANNER, student[0]);
    }
    public static void printInfo(String[][] student, int now) {
        System.out.println("정보");
        for(int i=0; i<now; i++) {
            System.out.printf("번호: %s번 이름: %s\n", student[i][0], student[i][1]);
            System.out.printf("국어: %s점 영어: %s점 수학: %s점\n", student[i][2], student[i][3], student[i][4]);
            System.out.printf("총점: %d점 평균: %.2f점\n", calculateSum(student[i]), calculateAverage(student[i]));
            System.out.println();
        }
    }

    public static int calculateSum(String[] s) {
        return Integer.parseInt(s[2]) + Integer.parseInt(s[3]) + Integer.parseInt(s[4]);
    }

    public static double calculateAverage(String[] s) {
        return (double) calculateSum(s) / 3;
    }
}
