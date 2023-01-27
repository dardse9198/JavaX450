package m_viewer;

import m_controller.TheaterController;
import m_model.TheaterDTO;
import m_model.UserDTO;
import util.ScannerUtil;
import m_viewer.ScreenViewer;
import java.util.ArrayList;
import java.util.Scanner;

public class TheaterViewer {
    private TheaterController theaterController;
    private UserViewer userViewer;
    private final Scanner SCANNER;
    private UserDTO logIn;
    private ScreenViewer screenViewer;

    public TheaterViewer(Scanner scanner) {
        theaterController = new TheaterController();
        SCANNER = scanner;
    }

    public void setScreenViewer(ScreenViewer screenViewer) {
        this.screenViewer = screenViewer;
    }
    public void setUserViewer(UserViewer userViewer) { this.userViewer = userViewer; }

    public void setLogIn(UserDTO logIn) { this.logIn = logIn; }

    public void showMenu() {
        ArrayList<TheaterDTO> list = theaterController.selectAll();
        printList();

        while (true) {
            String message = "1. 극장 개별 보기 2. 종료 ";

            if (logIn.getRating() == 0) {
                message = message + "3. 새로운 극장 등록";
            }
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            if (userChoice == 1) {
                while(true) {
                    message = "개별 정보를 볼 극장의 번호나 뒤로 가시려면 0을 입력해주세요.";
                    userChoice = ScannerUtil.nextInt(SCANNER, message);

                    if (userChoice == 0) {
                        break;
                    } else {
                        while (!list.contains(new TheaterDTO(userChoice))) {
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
                addTheater();
                printList();
            }
        }
    }
    public void printOne(int id) {
        TheaterDTO theaterDTO = theaterController.selectOne(id);
        System.out.println(theaterDTO.toString());
        String message = "1. 현재 상영중인 영화 목록";
        if (logIn.getRating() == 0) {
            message += "2. 극장 수정 3. 극장 삭제 4. 뒤로 가기";
        }
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        if(userChoice == 1) {
            screenViewer.showScreen(id);
        } else if (logIn.getRating() == 0) {
            if (userChoice == 2) {
                update(id);
            } else if (userChoice == 3) {
                delete(id);
            } else if (userChoice == 4) {

            }
        }
    }

    public void printList() {
        ArrayList<TheaterDTO> list = theaterController.selectAll();

        if (list.isEmpty()) {
            System.out.println("아직 등록된 극장이 없습니다.");
        } else {
            for (TheaterDTO t : list) {
                System.out.printf("%d. %s\n", t.getId(), t.getTheaterName());
            }
        }
    }

    private void addTheater() {
        TheaterDTO theaterDTO = new TheaterDTO();

        String message = "극장의 이름을 입력해주세요.";
        theaterDTO.setTheaterName(ScannerUtil.nextLine(SCANNER, message));

        message = "극장의 주소를 입력해주세요.";
        theaterDTO.setPlace(ScannerUtil.nextLine(SCANNER, message));

        message = "극장의 전화번호를 입력해주세요.";
        theaterDTO.setNumber(ScannerUtil.nextLine(SCANNER, message));

        theaterController.add(theaterDTO);
    }

    private void update(int id) {
        TheaterDTO m = theaterController.selectOne(id);

        String message = "새로운 이름을 입력해주세요.";
        m.setTheaterName(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 주소를 입력해주세요.";
        m.setPlace(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 전화번호를 입력해주세요.";
        m.setNumber(ScannerUtil.nextLine(SCANNER, message));

        theaterController.update(m);
    }

    private void delete(int id) {
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);
        if (yesNo.equalsIgnoreCase("Y")) {
            theaterController.delete(id);
            showMenu();
        } else {
            printOne(id);
        }
    }
}
