package movie;

import m_viewer.*;

import java.util.Scanner;

public class movie {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserViewer userViewer = new UserViewer(scanner);
        MovieViewer movieViewer = new MovieViewer(scanner);
        TheaterViewer theaterViewer = new TheaterViewer(scanner);
        ScreenViewer screenViewer = new ScreenViewer(scanner);
        GradeViewer gradeViewer = new GradeViewer(scanner);
        // scanner 객체의 초기화 식만 바꿔주면 위의 두개의 뷰어 변경할게 없어서 변경의 양이 줄어듦
        // userViewer boardViewer 초기화는 setter 사용(생성자로 하면 둘 초기화하는데 서로가 필요..?)

        userViewer.setMovieViewer(movieViewer);
        userViewer.setTheaterViewer(theaterViewer);
        userViewer.setScreenViewer(screenViewer);
        userViewer.setGradeViewer(gradeViewer);
        movieViewer.setUserViewer(userViewer);
        theaterViewer.setUserViewer(userViewer);
        theaterViewer.setScreenViewer(screenViewer);
        screenViewer.setUserViewer(userViewer);
        movieViewer.setGradeViewer(gradeViewer);
        gradeViewer.setUserViewer(userViewer);
        userViewer.showIndex(); // userViewer의 index 실행시켜야함(로그인 되어야 글 쓸 수 있음..)
    }
}