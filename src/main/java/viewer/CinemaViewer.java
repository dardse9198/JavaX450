package viewer;

import controller.CinemaController;
import model.CinemaDTO;
import model.MemberDTO;
import viewer.ScreenViewer;
import viewer.MemberViewer;
import model.CinemaDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class CinemaViewer {
    private CinemaController cinemaController;
    private MemberViewer memberViewer;
    private final Scanner SCANNER;
    private MemberDTO logIn;
    private final int OPTION_ALL = 1;
    private final int LEVEL_GENERAL = 1;
    private final int LEVEL_CRITIC = 2;
    private final int LEVEL_ADMIN = 3;
    private ScreenViewer screenViewer;

    public CinemaViewer(Scanner scanner) {
        cinemaController = new CinemaController();
        SCANNER = scanner;
    }

    public void setScreenViewer(ScreenViewer screenViewer) {
        this.screenViewer = screenViewer;
    }
    public void setMemberViewer(MemberViewer memberViewer) { this.memberViewer = memberViewer; }

    public void setLogIn(MemberDTO logIn) { this.logIn = logIn; }

    public void showMenu() {
        if(logIn.getLevel() == LEVEL_ADMIN) {
            showAdminMenu();
        } else {
            showGeneralMenu();
        }
    }

    private void showAdminMenu() {
        String message = "1. 극장 목록 보기 2. 극장 등록 하기 3. 뒤로 가기";
        while(true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                printList();
            } else if (userChoice == 2) {
                addCinema();
            } else if (userChoice == 3) {
                System.out.println("메인 화면으로 돌아갑니다.");
                break;
            }
        }
    }

    private void showGeneralMenu() {
        String message = "1. 극장 목록 보기 2. 뒤로 가기";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                printList();
            } else if (userChoice == 2) {
                System.out.println("메인 화면으로 돌아갑니다.");
                break;
            }
        }
    }

    private void addCinema() {
        CinemaDTO c = new CinemaDTO();

        String message = "극장 이름을 입력해주세요.";
        c.setCinemaName(ScannerUtil.nextLine(SCANNER, message));

        message = "극장 주소을 입력해주세요.";
        c.setPlace(ScannerUtil.nextLine(SCANNER, message));

        message = "극장 전화번호를 입력해주세요.";
        c.setNumber(ScannerUtil.nextLine(SCANNER, message));

        cinemaController.add(c);
    }

    public void printList() {
        ArrayList<CinemaDTO> list = cinemaController.selectAll();

        if (list.isEmpty()) {
            System.out.println("아직 등록된 극장이 없습니다.");
        } else {
            for (CinemaDTO c : list) {
                System.out.printf("%d. %s\n", c.getId(), c.getCinemaName());
            }
        }

        String message = "상세보기할 극장의 번호나 뒤로 가시려면 0을 입력해주세요.";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);

        while (userChoice != 0 && cinemaController.selectOne(userChoice) == null) {
            System.out.println("잘못 입력하셨습니다.");
            userChoice = ScannerUtil.nextInt(SCANNER, message);
        }

        if (userChoice != 0) {
            printOne(userChoice);
        }
    }

    public void printOne(int id) {
        CinemaDTO c = cinemaController.selectOne(id);

        System.out.println("====================================================");
        System.out.println("극장명: " + c.getCinemaName());
        System.out.println("주소: " + c.getPlace());
        System.out.println("전화번호: " + c.getNumber());
        System.out.println("====================================================");

        if(logIn.getLevel() == LEVEL_ADMIN) {
            showAdminOptions(id);
        } else {
            showGeneralOptions(id);
        }
//
//        String message = "1. 현재 상영중인 영화 목록";
//        if (logIn.getLevel() == 0) {
//            message += "2. 극장 수정 3. 극장 삭제 4. 뒤로 가기";
//        }
//        int userChoice = ScannerUtil.nextInt(SCANNER, message);
//        if(userChoice == 1) {
//            screenViewer.showScreen(id);
//        } else if (logIn.getLevel() == 0) {
//            if (userChoice == 2) {
//                update(id);
//            } else if (userChoice == 3) {
//                delete(id);
//            } else if (userChoice == 4) {
//
//            }
//        }
    }

    private void showAdminOptions(int id) {
        String message = "1. 수정 2. 삭제 3. 뒤로 가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        if (userChoice == 1) {
            update(id);
        } else if (userChoice == 2) {
            delete(id);
        } else if (userChoice == 3) {
            printList();
        }
    }

    private void showGeneralOptions(int id) {
        screenViewer.showMenu(id, OPTION_ALL);
        printList();
    }

    private void update(int id) {
        CinemaDTO m = cinemaController.selectOne(id);

        String message = "새로운 극장명을 입력해주세요.";
        m.setCinemaName(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 주소를 입력해주세요.";
        m.setPlace(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 전화번호를 입력해주세요.";
        m.setNumber(ScannerUtil.nextLine(SCANNER, message));

        cinemaController.update(m);

        printOne(id);
    }

    private void delete(int id) {
        String message = "해당 극장을 정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);
        if (yesNo.equalsIgnoreCase("Y")) {
            cinemaController.delete(id);
            showMenu();
        } else {
            printOne(id);
        }
    }
}
