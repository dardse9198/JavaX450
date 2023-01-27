package day0109;
import java.util.Scanner;
public class uu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("줄 입력: ");
        int lineNumber = scanner.nextInt();

        int maxLine = lineNumber * 2 - 1;

        for(int i = 1; i <= maxLine; i++) {
            int starSpace = 0;
            String stars = "";

            if(i == 1 || i == maxLine) {
                for (int j = 1; j <= maxLine; j++) {
                    stars += "*";
                }
            }
            else {
                starSpace = 0;
                if(i<lineNumber) {
                    starSpace = lineNumber - i + 1;
                }
                else {
                    int lowerI = i - lineNumber + 1;
                    starSpace = lowerI;
                }

                int emptySpace = maxLine - 2 * starSpace;

                for (int j = 1; j <= starSpace; j++) {
                    stars += "*";
                }
                for (int j = 1; j <= emptySpace; j++) {
                    stars += " ";
                }
                for (int j = 1; j <= starSpace; j++) {
                    stars += "*";
                }
            }

            System.out.println(stars);
        }
    }
}
