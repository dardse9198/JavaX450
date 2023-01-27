package m_model;

public class TheaterDTO {
    private int id;
    private String theaterName;
    private String place;
    private String number;

    public TheaterDTO(int id) { this.id = id; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public TheaterDTO() {

    }

    public boolean equals(Object o) {
        if(o instanceof TheaterDTO) {
            TheaterDTO t = (TheaterDTO) o;
            return id == t.id;
        }
        return false;
    }

    public TheaterDTO(TheaterDTO origin) {
        id = origin.id;
        theaterName = origin.theaterName;
        place = origin.place;
        number = origin.number;
    }

    public String toString() {
        return "번호: " + id + " 이름: " + theaterName + " 전화번호: " + number
                + "\n주소: " + place;
    }
}
