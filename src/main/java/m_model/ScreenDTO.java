package m_model;

public class ScreenDTO {
    private int id;
    private int movieId;
    private int theaterId;
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ScreenDTO() { }

    public ScreenDTO(int theaterId) {
        this.theaterId = theaterId;
    }

    public ScreenDTO(int theaterId, int id) {
        this.theaterId = theaterId;
        this.id = id;
    }

    public boolean equals(Object o) {
        if (o instanceof ScreenDTO) {
            ScreenDTO s = (ScreenDTO) o;
            return id == s.id;
        }
        return false;
    }


    public ScreenDTO(ScreenDTO origin) {
        id = origin.id;
        movieId = origin.movieId;
        theaterId = origin.theaterId;
        time = origin.time;
    }
}
