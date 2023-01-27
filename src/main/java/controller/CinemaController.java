package controller;

import model.CinemaDTO;

import java.util.ArrayList;

public class CinemaController {
    private ArrayList<CinemaDTO> list;
    private int nextId;
    public CinemaController() {
        list = new ArrayList<>();
        nextId = 1;

        for(int i=1; i<=3; i++) {
            CinemaDTO c = new CinemaDTO();
            c.setCinemaName("영화관 " + i);
            c.setNumber("전화번호 " + i);
            c.setPlace("주소 " + i);

            add(c);
        }
    }

    public void add(CinemaDTO cinemaDTO) {
        cinemaDTO.setId(nextId++);
        list.add(cinemaDTO);
    }
    public CinemaDTO selectOne(int id) {
        CinemaDTO temp = new CinemaDTO(id);
        if (list.contains(temp)) {
            return new CinemaDTO(list.get(list.indexOf(temp)));
        }

        return null;
    }

    public ArrayList<CinemaDTO> selectAll() {
        ArrayList<CinemaDTO> temp = new ArrayList<>();
        for (CinemaDTO c : list) {
            temp.add(new CinemaDTO(c));
        }
        return temp;
    }

    public void update(CinemaDTO cinemaDTO) {
        int index = list.indexOf(cinemaDTO);
        list.set(index, cinemaDTO);
    }
    public void delete(int id) {
        CinemaDTO c = new CinemaDTO(id);
        list.remove(c);
    }
}
