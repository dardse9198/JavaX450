package day0126;

import dbConn.ConnectionMaker;
import dbConn.MySqlConnectionMaker;
import viewer.StudentViewr;

public class StudentManager03 {
    public static void main(String[] args) {
        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        StudentViewr studentViewer = new StudentViewr(connectionMaker);
        studentViewer.showMenu();
    }
}
