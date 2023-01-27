package m_controller;

import m_model.GradeDTO;
import m_model.UserDTO;
import java.util.ArrayList;

public class GradeController {
    private ArrayList<GradeDTO> list;
    private int nextId;

    public GradeController() {
        list = new ArrayList<>();
        nextId = 1;
    }

    public void add(GradeDTO gradeDTO) {
        gradeDTO.setId(nextId++);
        list.add(gradeDTO);
    }
    public GradeDTO selectOne(int movieId, int writerId) {
        GradeDTO temp = new GradeDTO(movieId, writerId);
        if(list.contains(temp)) {
            return new GradeDTO(list.get(list.indexOf(temp)));
        }
        return null;
    }

    public GradeDTO selectOne(int movieId) {
        GradeDTO temp = new GradeDTO(movieId);
        if(list.contains(temp)) {
            return new GradeDTO(list.get(list.indexOf(temp)));
        }
        return null;
    }

    public ArrayList<GradeDTO> selectGeneral(int movieId) {
        ArrayList<GradeDTO> temp = new ArrayList<>();
        for (GradeDTO g : list) {
            if (g.getMovieId() == movieId) {
                temp.add(new GradeDTO(g));
            }
        }
        return temp;
    }

    public ArrayList<GradeDTO> selectPro(int movieId) {
        ArrayList<GradeDTO> temp = new ArrayList<>();
        for(GradeDTO g : list) {
            if (g.getMovieId() == movieId) {
//                UserDTO u = userController.selectById(g.getWriterId());
//                if(u == null) {
//                    System.out.println("null 오류");
//                }
//                else if (u.getRating() == 4)
//                    temp.add(new GradeDTO(g));
            }
        }
        return temp;
    }

    public ArrayList<GradeDTO> selectAll(int movieId) {
        ArrayList<GradeDTO> temp = new ArrayList<>();
        for(GradeDTO g : list) {
            if (g.getMovieId() == movieId) {
                temp.add(new GradeDTO(g));
            }
        }
        return temp;
    }

    public void update(GradeDTO gradeDTO) {
        list.set(list.indexOf(gradeDTO), gradeDTO);
    }
    public void delete(int movieId, int writerId) {

        list.remove(new GradeDTO(movieId, writerId));
    }

}
