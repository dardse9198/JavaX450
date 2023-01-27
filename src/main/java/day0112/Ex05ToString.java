package day0112;

import model.UserDTO;

public class Ex05ToString {
    public static void main(String[] args) {
        int number = 4;
        System.out.println(number);

        String str = "abcd";
        System.out.println(str);

        UserDTO u = new UserDTO();
        u.setId(1);
        u.setUsername("a");
        u.setPassword("a");
        u.setNickname("user1");

        System.out.println(u); // 다형성 때문에 object o라고 파라미터 정의하면 모든 객체가 올 수 있음
        // ToString 정의 안하면 결과 model.UserDTO@27d6c5e0
        // ToString 정의하면 정의한대로
    }
}


