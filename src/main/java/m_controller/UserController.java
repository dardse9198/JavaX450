package m_controller;

import m_model.UserDTO;

import java.util.ArrayList;

public class UserController {
    private ArrayList<UserDTO> list;
    private int nextId;

    public UserController() {
        list = new ArrayList<>();
        nextId = 1;

        UserDTO register = new UserDTO();
        register.setId(0);
        register.setNickname("mng");
        register.setUsername("mng");
        register.setRating(0);
        register.setPassword("mng");
        list.add(register);
    }

    public void insert(UserDTO userDTO) {
        userDTO.setId(nextId++);
        userDTO.setRating(1);
        list.add(userDTO);
    }

    public UserDTO selectById(int id) {
        for (UserDTO u : list) {
            if (u.getId() == id) {
                return new UserDTO(u);
            }
        }

        return null;
    }

    public void update(UserDTO userDTO) {
        list.set(list.indexOf(userDTO), userDTO);
    }

    public void delete(int id) {
        UserDTO u = new UserDTO();
        u.setId(id);
        list.remove(u);
    }

    public boolean validateUsername(String username) {
        if (username.equalsIgnoreCase("X")) {
            return false;
        }

        for (UserDTO u : list) {
            if (username.equalsIgnoreCase(u.getUsername())) {
                return false;
            }
        }

        return true;
    }

    public UserDTO auth(String username, String password) {
        for (UserDTO u : list) {
            if (username.equalsIgnoreCase(u.getUsername()) && password.equals(u.getPassword())) {
                return new UserDTO(u);
            }
        }

        return null;
    }
}