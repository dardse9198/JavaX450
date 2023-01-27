package m_controller;

import m_model.MovieDTO;
import model.BoardDTO;

import java.util.ArrayList;

public class MovieController {
    private ArrayList<MovieDTO> list;
    private int nextId;
    public MovieController() {
        list = new ArrayList<>();

        nextId = 1;

        for(int i=1; i<=6; i++) {
            MovieDTO m = new MovieDTO();
            m.setMovieName(String.valueOf(i));
            m.setPlot(String.valueOf(i));
            m.setLimit(String.valueOf(i));
            m.setScore(i);

            add(m);
        }
    }


    public void add(MovieDTO movieDTO) {
        movieDTO.setId(nextId++);
        list.add(movieDTO);
    }
    public MovieDTO selectOne(int id) {
        MovieDTO temp = new MovieDTO(id);
        if (list.contains(temp)) {
            return new MovieDTO(list.get(list.indexOf(temp)));
        }
        return null;
    }

    public ArrayList<MovieDTO> selectAll() {
        ArrayList<MovieDTO> temp = new ArrayList<>();
        for (MovieDTO m : list) {
            temp.add(new MovieDTO(m));
        }

        return temp;
    }

    public void update(MovieDTO movieDTO) {
        list.set(list.indexOf(movieDTO), movieDTO);
    }
    public void delete(int id) {
        list.remove(new MovieDTO(id));
    }
}
