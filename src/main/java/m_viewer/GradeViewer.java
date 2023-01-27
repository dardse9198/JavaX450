package m_viewer;

import m_controller.GradeController;
import m_controller.MovieController;
import m_controller.UserController;
import m_model.GradeDTO;
import m_model.UserDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class GradeViewer {
    private ArrayList<GradeDTO> list;
    private GradeController gradeController;
    private MovieController movieController;
    private UserController userController;
    private UserViewer userViewer;
    private MovieViewer movieViewer;
    private final Scanner SCANNER;
    private UserDTO logIn = null;

    private int writerId;
    public GradeViewer(Scanner scanner) {
        SCANNER = scanner;
        userController = new UserController();
        gradeController = new GradeController();
    }

    public void setLogIn(UserDTO logIn) {
        this.logIn = logIn;
        writerId = logIn.getId();
    }

    public void setUserViewer(UserViewer userViewer) {
        this.userViewer = userViewer;
    }

    public void showRating(int movieId) {
        ArrayList<GradeDTO> list3 = gradeController.selectAll(movieId);
        UserDTO u = userController.selectById(logIn.getId());
//        ArrayList<GradeDTO> list = gradeController.selectGeneral(movieId);
//        ArrayList<GradeDTO> list2 = gradeController.selectGeneral(movieId);
        int rating=0;
        int rating2=0;
        if (list.isEmpty()) {
            System.out.println("현재 입력된 평점이 없습니다.");
        } else {
            for (GradeDTO g : list) {
                if (g.getMovieId() == movieId) {
                    if(u.getRating() != 4) {
                        rating += g.getRating();
                    }
                    else {
                        rating2 += g.getRating();
                    }
                }
                }
            }
            System.out.printf("일반 회원 평점 : %d", rating);
            rating = 0;

//            for (GradeDTO g : list2) {
//                if (g.getMovieId() == movieId) {
//                    rating += g.getRating();
//                }
//            }
            System.out.printf("전문가 평점 : %d", rating);

            //UserDTO u = userController.selectById(logIn.getId());

            //u.getRating() == 4;

        if (logIn.getRating() != 4 && logIn.getRating() != 0) {
            {
                String message = "1. 평점 등록 2. 기존 평점 수정 3. 기존 평점 삭제 0. 돌아가기";
                int userChoice = ScannerUtil.nextInt(SCANNER, message);
                if (userChoice == 1) {
                    if(exist(movieId, writerId) == false) {
                        addRating(movieId, writerId);
                    } else {
                        System.out.println("이미 작성된 평점이 있습니다.");
                    }
                } else if (userChoice == 2) {
                    if(exist(movieId, writerId) == true) {
                        updateRating(movieId, writerId);
                    } else {
                        System.out.println("작성된 평점이 없습니다.");
                    }
                } else if (userChoice == 3) {
                    if(exist(movieId, writerId) == true) {
                        deleteRating(movieId, writerId);
                    } else {
                        System.out.println("작성된 평점이 없습니다.");
                    }
                } else {

                }
            }
        }
    }

    public void printReview(int movieId) {
        ArrayList<GradeDTO> list = gradeController.selectPro(movieId);
        if (list.isEmpty()) {
            System.out.println("현재 입력된 평론이 없습니다.");
        } else {
            System.out.println("평론 : ");
            for (GradeDTO g : list) {
                if (g.getMovieId() == movieId) {
                    UserDTO u = userController.selectById(g.getWriterId());
                    System.out.printf("%d. %s\n", g.getId(), u.getNickname());
                    System.out.println(g.getReview());
                }
            }
        }
        if (logIn.getRating() == 4) {
            String message = "1. 평론 등록 2. 기존 평론 수정 3. 기존 평론 삭제 0. 돌아가기";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            boolean exist = exist(movieId, writerId);

            if (exist == true) {
                System.out.println("이미 평론이 존재합니다.");
            }
            if (userChoice == 1) {
                if (exist == true) {
                    System.out.println("이미 평론이 존재합니다.");
                } else {
                    addReview(movieId);
                }
            } else if (userChoice == 2) {
                message = "수정할 평론 번호를 입력해주세요.";
                int number = ScannerUtil.nextInt(SCANNER, message);
                GradeDTO g = gradeController.selectOne(number);
                if (writerId == g.getWriterId()) {
                    updateReview(number);
                } else {
                    System.out.println("본인이 쓴 평론만 수정할 수 있습니다");
                }
            } else if (userChoice == 3) {
                message = "수정할 평론 번호를 입력해주세요.";
                int number = ScannerUtil.nextInt(SCANNER, message);
                GradeDTO g = gradeController.selectOne(number);
                deleteReview(movieId, writerId);
            } else {

            }
        }
    }

    private boolean exist(int movieId, int writerId) {
        GradeDTO g = gradeController.selectOne(movieId, writerId);
        if (g != null)
            return true;
        else
            return false;
    }

    private void deleteRating(int movieId, int writerId) {
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);
        if (yesNo.equalsIgnoreCase("Y")) {
            gradeController.delete(movieId, writerId);
        } else {

        }
    }

    private void updateRating(int movieId, int writerId) {
        GradeDTO g = gradeController.selectOne(movieId, writerId);
        String message = "수정 평점 입력 (0~5 정수)";
        g.setRating(ScannerUtil.nextInt(SCANNER, message));
        gradeController.update(g);
    }

    private void addRating(int movieId, int writerId) {
        String message = "평점 입력 (0~5 정수)";
        GradeDTO g = new GradeDTO();
        g.setRating(ScannerUtil.nextInt(SCANNER, message));

        g.setWriterId(writerId);
        g.setMovieId(movieId);

        gradeController.add(g);
    }

    public void addReview(int movieId) {
        String message = "평점 입력 (0~5 정수)";
        GradeDTO g = new GradeDTO();
        g.setRating(ScannerUtil.nextInt(SCANNER, message));

        message = "평론 입력";
        g.setReview(ScannerUtil.nextLine(SCANNER, message));

        g.setWriterId(writerId);
        g.setMovieId(movieId);

        gradeController.add(g);
    }

    public void updateReview(int number) {
        GradeDTO g = gradeController.selectOne(number);
        String message = "수정 평점 입력 (0~5 정수)";
        g.setRating(ScannerUtil.nextInt(SCANNER, message));

        message = "수정 평론 입력";
        g.setReview(ScannerUtil.nextLine(SCANNER, message));

        gradeController.update(g);
    }

    public void deleteReview(int movieId, int writerId) {
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);
        if (yesNo.equalsIgnoreCase("Y")) {
            gradeController.delete(movieId, writerId);
        } else {

        }
    }
}
