package day0109;
import java.util.Scanner;
public class StarPinter01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("줄 수 입력 : ");
        System.out.print("> ");
        int lineNumber = scanner.nextInt();

        for (int i = 0; i < lineNumber; i++) {
            for (int j = 0; j <= i; j++)
                System.out.print('*');
            System.out.println();
        }
        System.out.println("1번\n");

        for (int i = 1; i <= lineNumber; i++) {
            String stars = "";
            for (int j = i; j <= lineNumber; j++)
                stars += "*";
            System.out.println(stars);
        }
        System.out.println("2번\n");

        for (int i = 1; i <= lineNumber; i++) {
            String stars = "";
            for (int j = 1; j <= lineNumber; j++) {
                if(j<=lineNumber - i) {
                    stars += " ";
                } else {
                    stars += "*";
                }
            }
            System.out.println(stars);
        }
        System.out.println("3번\n");

        for (int i = 1; i <= lineNumber; i++) {
            String stars = "";
            for (int j = 1; j <= lineNumber-i; j++) {
               stars += " ";
            }
            for (int j = 1; j <= 2*i-1; j++) {
                stars += "*";
            }
            System.out.println(stars);
        }
        System.out.println("5번\n");


        scanner.close();
    }
}
