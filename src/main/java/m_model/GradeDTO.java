package m_model;

public class GradeDTO {
    private int id;
    private int writerId;
    private int movieId;
    private int rating;
    private String review;

    public GradeDTO(int movieId, int writerId) {
        this.movieId = movieId;
        this.writerId = writerId;
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

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public GradeDTO() { }
    public GradeDTO(int id) {
        this.id = id;
    }
    public boolean equals(Object o) {
        if (o instanceof GradeDTO) {
            GradeDTO g =(GradeDTO) o;
            return id == g.id;
        }
        return false;
    }

    public GradeDTO(GradeDTO origin) {
        id = origin.id;
        writerId = origin.writerId;
        movieId = origin.movieId;
        rating = origin.rating;
        review = origin.review;
    }
}
