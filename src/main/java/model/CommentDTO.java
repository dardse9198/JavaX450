package model;

public class CommentDTO {
    private int boardId;
    private int id;
    private int writerId;
    private String writerNickname;
    private String comment;

    public int getBoradId() {
        return boardId;
    }

    public void setBoradId(int boardId) {
        this.boardId = boardId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public String getWriterNickname() {
        return writerNickname;
    }

    public void setWriterNickname(String writerNickname) {
        this.writerNickname = writerNickname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CommentDTO(CommentDTO origin) {
        boardId = origin.boardId;
        id = origin.id;
        writerId = origin.writerId;
        writerNickname = origin.writerNickname;
        comment = origin.comment;
    }

    public CommentDTO() {

    }

    public CommentDTO(int board) {
        this.boardId = boardId;
    }

    public CommentDTO(int id, int writerId) {
        this.id = id;
        this.writerId = writerId;
    }

    public boolean equals(Object o) {
        if(o instanceof CommentDTO) {
            CommentDTO c = (CommentDTO) o;
            return id == c.id;
        }
        return false;
    }
}
