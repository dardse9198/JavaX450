package movie;

import viewer.*;

import java.util.Scanner;

public class movieProject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 각 뷰어 초기화
        MemberViewer memberViewer = new MemberViewer(scanner);
        FilmViewer filmViewer = new FilmViewer(scanner);
        ReviewViewer reviewViewer = new ReviewViewer(scanner);
        CinemaViewer cinemaViewer = new CinemaViewer(scanner);
        ScreenViewer screenViewer = new ScreenViewer(scanner);
        // 의존성 주입
        memberViewer.setFilmViewer(filmViewer);
        memberViewer.setReviewViewer(reviewViewer);
        memberViewer.setCinemaViewer(cinemaViewer);
        memberViewer.setScreenViewer(screenViewer);

        filmViewer.setReviewViewer(reviewViewer);
        reviewViewer.setMemberViewer(memberViewer);
        screenViewer.setFilmViewer(filmViewer);
        screenViewer.setMemberViewer(memberViewer);
        memberViewer.showIndex();
    }
}
