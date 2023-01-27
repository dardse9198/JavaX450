package board0112_x;//package board0112_x;
//
//import viewer.BoardViewer;
//import viewer.CommentViewer;
//import viewer.UserViewer;
//
//import java.util.Scanner;
//
//public class Ex03Board02 {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        BoardViewer boardViewer = new BoardViewer(scanner);
//        UserViewer userViewer = new UserViewer(scanner);
//        CommentViewer commentViewer = new CommentViewer(scanner);
//        // scanner 객체의 초기화 식만 바꿔주면 위의 두개의 뷰어 변경할게 없어서 변경의 양이 줄어듦
//        // userViewer boardViewer 초기화는 setter 사용(생성자로 하면 둘 초기화하는데 서로가 필요..?)
//
//        userViewer.setBoardViewer(boardViewer);
//        boardViewer.setUserViewer(userViewer);
//        boardViewer.setCommentViewer(commentViewer);
//        userViewer.setCommentViewer(commentViewer);
//        commentViewer.setUserViewer(userViewer);
//        commentViewer.setBoardViewer(boardViewer);
//        userViewer.showIndex(); // userViewer의 index 실행시켜야함(로그인 되어야 글 쓸 수 있음..)
//
//    }
//}
//
//// 의존성 주입 -> 1. Scanner 생성자 통해서 주입