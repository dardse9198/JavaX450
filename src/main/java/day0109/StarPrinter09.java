package day0109;

import java.util.Scanner;

public class StarPrinter09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("줄 수 입력 : ");
        System.out.print("> ");
        int lineNumber = scanner.nextInt();

        int totalHeight = 2 * lineNumber - 1;

//        for (int i = 1; i <= lineNumber*2-1; i++) {
//            String stars = "";
//            if(i<=lineNumber) {
//                for (int j = 1; j <= lineNumber - i; j++) {
//                    stars += " ";
//                }
//                for (int j = 1; j <= 2 * i - 1; j++) {
//                    stars += "*";
//                }
//            }
//            else {
//                for (int j = 1; j <= i-lineNumber; j++) {
//                    stars += " ";
//                }
//                for (int j = 2 * (i-lineNumber)+1; j <= 2 * lineNumber - 1; j++) {
//                    stars += "*";
//                }
//            }
//            System.out.println(stars);
//        }
//        System.out.println("9번\n");

        for (int i = 1; i <= totalHeight; i++) {
            String stars = "";

            if(i<lineNumber) {
                for (int j = 1; j <= lineNumber - i; j++) {
                    stars += " ";
                }
                for (int j = 1; j <= 2 * i - 1; j++) {
                    stars += "*";
                }
            }
            else {
                int lowerI = i - lineNumber + 1;

                for (int j = 1; j <= lowerI-1; j++) {
                    stars += " ";
                }
                for (int j = 1; j <= 2 * (lineNumber-lowerI) + 1; j++) {
                    stars += "*";
                }
            }
            System.out.println(stars);
        }
        System.out.println("9번\n");

        for (int i = 1; i <= totalHeight; i++) {
            String stars = "";
            int spaceWidth = 0;
            int starWidth = 0;

            if(i<lineNumber) {
                spaceWidth = lineNumber - i;
                starWidth = 2*i-1;
            }
            else {
                int lowerI = i - lineNumber + 1;
                spaceWidth = lowerI - 1;
                starWidth = 2*(lineNumber-lowerI)+1;
            }

            for (int j = 1; j <= spaceWidth; j++) {
                stars += " ";
            }
            for (int j = 1; j <= starWidth; j++) {
                stars += "*";
            }
            System.out.println(stars);
        }
        System.out.println("9-2번\n");

        scanner.close();
    }
}
