package model;

public class ScreenDTO {
    private int id;
    private int movieId;
    private int cinemaId;
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

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int theaterId) {
        this.cinemaId = cinemaId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ScreenDTO() { }

    public ScreenDTO(int theaterId) {
        this.cinemaId = cinemaId;
    }

    public ScreenDTO(int cinemaId, int id) {
        this.cinemaId = cinemaId;
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
        cinemaId = origin.cinemaId;
        time = origin.time;
    }
}
