package m_controller;
import m_model.ScreenDTO;
import java.util.ArrayList;

public class ScreenController {
    private ArrayList<ScreenDTO> list;
    private int nextId;

    public ScreenController() {
        list = new ArrayList<>();

        nextId = 1;

        for(int i=1; i<=3; i++) {
            ScreenDTO s = new ScreenDTO();
            s.setTime(String.valueOf(i));
            s.setMovieId(i);
            s.setTheaterId(i);
            add(s);
        }
    }

    public void add(ScreenDTO screenDTO) {
        screenDTO.setId(nextId++);
        list.add(screenDTO);
    }
    public ScreenDTO selectOne(int theaterId) {
        ScreenDTO temp = new ScreenDTO(theaterId);
        if(list.contains(temp)) {
            return new ScreenDTO(list.get(list.indexOf(temp)));
        }
        return null;
    }

    public ArrayList<ScreenDTO> selectAll(int theaterId) {
        ArrayList<ScreenDTO> temp = new ArrayList<>();
        for (ScreenDTO s : list) {
            if (s.getTheaterId() == theaterId) {
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
        list.set(list.indexOf(screenDTO), screenDTO);
    }
    public void delete(int id) {
        list.remove(new ScreenDTO(id));
    }

    public ScreenDTO selectOne(int theaterId, int id) {
        ScreenDTO temp = new ScreenDTO(theaterId, id);
        if(list.contains(temp)) {
            return new ScreenDTO(list.get(list.indexOf(temp)));
        }
        return null;
    }
}