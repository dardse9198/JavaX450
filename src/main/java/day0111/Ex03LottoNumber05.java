package day0111;

import day0110.Ex10Lotto03;
import util.ArrayUtil;
import util.ScannerUtil;

import java.util.Random;
import java.util.Scanner;

public class Ex03LottoNumber05 {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final int ARRAY_LENGTH = 6;
    public static final int NUMBER_MIN = 1;
    public static final int NUMBER_MAX = 45;

    public static void main(String[] args) {
        int[] lottoNumbers = new int[0];
        Random random = new Random();
        while(ArrayUtil.size(lottoNumbers) < 6) {
            int temp = random.nextInt(45) + 1;
            if(!ArrayUtil.contains(lottoNumbers, temp)) {
                lottoNumbers = ArrayUtil.add(lottoNumbers, temp);
            }
        }

        ArrayUtil.sort(lottoNumbers);

        for(int i=0; i<lottoNumbers.length; i++) {
            System.out.println(lottoNumbers[i]);
        }

        String message = "총 몇게임을 하시겠습니까?";
        int gameSize = ScannerUtil.nextInt(SCANNER, message);

        int[][] userNumbers = new int[gameSize][ARRAY_LENGTH];

        setNumbers(userNumbers);

        int[] computerNumbers = new int[ARRAY_LENGTH];
        Ex10Lotto03.setAutoNumbers(computerNumbers);
        Ex10Lotto03.sort(computerNumbers);

        printResult(userNumbers, computerNumbers);
    }
    public static void setNumbers(int[][] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            System.out.println((i + 1) + "번째 게임");
            String message = "1. 자동 2. 수동";
            int userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 2);

            if (userChoice == 1) {
                Ex10Lotto03.setAutoNumbers(arrays[i]);
            } else {
                Ex10Lotto03.setManualNumbers(arrays[i]);
            }

            Ex10Lotto03.sort(arrays[i]);
        }
    }

    public static void printResult(int[][] userNumbers, int[] computerNumbers) { // alt + shift + r : 같은 이름 한번에 바꾸기
        System.out.print("컴퓨터 숫자: ");
        printArray(computerNumbers);
        System.out.println();
        System.out.println("사용자 숫자");
        for (int i = 0; i < userNumbers.length; i++) {
            System.out.printf("%d번 게임: ", i + 1);
            printArray(userNumbers[i]);
            System.out.printf(" - %d개\n", countSame(computerNumbers, userNumbers[i]));
        }
    }

    public static void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%2d", array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");  // json 표기법 (배열은 대괄호로, 객체는 중괄호로)
    }

    public static int countSame(int[] computerNumbers, int[] userNumbers) {
        int count = 0;
        for (int i = 0; i < computerNumbers.length; i++) {
            if (Ex10Lotto03.contains(userNumbers, computerNumbers[i])) {
                count++;
            }
        }

        return count;
    }
}
