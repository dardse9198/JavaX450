package m_viewer;

import m_controller.ScreenController;
import m_controller.MovieController;
import m_controller.UserController;
import m_model.UserDTO;
import util.ScannerUtil;
import m_viewer.MovieViewer;
import java.util.Scanner;
public class UserViewer {
    private final Scanner SCANNER;
    private UserController userController;
    private MovieController movieController;
    private ScreenController screenController;
    private UserDTO logIn = null;
    private MovieViewer movieViewer;
    private TheaterViewer theaterViewer;
    private ScreenViewer screenViewer;
    private GradeViewer gradeViewer;
    public UserViewer(Scanner scanner) {
        SCANNER = scanner;
        userController = new UserController();
    }

    public void setMovieViewer(MovieViewer movieViewer) {
        this.movieViewer = movieViewer;
    }

    public void setTheaterViewer(TheaterViewer theaterViewer) {
        this.theaterViewer = theaterViewer;
    }

    public void setScreenViewer(ScreenViewer screenViewer) {
        this.screenViewer = screenViewer;
    }

    public void setGradeViewer(GradeViewer gradeViewer) { this.gradeViewer = gradeViewer; }
    public void showIndex() {
        String message = "1. 로그인 2. 회원가입 3. 종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                auth();
                if (logIn != null) {
                    if (logIn.getRating() == 0) {
                        System.out.println("관리자 로그인");
                    }
                    movieViewer.setLogIn(logIn);
                    theaterViewer.setLogIn(logIn);
                    screenViewer.setLogIn(logIn);
                    gradeViewer.setLogIn(logIn);
                    showMenu();
                }
            } else if (userChoice == 2) {
                register();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    private void register() {
        String message;
        message = "사용하실 아이디를 입력해주세요.";
        String username = ScannerUtil.nextLine(SCANNER, message);

        while (!userController.validateUsername(username)) {
            System.out.println("해당 아이디는 사용하실 수 없습니다.");
            message = "사용하실 아이디나 뒤로 가실려면 \"X\"를 입력해주세요.";
            username = ScannerUtil.nextLine(SCANNER, message);

            if (username.equalsIgnoreCase("X")) {
                break;
            }
        }

        if (!username.equalsIgnoreCase("X")) {
            UserDTO u = new UserDTO();
            u.setUsername(username);

            message = "사용하실 비밀번호를 입력해주세요.";
            u.setPassword(ScannerUtil.nextLine(SCANNER, message));

            message = "사용하실 닉네임을 입력해주세요.";
            u.setNickname(ScannerUtil.nextLine(SCANNER, message));

            userController.insert(u);
        }
    }

    private void auth() {
        String message;
        message = "아이디를 입력해주세요.";
        String username = ScannerUtil.nextLine(SCANNER, message);

        message = "비밀번호를 입력해주세요.";
        String password = ScannerUtil.nextLine(SCANNER, message);

        logIn = userController.auth(username, password);

        if (logIn == null) {
            System.out.println("로그인 정보가 정확하지 않습니다.");
        }
    }

    private void showMenu() {
        String message = "1. 영화 목록 2. 극장 목록 3. 회원 정보 관리 4. 로그아웃";
        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                movieViewer.showMenu();
            } else if (userChoice == 2) {
                theaterViewer.showMenu();
            } else if (userChoice == 3) {
                printOne();
            } else if (userChoice == 4) {
                logIn = null;
                System.out.println("정상적으로 로그아웃되었습니다.");
            }
        }
    }

    private void showManager() {
        String message = "1. 게시판으로 2. 회원 정보 관리 3. 로그아웃";
        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                //boardViewer.showMenu();
            } else if (userChoice == 2) {
                printOne();
            } else if (userChoice == 3) {
                logIn = null;
                System.out.println("정상적으로 로그아웃되었습니다.");
            }
        }
    }
    private void printOne() {
        System.out.println("회원 번호: " + logIn.getId());
        System.out.println("회원 닉네임: " + logIn.getNickname());
        System.out.println("---------------------------------------------");
        String message = "1. 수정 2. 탈퇴 3. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        if (userChoice == 1) {
            update();
        } else if (userChoice == 2) {
            delete();
        }
    }

    private void update() {

        String message = "새로운 비밀번호를 입력해주세요.";
        String newPassword = ScannerUtil.nextLine(SCANNER, message);

        message = "새로운 닉네임을 입력해주세요.";
        String newNickname = ScannerUtil.nextLine(SCANNER, message);

        message = "기존 비밀번호를 입력해주세요.";
        String oldPassword = ScannerUtil.nextLine(SCANNER, message);

        if (logIn.getPassword().equals(oldPassword)) {
            logIn.setNickname(newNickname);
            logIn.setPassword(newPassword);

            userController.update(logIn);
        } else {
            System.out.println("회원 정보 변경에 실패하였습니다.");
        }
    }

    private void delete() {
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);

        if (yesNo.equalsIgnoreCase("Y")) {
            message = "비밀번호를 입력해주세요.";
            String password = ScannerUtil.nextLine(SCANNER, message);

            if (password.equals(logIn.getPassword())) {
                userController.delete(logIn.getId());
                logIn = null;
            }
        }
    }
}

