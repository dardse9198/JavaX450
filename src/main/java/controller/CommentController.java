package controller;

import model.CommentDTO;

import java.util.ArrayList;
public class CommentController {
    private ArrayList<CommentDTO> list;
    private int nextId;

    public CommentController() {
        list = new ArrayList<>();
        nextId = 1;
    }

    public void add(CommentDTO commentDTO) {
        commentDTO.setId(nextId++);
        list.add(commentDTO);
    }

    public CommentDTO selectOne(int id, int writerID) {
        ArrayList<CommentDTO> temp = selectAll(id, writerID);

        if (list.contains(temp)) {
            return new CommentDTO(list.get(list.indexOf(temp)));
        }

        return null;
    }

    public ArrayList<CommentDTO> selectAll(int id, int writerID) {
        ArrayList<CommentDTO> temp = new ArrayList<>();
        CommentDTO cmt = new CommentDTO(id, writerID);

        for(CommentDTO c : list) {
            if(list.contains(cmt))
                temp.add(new CommentDTO(c));
        }
        return temp;
    }

    public void update(CommentDTO commentDTO) {
        list.set(list.indexOf(commentDTO), commentDTO);
    }

    public void delete(int id) {
        list.remove(new CommentDTO(id));
    }
}
