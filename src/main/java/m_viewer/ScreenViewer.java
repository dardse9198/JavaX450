package m_viewer;

import m_controller.MovieController;
import m_controller.ScreenController;
import m_model.MovieDTO;
import m_model.ScreenDTO;
import m_model.UserDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class ScreenViewer {
    private ScreenController screenController;
    private MovieController movieController;
    private UserViewer userViewer;
    private MovieViewer movieViewer;
    private final Scanner SCANNER;
    private UserDTO logIn = null;
    public ScreenViewer(Scanner scanner) {
        SCANNER = scanner;
        screenController = new ScreenController();
        movieController = new MovieController();
    }
    public void setLogIn(UserDTO logIn) { this.logIn = logIn; }
    public void setUserViewer(UserViewer userViewer) { this.userViewer = userViewer; }
    public void setMovieViewer(MovieViewer movieViewer) { this.movieViewer = movieViewer; }
    public void showScreen(int theaterId) {
        ArrayList<ScreenDTO> list = screenController.selectAll(theaterId);
        int movieId;

        if(list.isEmpty()) {
            System.out.println("현재 상영중인 영화가 없습니다.");
        } else {
            for(ScreenDTO s : list) {
                MovieDTO movieDTO = movieController.selectOne(s.getMovieId());
                System.out.printf("%d. %s\n", s.getId(), s.getTime());
                System.out.println("%s" + movieDTO.getMovieName());
            }
        }

        if (logIn.getRating() == 0) {
            String message = "1. 새로운 상영정보 등록 2. 기존 상영정보 수정 3. 기존 상영정보 삭제";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if(userChoice == 1) {
                addScreen(theaterId);
            } else if(userChoice == 2) {
                updateScreen(theaterId);
            } else if(userChoice == 3) {
                deleteScreen(theaterId);
            }
        }
    }

    public void addScreen(int theaterId) {
        ScreenDTO screenDTO = new ScreenDTO();
        screenDTO.setTheaterId(theaterId);

        String message = "영화의 번호를 입력해주세요.";

        while(true) {
            int movieId = ScannerUtil.nextInt(SCANNER, message);
            if (movieController.selectOne(movieId) != null) {
                screenDTO.setMovieId(movieId);
                break;
            } else {
                System.out.println("해당하는 영화의 번호가 없습니다.");
            }
        }

        message = "영화의 상영시간을 입력해주세요.";
        screenDTO.setTime(ScannerUtil.nextLine(SCANNER, message));

        screenController.add(screenDTO);
    }

    public void updateScreen(int theaterId) {
        String message = "수정할 상영 번호를 입력해주세요.";
        while(true) {
            int id = ScannerUtil.nextInt(SCANNER, message);
            ScreenDTO s = screenController.selectOne(theaterId, id);
            if (s != null) {
                message = "새로운 영화의 번호를 입력해주세요.";
                int movieId = ScannerUtil.nextInt(SCANNER, message);
                if (movieController.selectOne(movieId) != null) {
                    s.setMovieId(movieId);
                    message = "새로운 상영시간을 입력해주세요.";
                    s.setTime(ScannerUtil.nextLine(SCANNER, message));
                    screenController.update(s);
                    break;
                } else {
                    System.out.println("해당하는 영화의 번호가 없습니다.");
                }
            } else {
                System.out.println("해당하는 상영 번호가 없습니다.");
            }
        }
    }

    public void deleteScreen(int theaterId) {
        String message = "삭제할 상영 번호를 입력해주세요.";
        while(true) {
            int id = ScannerUtil.nextInt(SCANNER, message);
            ScreenDTO s = screenController.selectOne(theaterId, id);
            if (s != null) {
                screenController.delete(id);
                break;
            } else {
                System.out.println("해당하는 상영 번호가 없습니다.");
            }
        }
    }
}
