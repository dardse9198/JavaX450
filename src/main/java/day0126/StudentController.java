package day0126;

import util.ScannerUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentController {
    static final Scanner SCANNER = new Scanner(System.in);
    static PreparedStatement pstmt = null;
    static Connection connection = null;
    static ResultSet resultSet = null;

    public StudentController() {
        initialize();
        try {
            terminate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void initialize() {
        String address = "jdbc:mysql://localhost/basic";
        String username = "root";
        String password = "1111";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(address, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static void terminate() throws SQLException {  // throws SQLException : sql 실행하는 곳에서 try-catch 처리하도록 미뤄둠
        if(resultSet != null) {
            resultSet.close();
        }

        if(pstmt != null) {
            pstmt.close();
        }

        if(connection != null) {
            connection.close();
        }

        SCANNER.close();
    }

    static void add(Student s) {
        String query = "INSERT INTO `student`(`name`, `korean`, `english`, `math`) VALUES(?,?,?,?)";

        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, s.getName());
            pstmt.setInt(2, s.getKorean());
            pstmt.setInt(3, s.getEnglish());
            pstmt.setInt(4, s.getMath());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Student> selectAll() {
        ArrayList<Student> list = new ArrayList<>();

        while(true) {
            try {
                while(resultSet.next()) {
                    Student s = new Student();
                    s.setId(resultSet.getInt("id"));
                    s.setKorean(resultSet.getInt("korean"));
                    s.setEnglish(resultSet.getInt("english"));
                    s.setMath(resultSet.getInt("math"));
                    s.setName(resultSet.getString("name"));

                    list.add(s);
                }
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    static Student selectOne(int id) {
        String query = "SELECT * FROM `student` WHERE `id` = ?";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            resultSet = pstmt.executeQuery();

            if(resultSet.next()) {
                Student s = new Student();
                s.setId(resultSet.getInt("id"));
                s.setName(resultSet.getString("name"));
                s.setKorean(resultSet.getInt("korean"));
                s.setEnglish(resultSet.getInt("english"));
                s.setMath(resultSet.getInt("math"));

                return s;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    static void update(Student s) {
        String query = "UPDATE `student` SET `korean` = ?, `english` = ?, `math` = ? WHERE `id` = ?";

        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, s.getKorean());
            pstmt.setInt(2, s.getEnglish());
            pstmt.setInt(3, s.getMath());
            pstmt.setInt(4, s.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void delete(int id) {
        String query = "DELETE FROM `student` WHERE `id` = ?";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
