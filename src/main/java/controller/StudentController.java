package controller;

import model.StudentDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentController {
    static final Scanner SCANNER = new Scanner(System.in);
    static PreparedStatement pstmt = null;
    static Connection connection = null;
    static ResultSet resultSet = null;

    public StudentController(Connection connection) {
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

    public static void add(StudentDTO s) {
        String query = "INSERT INTO `student`(`name`, `korean`, `english`, `math`) VALUES(?,?,?,?)";

        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, s.getName());
            pstmt.setInt(2, s.getKorean());
            pstmt.setInt(3, s.getEnglish());
            pstmt.setInt(4, s.getMath());

            pstmt.executeUpdate();

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("INSERT ERROR");
            e.printStackTrace();
        }
    }

    public static ArrayList<StudentDTO> selectAll() {
        ArrayList<StudentDTO> list = new ArrayList<>();
        String query = "SELECT * FROM `student`";

        while(true) {
            try {
                PreparedStatement pstmt = connection.prepareStatement(query);
                ResultSet resultSet1 = pstmt.executeQuery();

                while(resultSet.next()) {
                    StudentDTO s = new StudentDTO();
                    s.setId(resultSet.getInt("id"));
                    s.setKorean(resultSet.getInt("korean"));
                    s.setEnglish(resultSet.getInt("english"));
                    s.setMath(resultSet.getInt("math"));
                    s.setName(resultSet.getString("name"));

                    list.add(s);
                }
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                System.out.println("SELECTALL ERROR");
                e.printStackTrace();
            }
        }
        return list;
    }

    public static StudentDTO selectOne(int id) {
        String query = "SELECT * FROM `student` WHERE `id` = ?";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            resultSet = pstmt.executeQuery();

            if(resultSet.next()) {
                StudentDTO s = new StudentDTO();
                s.setId(resultSet.getInt("id"));
                s.setName(resultSet.getString("name"));
                s.setKorean(resultSet.getInt("korean"));
                s.setEnglish(resultSet.getInt("english"));
                s.setMath(resultSet.getInt("math"));

                pstmt.close();
                return s;
            }
        } catch (SQLException e) {
            System.out.println("SELECTONE ERROR");
            e.printStackTrace();
        }

        return null;
    }

    public static void update(StudentDTO s) {
        String query = "UPDATE `student` SET `korean` = ?, `english` = ?, `math` = ? WHERE `id` = ?";

        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, s.getKorean());
            pstmt.setInt(2, s.getEnglish());
            pstmt.setInt(3, s.getMath());
            pstmt.setInt(4, s.getId());

            pstmt.executeUpdate();
            } catch (SQLException e) {
            System.out.println("UPDATE ERROR");
            throw new RuntimeException(e);
        }
    }

    public static void delete(int id) {
        String query = "DELETE FROM `student` WHERE `id` = ?";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("UPDATE ERROR");
            e.printStackTrace();
        }
    }
}
