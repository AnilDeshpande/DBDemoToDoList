package todolist.youtube.com.codetutor.model;

import java.util.List;

import todolist.youtube.com.codetutor.model.bean.ToDo;

public interface MVCModel {

    public List<ToDo> getAllToDos() throws Exception;
    public void addToDoItem(String toDoItem, String place) throws Exception;
    public void removeToDoItem(int id) throws Exception;
    public void modifyToDoItem(int id, String newToDoValuel) throws Exception;
}
