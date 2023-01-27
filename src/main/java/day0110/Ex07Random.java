package day0110;

import java.util.Random;

public class Ex07Random {
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextDouble());
        System.out.println(random.nextInt());
        System.out.println(random.nextInt(100));
        System.out.println(random.nextInt(100) + 40);
    }
}
