package m_viewer;

import m_controller.MovieController;
import m_controller.GradeController;
import m_model.GradeDTO;
import m_model.MovieDTO;
import m_model.UserDTO;
import util.ScannerUtil;
import m_viewer.GradeViewer;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieViewer {
    private MovieController movieController;
    private GradeController gradeController;
    private UserViewer userViewer;
    private GradeViewer gradeViewer;
    private final Scanner SCANNER;
    private UserDTO logIn;

    public MovieViewer(Scanner scanner) {
        movieController = new MovieController();
        gradeController = new GradeController();
        SCANNER = scanner;
    }

    public void setUserViewer(UserViewer userViewer) { this.userViewer = userViewer; }

    public void setGradeViewer(GradeViewer gradeViewer) { this.gradeViewer = gradeViewer;}
    public void setLogIn(UserDTO logIn) { this.logIn = logIn; }

    public void showMenu() {
        ArrayList<MovieDTO> list = movieController.selectAll();
        printList();

        while (true) {
            String message = "1. 영화 개별 보기 2. 종료 ";

            if (logIn.getRating() == 0) {
                message = message + "3. 새로운 영화 등록";
            }
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            if (userChoice == 1) {
                while(true) {
                    message = "개별 정보를 볼 영화의 번호나 뒤로 가시려면 0을 입력해주세요.";
                    userChoice = ScannerUtil.nextInt(SCANNER, message);

                    if (userChoice == 0) {
                        break;
                    } else {
                        while (!list.contains(new MovieDTO(userChoice))) {
                            System.out.println("잘못 입력하셨습니다.");
                            userChoice = ScannerUtil.nextInt(SCANNER, message);
                        }
                        printOne(userChoice);
                    }
                }
            } else if (userChoice == 2) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            } else if (logIn.getRating() == 0 && userChoice == 3) {
                addMovie();
                printList();
            }
        }
    }
    public void printOne(int id) {
        MovieDTO movieDTO = movieController.selectOne(id);

        System.out.println(movieDTO.toString());
        gradeViewer.showRating(id);
        gradeViewer.printReview(id);

        if (logIn.getRating() == 0) {
            String message = "1. 영화 수정 2. 영화 삭제 3. 뒤로 가기";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            if (userChoice == 1) {
                update(id);
            } else if (userChoice == 2) {
                delete(id);
            } else if (userChoice == 3) {

            }
        }
    }

    public void printList() {
        ArrayList<MovieDTO> list = movieController.selectAll();

        if (list.isEmpty()) {
            System.out.println("아직 등록된 글이 없습니다.");
        } else {
            for (MovieDTO m : list) {
                System.out.printf("%d. %s\n", m.getId(), m.getMovieName());
            }
        }
    }

    private void addMovie() {
        MovieDTO movieDTO = new MovieDTO();

        String message = "영화의 제목을 입력해주세요.";
        movieDTO.setMovieName(ScannerUtil.nextLine(SCANNER, message));

        message = "영화의 줄거리를 입력해주세요.";
        movieDTO.setPlot(ScannerUtil.nextLine(SCANNER, message));

        message = "영화의 등급을 입력해주세요.";
        movieDTO.setLimit(ScannerUtil.nextLine(SCANNER, message));

        movieController.add(movieDTO);
    }

    private void update(int id) {
        String message = "수정할 영화의 아이디를 입력하세요.";

        MovieDTO m = movieController.selectOne(id);

        message = "새로운 제목을 입력해주세요.";
        m.setMovieName(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 줄거리를 입력해주세요.";
        m.setPlot(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 등급을 입력해주세요.";
        m.setLimit(ScannerUtil.nextLine(SCANNER, message));

        movieController.update(m);
    }

    private void delete(int id) {
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);
        if (yesNo.equalsIgnoreCase("Y")) {
            movieController.delete(id);
            showMenu();
        } else {
            printOne(id);
        }
    }
}
