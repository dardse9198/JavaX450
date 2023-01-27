package model;

import m_model.TheaterDTO;

public class CinemaDTO {
    private int id;
    private String cinemaName;
    private String place;
    private String number;

    public CinemaDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
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

    public CinemaDTO() {

    }

    public boolean equals(Object o) {
        if (o instanceof CinemaDTO) {
            CinemaDTO c = (CinemaDTO) o;
            return id == c.id;
        }
        return false;
    }

    public CinemaDTO(CinemaDTO origin) {
        id = origin.id;
        cinemaName = origin.cinemaName;
        place = origin.place;
        number = origin.number;
    }

    public String toString() {
        return "번호: " + id + " 이름: " + cinemaName + " 전화번호: " + number
                + "\n주소: " + place;
    }
}
