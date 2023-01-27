package day0109;

import util.ScannerUtil;

import java.util.Scanner;

public class EX02Gradebook {
    public static void main(String[] args) {
        // 1. 입력
        Scanner scanner = new Scanner(System.in);

        // 1-1. 번호 입력
        int id = getId(scanner);

        // 1-2. 이름 입력
        String name = getName(scanner);

        // 1-3. 국어 영어 수학 점수 입력
        int korean = getKorean(scanner);
        int english = getEnglish(scanner);
        int math = getMath(scanner);

        // 2. 출력
        printInfo(id, name, korean, english, math);

        scanner.close();
    }

    public static int getId(Scanner scanner) {
        String message = "학생의 번호를 입력해주세요.";

        return ScannerUtil.nextInt(scanner, message);
    }

    public static String getName(Scanner scanner) {
        String temp;
        String message;
        message = "학생의 이름을 입력해주세요.";
        temp = ScannerUtil.nextLine(scanner, message);

        return temp;
    }

    public static int getKorean(Scanner scanner) {
        int temp;
        String message;
        message = "학생의 국어 점수를 입력해주세요.";

        int min = 0;
        int max = 100;
        temp = ScannerUtil.nextInt(scanner, message, min, max);

        return temp;
    }

    public static int getEnglish(Scanner scanner) {
        int temp;
        String message;
        message = "학생의 영어 점수를 입력해주세요.";

        int min = 0;
        int max = 100;
        temp = ScannerUtil.nextInt(scanner, message, min, max);

        return temp;
    }

    public static int getMath(Scanner scanner) {
        int temp;
        String message;
        message = "학생의 수학 점수를 입력해주세요.";

        int min = 0;
        int max = 100;
        temp = ScannerUtil.nextInt(scanner, message, min, max);

        return temp;
    }
    public static void printInfo(int id, String name, int kor, int eng, int math) {
        System.out.println("번호: " + id + "번 이름: " + name);
        System.out.println("국어: " + kor + "점 영어: " + eng + "점 수학: " + math + "점");
        int total = calculateSum(kor, eng, math);
        double average = calculateAverage(total);
        System.out.printf("총점: %d점 평균: %f점\n", total, average);
    }

    public static int calculateSum(int kor, int eng, int math) {
        return kor + eng + math;
    }

    public static double calculateAverage(int total) {
        final int SUBJECT_SIZE = 3;  // 상수
        return total / (double)SUBJECT_SIZE;
    }
}
