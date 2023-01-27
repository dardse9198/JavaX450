package m_controller;

import m_model.TheaterDTO;

import java.util.ArrayList;

public class TheaterController {
    private ArrayList<TheaterDTO> list;
    private int nextId;
    public TheaterController() {
        list = new ArrayList<>();
        nextId = 1;

        for(int i=1; i<=3; i++) {
            TheaterDTO t = new TheaterDTO();
            t.setTheaterName(String.valueOf(i));
            t.setNumber(String.valueOf(i));
            t.setPlace(String.valueOf(i));

            add(t);
        }
    }

    public void add(TheaterDTO theaterDTO) {
        theaterDTO.setId(nextId++);
        list.add(theaterDTO);
    }
    public TheaterDTO selectOne(int id) {
        TheaterDTO temp = new TheaterDTO(id);
        if (list.contains(temp)) {
            return new TheaterDTO(list.get(list.indexOf(temp)));
        }

        return null;
    }

    public ArrayList<TheaterDTO> selectAll() {
        ArrayList<TheaterDTO> temp = new ArrayList<>();
        for (TheaterDTO t : list) {
            temp.add(new TheaterDTO(t));
        }

        return temp;
    }

    public void update(TheaterDTO theaterDTO) {
        list.set(list.indexOf(theaterDTO), theaterDTO);
    }
    public void delete(int id) {
        list.remove(new TheaterDTO(id));
    }
}
