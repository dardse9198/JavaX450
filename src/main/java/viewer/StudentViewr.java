package viewer;

import dbConn.ConnectionMaker;
import model.StudentDTO;
import util.ScannerUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import controller.StudentController;
public class StudentViewr {
    private static Connection connection = null;
    public static Scanner scanner;
    static final Scanner SCANNER = new Scanner(System.in);
    static PreparedStatement pstmt = null;
    static ResultSet resultSet = null;
    private static StudentController studentController;

    public StudentViewr(ConnectionMaker connectionMaker) {
        scanner = new Scanner(System.in);
        connection = connectionMaker.makeConnection();
    }

    public static void showMenu() {
        String message = "1. 입력 2. 목록 보기 3. 종료";
        while(true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if(userChoice == 1) {
                insertStudent();
            } else if(userChoice == 2) {
                printList();
            } else if(userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
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

    static void insertStudent() {
        StudentDTO s = new StudentDTO();

        String message = "학생의 이름을 입력해주세요.";
        s.setName(ScannerUtil.nextLine(SCANNER, message));

        message = "학생의 국어 점수를 입력해주세요.";
        s.setKorean(ScannerUtil.nextInt(SCANNER, message, 0, 100));

        message = "학생의 영어 점수를 입력해주세요.";
        s.setEnglish(ScannerUtil.nextInt(SCANNER, message, 0, 100));

        message = "학생의 수학 점수를 입력해주세요.";
        s.setMath(ScannerUtil.nextInt(SCANNER, message, 0, 100));

        studentController.add(s);
    }

    public static void printList() {
        StudentController controller = new StudentController(connection);
        ArrayList<StudentDTO> list = controller.selectAll();

        if(list.isEmpty()) {
                System.out.println("아직 입력된 학생이 없습니다.");
        } else {
            for (StudentDTO s : list) {  // while(resultSet.next()) 은 next하는 순간 바로 다음줄로 넘어감
                System.out.printf("%d. %s\n", s.getId(), s.getName());
            }
            String message = "상세보기할 학생의 번호나 뒤로 가시려면 0을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            while(userChoice != 0 && studentController.selectOne(userChoice) == null) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(SCANNER, message);
            }
            if(userChoice != 0) {
                printOne(userChoice);
            }
        }
    }

    static void printOne(int id) {
        StudentController controller = new StudentController(connection);
        StudentDTO s = studentController.selectOne(id);

        if(s == null) {
            System.out.println("해딩 번호는 유효하지 않습니다.");
            printList();
        } else {
            System.out.printf("번호: %d번 이름: %s 국어: %d점 영어: %d점 수학 %d점\n", s.getId(), s.getName(), s.getKorean(), s.getEnglish(), s.getMath());
        }
        String message = "1. 수정 2. 삭제 3. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 3);
        if(userChoice == 1) {
            updateStudent(id);
        } else if(userChoice == 2) {
            updateDelete(id);
        } else if(userChoice == 3) {
            printList();
        }
    }

    static void updateStudent(int id) {
        StudentDTO s = new StudentDTO(id);

        String message = "새로운 국어 점수를 입력해주세요.";
        s.setKorean(ScannerUtil.nextInt(SCANNER, message, 0, 100));
        message = "새로운 영어 점수를 입력해주세요.";
        s.setEnglish(ScannerUtil.nextInt(SCANNER, message, 0, 100));

        message = "새로운 수학 점수를 입력해주세요.";
        s.setMath(ScannerUtil.nextInt(SCANNER, message, 0, 100));

        studentController.update(s);
    }

    static void updateDelete(int id) {
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);
        if(yesNo.equalsIgnoreCase("Y")) {
            studentController.delete(id);
            printList();
        } else {
            printOne(id);
        }
    }
}
