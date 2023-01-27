package m_model;

import model.BoardDTO;

public class MovieDTO {
    private int id;
    private String movieName;
    private String plot;
    private String limit;
    private int score;

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public MovieDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean equals(Object o) {
        if (o instanceof MovieDTO) {
            MovieDTO m = (MovieDTO) o;
            return id == m.id;
        }
        return false;
    }

    public MovieDTO(MovieDTO origin) {
        id = origin.id;
        movieName = origin.movieName;
        plot = origin.plot;
        limit = origin.limit;
        score = origin.score;
    }

    public MovieDTO(int id) { this.id = id; }

    public String toString() {
        return "번호: " + id + " 제목: " + movieName + " 등급: " + limit + " 평점: " + score
                + "\n줄거리: " + plot;
    }
}
