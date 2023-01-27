package board0112_x;

import controller.BoardController;
import controller.CommentController;
import model.CommentDTO;
import controller.UserController;
import model.UserDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class CommentViewer {
    private final Scanner SCANNER;
    private UserViewer userViewer;
    private BoardViewer boardViewer;
    private CommentController commentController;
    private UserController userController;
    private BoardController boardController;

    private int nextId=1;
    private UserDTO logIn;

    public void setLogIn(UserDTO logIn){
        this.logIn = logIn;
    }

    public CommentViewer(Scanner scanner) {
        SCANNER = scanner;
        commentController = new CommentController();
    }
    public void setUserViewer(UserViewer userViewer) {
        this.userViewer = userViewer;
    }

    public void setBoardViewer(BoardViewer boardViewer) {
        this.boardViewer = boardViewer;
    }


    public void writeComment(int id) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setBoradId(id);
        commentDTO.setId(nextId++);
        commentDTO.setWriterId(logIn.getId());
        commentDTO.setWriterNickname(logIn.getNickname());

        String message = "댓글을 입력하세요.";
        commentDTO.setComment(ScannerUtil.nextLine(SCANNER, message));

        commentController.add(commentDTO);

        printList(id);
    }

    public void printList(int id) {
        ArrayList<CommentDTO> list = commentController.selectAll(id, logIn.getId());

        if(list.isEmpty()) {
            System.out.println("아직 등록된 댓글이 없습니다.");
        } else {
            for(CommentDTO c : list) {
                System.out.printf("%d. 작성자 %s : + %s\n", c.getId(), c.getWriterNickname(), c.getComment());
            }
        }
    }

    public void updateComment(int writerId) {
//        CommentDTO commentDTO = new CommentDTO();
//
//        ArrayList<CommentDTO> list = commentController.selectWriterAll(boardId, writerId);;
//
//        if(list.isEmpty()) {
//            System.out.println("");
//        } else {
//            for(CommentDTO c : list) {
//                if(commentDTO.getWriterId() == logIn.getId()) {
//                    list = commentController.selectWriterAll(boardId, writerId);
//                }
//            }
//            String message = "1. 수정 2. 삭제 3. 댓글 등록 4. 댓글 수정 5. 댓글 삭제 6. 뒤로 가기";
//            int userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 6);
//        }
//        boardViewer.printOne(id);
        String message = "수정하실 댓글의 번호를 입력해주세요.";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        CommentDTO c = commentController.selectOne(userChoice, writerId);

        if(c!=null) {
            message = "새로운 내용을 입력해주세요.";
            c.setComment(ScannerUtil.nextLine(SCANNER, message));
            commentController.update(c);
        } else {
            System.out.println("내가 쓴 댓글만 수정 가능합니다.");
        }
    }

    public void deleteComment(int id) {

    }
}
