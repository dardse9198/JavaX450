package day0113;
// 정규 표현식 (Regular Expression)

public class Ex03RegExp {
    public static void main(String[] args) {
        System.out.println("1. 숫자 체크");
        String pattern = "\\d";
        System.out.println("\"1\": " + "1".matches(pattern));
        System.out.println("\"a\": " + "a".matches(pattern));  // false
        System.out.println("-------------------------------");

        System.out.println("2. 글자 체크");
        pattern = "\\w";
        System.out.println("\"1\": " + "1".matches(pattern));  // 1도 글자라 true
        System.out.println("\"a\": " + "a".matches(pattern));
        System.out.println("-------------------------------");

        System.out.println("2. 알파벳 체크");
        pattern = "[A-Za-z]";
        System.out.println("\"1\": " + "1".matches(pattern));  // false
        System.out.println("\"a\": " + "a".matches(pattern));
        System.out.println("-------------------------------");

        // 여러개의 숫자로만 이루어졌는지 체크
        System.out.println("3. 여러개의 숫자 체크");
        pattern = "\\d+";  // + : 1개 이상, * : 0개 이상
        System.out.println("\"1\": " + "1".matches(pattern));
        System.out.println("\"123\": " + "123".matches(pattern));
        System.out.println("-------------------------------");

        // 4. 복합적으로 섞어서 사용해보기
        System.out.println("4. 복합적인 사용");
        pattern = "010-\\d{4}-\\d{4}";
        System.out.println("010-1234-1234: " + "010-1234-1234".matches(pattern));
        System.out.println("02-999-8888: " + "02-999-8888".matches(pattern));
        System.out.println("aa@bb.com: " + "0aa@bb.com".matches(pattern));
        System.out.println("-------------------------------");

        pattern = "\\d{3,6}";
        System.out.println("\"1\": " + "1".matches(pattern));
        System.out.println("\"123\": " + "123".matches(pattern));
        System.out.println("\"123456\": " + "123456".matches(pattern));
        System.out.println("\"1234567\": " + "1234567".matches(pattern));
        System.out.println("-------------------------------");
    }
}
