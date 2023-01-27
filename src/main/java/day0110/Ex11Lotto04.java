package day0110;
// 로또 시뮬레이텨
// ver4.0
// 사용자로부터 총 몇게임할지 입력 받은 후에
// 해당 게임마다 각각 자동/수동 입력을 받아서
// 알맞게 처리하는 프로그램

import util.ScannerUtil;

import java.util.Random;
import java.util.Scanner;

public class Ex11Lotto04 {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final int NUMBER_LENGTH = 6;
    public static final int NUMBER_MIN = 1;
    public static final int NUMBER_MAX = 45;

    public static int[] userNumbers = new int[NUMBER_LENGTH];
    public static int[] computerNumbers = new int[NUMBER_LENGTH];

    public static void main(String[] args) {
        String message = "몇번의 게임을 하시겠습니까?: ";
        int gameSize = ScannerUtil.nextInt(SCANNER, message);

        for(int i=0; i<gameSize; i++) {
            message = "1. 자동 / 2. 수동";
            int selectNumber = ScannerUtil.nextInt(SCANNER, message);
            if(selectNumber == 1) {
                autoUserInput(userNumbers);
            } else {
                manualUserInput(userNumbers);
            }
            sort(userNumbers);
            conputerInput(computerNumbers);
            sort(computerNumbers);
            printResult();

            userNumbers = new int[NUMBER_LENGTH];
            computerNumbers = new int[NUMBER_LENGTH];
        }
    }
    public static void autoUserInput(int[] array) {
        for(int i=0; i<array.length;) {
            Random random = new Random();
            int temp = random.nextInt(NUMBER_MAX) + NUMBER_MIN;
            if(!contains(array, temp)){
                array[i] = temp;
                i++;
            }
        }
    }

    public static void manualUserInput(int[] array) {
        Random random = new Random();
        for(int i=0; i<array.length;) {
            String message = "1~45 사이의 숫자를 입력해주세요.";
            int temp = ScannerUtil.nextInt(SCANNER, message);
            if (temp < NUMBER_MIN || temp > NUMBER_MAX) {
                message = "다시 입력하세요(1~45 사이)";
                temp = ScannerUtil.nextInt(SCANNER, message);
            }
            if(!contains(array, temp)) {
                array[i] = temp;
                i++;
            } else {
                message = "중복입니다. 다시 입력하세요(1~45 사이)";
                temp = ScannerUtil.nextInt(SCANNER, message);
            }
        }
    }

    public static void conputerInput(int[] array) {
        for(int i=0; i<array.length;) {
            Random random = new Random();
            int temp = random.nextInt(NUMBER_MAX) + NUMBER_MIN;
            if(!contains(array, temp)){
                array[i] = temp;
                i++;
            }
        }
    }

    public static boolean contains(int[] array, int temp) {
        for(int i=0; i<array.length; i++) {
            if (temp == array[i]) {
                return true;
            }
        }
        return false;
    }

    public static void printArray(int[] array) {
        for(int i=0; i<array.length; i++) {
            System.out.print(array[i]);
            if(i==array.length-1) {
                System.out.println();
            } else {
                System.out.print(", ");
            }
        }
    }

    public static void printResult() {
        System.out.println("사용자의 숫자");
        printArray(userNumbers);
        System.out.println("컴퓨터의 숫자");
        printArray(computerNumbers);
        System.out.println("총 맞은 갯수: " + countSame());
    }

    public static void sort(int[] array) {
        for(int i=0; i<array.length-1; i++) {
            int temp;
            if(array[i] > array[i+1]) {
                temp = array[i];
                array[i] = array[i+1];
                array[i+1] = temp;
                i = -1;
            }
        }
    }

    public static int countSame() {
        int count=0;
        for(int i=0; i<userNumbers.length; i++) {
            if (contains(userNumbers, computerNumbers[i])) {
                count++;
            }
        }
        return count;
    }
}
