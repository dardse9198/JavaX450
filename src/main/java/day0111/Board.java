package day0111;

// 역할 1. 사용자가 입력한 데이터를 객체로 만듦 2. 화면에 출력
public class Board {
    private int id;
    private String title;
    private String writer;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void printBoard() {
        System.out.println("----------------------------");
        System.out.println("NUMBER " + id);
        System.out.println("제목: " + title);
        System.out.println("작성자: " + writer);
        System.out.println(content);
        System.out.println("----------------------------");
    }

    public boolean equals(Object o) {
        if(o instanceof Board) {
            Board b = (Board) o;
            return id == b.id;
        }
        return false;
    }
}

// MVC
// M : dto
// V : viwer
// C : 데이터를 사용자 요청에 맞춰서 처리하고 Viwer로 보내줌 (데이터베이스와의 메인 통신)