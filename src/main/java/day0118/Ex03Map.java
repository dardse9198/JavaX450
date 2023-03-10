package day0118;

import model.UserDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Ex03Map {
    public static void main(String[] args) {
        HashMap<String, UserDTO> map = new HashMap<>();

        Map<String, UserDTO> map2 = new TreeMap<>();

//        for(int 1; i<=5; i++) {
//
//        }
//        System.out.println(map.get("U1"));
//
//
//        for(int i=1; i<=5; i++) {
//            UserDTO u = new UserDTO();
//            u.setId(i);
//            u.setUsername("U"+i);
//            u.setNickname("사용자"+i);
//            map.put(u.getUsername(), u);
//        }


        UserDTO u = new UserDTO();
        u.setId(1);
        u.setUsername("U" + 1);
        u.setNickname("사용자" + 1);
        if(!map.containsKey(u.getUsername())) {
            map.put(u.getUsername(), u);
        }

        UserDTO u2 = new UserDTO();
        u2.setId(2);
        u2.setUsername("U" + 2);
        u2.setNickname("사용자" + 2);
        if(!map.containsKey(u2.getUsername())) {
            map.put(u2.getUsername(), u2);
        }

        UserDTO u3 = new UserDTO();
        u3.setId(3);
        u3.setUsername("U" + 3);
        u3.setNickname("사용자" + 3);
        if(!map.containsKey(u3.getUsername())) {
            map.put(u3.getUsername(), u3);
        }

        UserDTO u4 = new UserDTO();
        u4.setId(4);
        u4.setUsername("U" + 4);
        u4.setNickname("사용자" + 4);
        if(!map.containsKey(u4.getUsername())) {
            map.put(u4.getUsername(), u4);
        }

        System.out.println(map.get("U1").getNickname());

        for(String key : map.keySet()) {
            System.out.println(map.get(key));
        }

        System.out.println(map.get("u91"));  // null
        map.computeIfAbsent("u91", k -> new UserDTO());
        System.out.println(map.get("u91"));
    }
}
