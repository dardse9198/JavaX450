package day0109;
import java.util.Scanner;
public class StarPrinter06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("줄 수 입력 : ");
        System.out.print("> ");
        int lineNumber = scanner.nextInt();

        for (int i = 1; i <= lineNumber; i++) {
            String stars = "";
            for (int j = 1; j <= i - 1; j++) {
                stars += " ";
            }
            for (int j = 2 * i - 1; j <= 2 * lineNumber - 1; j++) {
                stars += "*";
            }

            //풀이
//            for (int j = 1; j <= j <= 2*(lineNumber-i)+1; j++) {
//                stars += "*";
//            }

            System.out.println(stars);
        }
        System.out.println("6번\n");

        for (int i = lineNumber; i >= 1; i--) {
            String stars = "";
            for (int j = 1; j <= lineNumber - i; j++) {
                stars += " ";
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                stars += "*";
            }
            System.out.println(stars);
        }
        System.out.println("6번-2\n");

        scanner.close();
    }
}
