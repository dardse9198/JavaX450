package viewer;

import controller.FilmController;
import controller.CinemaController;
import controller.ScreenController;
import model.FilmDTO;
import model.ScreenDTO;
import model.CinemaDTO;
import model.MemberDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class ScreenViewer {
    private ScreenController screenController;
    private FilmController filmController;
    private CinemaController cinemaController;
    private MemberViewer memberViewer;
    private FilmViewer filmViewer;
    private final Scanner SCANNER;
    private final int LEVEL_CRITIC = 2;
    private final int LEVEL_ADMIN = 3;
    private final int OPTION_ALL = 1;
    private final int OPTION_GENERAL = 2;
    private final int OPTION_CRITIC = 3;
    private MemberDTO logIn = null;
    public ScreenViewer(Scanner scanner) {
        SCANNER = scanner;
        screenController = new ScreenController();
        filmController = new FilmController();
    }
    public void setLogIn(MemberDTO logIn) { this.logIn = logIn; }
    public void setMemberViewer(MemberViewer memberViewer) { this.memberViewer = memberViewer; }
    public void setFilmViewer(FilmViewer filmViewer) { this.filmViewer = filmViewer; }

    public void showMenu(int cinemaId) {
        printList(cinemaId);
    }

    private void printList(int cinemaId) {
        ArrayList<ScreenDTO> list = screenController.selectAll(cinemaId);

        if(list.isEmpty()) {
            System.out.println("현재 상영중인 영화가 없습니다.");
        } else {
            for(ScreenDTO s : list) {
                FilmDTO filmDTO = filmController.selectOne(s.getMovieId());
                System.out.printf("%d. %s\n", s.getId(), s.getTime());
                System.out.println("%s" + filmDTO.getTitle());
            }
        }
    }

    private void printList() {
        ArrayList<ScreenDTO> list = screenController.selectAll();

        if(list.isEmpty()) {
            System.out.println("현재 상영중인 영화가 없습니다.");
        } else {
            for(ScreenDTO s : list) {
                FilmDTO filmDTO = filmController.selectOne(s.getMovieId());
                System.out.printf("%d. %s\n", s.getId(), s.getTime());
                System.out.println("%s" + filmDTO.getTitle());
            }
        }
    }

    public void showAdminOption() {
        if (logIn.getLevel() == 0) {
            String message = "1. 새로운 상영정보 등록 2. 기존 상영정보 수정 3. 기존 상영정보 삭제";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if(userChoice == 1) {
                addScreen();
            } else if(userChoice == 2) {
                updateScreen();
            } else if(userChoice == 3) {
                deleteScreen();
            }
        }
    }

    public void addScreen() {
        ScreenDTO screenDTO = new ScreenDTO();

        String message = "영화의 번호를 입력해주세요.";

        while(true) {
            int filmId = ScannerUtil.nextInt(SCANNER, message);
            if (filmController.selectOne(filmId) != null) {
                screenDTO.setMovieId(filmId);
                break;
            } else {
                System.out.println("해당하는 영화의 번호가 없습니다.");
            }
        }

        message = "극장의 번호를 입력해주세요.";

        while(true) {
            int cinemaId = ScannerUtil.nextInt(SCANNER, message);
            if (cinemaController.selectOne(cinemaId) != null) {
                screenDTO.setCinemaId(cinemaId);
                break;
            } else {
                System.out.println("해당하는 극장의 번호가 없습니다.");
            }
        }
        message = "영화의 상영시간을 입력해주세요.";
        screenDTO.setTime(ScannerUtil.nextLine(SCANNER, message));

        screenController.add(screenDTO);
    }

    public void updateScreen() {
        String message = "수정할 상영 번호를 입력해주세요.";

        while(true) {
            int id = ScannerUtil.nextInt(SCANNER, message);
            ScreenDTO s = screenController.selectOne(id);
            if (s != null) {
                message = "새로운 영화의 번호를 입력해주세요.";
                int movieId = ScannerUtil.nextInt(SCANNER, message);
                if (cinemaController.selectOne(movieId) != null) {
                    s.setMovieId(movieId);
                    message = "새로운 상영시간을 입력해주세요.";
                    s.setTime(ScannerUtil.nextLine(SCANNER, message));
                    screenController.update(s);
                    break;
                } else {
                    System.out.println("해당하는 영화의 번호가 없습니다.");
                }

                message = "새로운 극장의 번호를 입력해주세요.";

                while(true) {
                    int cinemaId = ScannerUtil.nextInt(SCANNER, message);
                    if (cinemaController.selectOne(cinemaId) != null) {
                        s.setCinemaId(cinemaId);
                        break;
                    } else {
                        System.out.println("해당하는 극장의 번호가 없습니다.");
                    }
                }
            } else {
                System.out.println("해당하는 상영 번호가 없습니다.");
            }
        }
    }

    public void deleteScreen() {
        String message = "삭제할 상영 번호를 입력해주세요.";
        while(true) {
            int id = ScannerUtil.nextInt(SCANNER, message);
            ScreenDTO s = screenController.selectOne(id);
            if (s != null) {
                screenController.delete(id);
                break;
            } else {
                System.out.println("해당하는 상영 번호가 없습니다.");
            }
        }
    }
    public void showMenu(int id, int optionAll) {
        printList(id);
    }


    public void infoScreen() {
        printList();
        if(logIn.getLevel() == LEVEL_ADMIN)
            showAdminOption();
        else
            System.out.println("1. 예약하기 2. 뒤로가기");
    }

    public void deleteByWriterFilmId(int filmId) {
        screenController.deleteByFilmId(filmId);
    }
}
