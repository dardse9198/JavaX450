package m_model;

public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private int rating;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean equals(Object o) {
        if(o instanceof UserDTO) {
            UserDTO u = (UserDTO) o;
            return id == u.id;
        }
        return false;
    }

    public UserDTO(UserDTO origin) {
        id = origin.id;
        username = origin.username;
        password = origin.password;
        nickname = origin.nickname;
        rating = origin.rating;
    }

    public UserDTO() {

    }

    public String toString() {
        return "{" +
                "id: " + id + ", " +
                "username: " + username + ", " +
                "password: " + password + ", "  +
                "nickname: " + nickname +
                "}";
    }
}