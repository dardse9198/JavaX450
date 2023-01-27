package controller;

import m_model.MovieDTO;
import model.ScreenDTO;

import java.util.ArrayList;

public class ScreenController {
    private ArrayList<ScreenDTO> list;
    private int nextId;

    public ScreenController() {
        list = new ArrayList<>();

        nextId = 1;

        for(int i=1; i<=3; i++) {
            ScreenDTO s = new ScreenDTO();
            s.setTime("시간 " + i);
            s.setCinemaId(i);
            s.setMovieId(i);

            add(s);
        }
    }

    public void add(ScreenDTO screenDTO) {
        screenDTO.setId(nextId++);
        list.add(screenDTO);
    }

    public ScreenDTO selectOne(int id) {
        ScreenDTO screenDTO = new ScreenDTO(id);
        if(list.contains(screenDTO)) {
            int index = list.indexOf(screenDTO);
            return new ScreenDTO(list.get(index));
        }
        return null;
    }

    public ArrayList<ScreenDTO> selectAll(int cinemaId) {
        ArrayList<ScreenDTO> temp = new ArrayList<>();
        for (ScreenDTO s : list) {
            if (s.getCinemaId() == cinemaId) {
                temp.add(new ScreenDTO(s));
            }
        }
        return temp;
    }

    public ArrayList<ScreenDTO> selectAll() {
        ArrayList<ScreenDTO> temp = new ArrayList<>();
        for (ScreenDTO s : list) {
            temp.add(new ScreenDTO(s));
        }
        return temp;
    }

    public void update(ScreenDTO screenDTO) {
        int index = list.indexOf(screenDTO);
        list.set(index, screenDTO);
    }
    public void delete(int id) {
        list.remove(new ScreenDTO(id));
    }

    public boolean validateScreen(int cinemaId, int movieId) {
        return selectOne(cinemaId, movieId) == null;
    }
    public ScreenDTO selectOne(int cinemaId, int movieId) {
        ScreenDTO temp = new ScreenDTO(cinemaId, movieId);
        for(ScreenDTO s : selectAll(cinemaId)) {
            if (s.getMovieId() == movieId) {
                return new ScreenDTO(s);
            }
        }
        return null;
    }

    public void deleteByWriterId(int filmId) {

    }

    public void deleteByFilmId(int filmId) {
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).getMovieId() == filmId) {
                list.remove(i);
                i--;
            }
        }
    }
}
