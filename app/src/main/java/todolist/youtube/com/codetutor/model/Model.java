package todolist.youtube.com.codetutor.model;

import java.util.List;

import todolist.youtube.com.codetutor.model.bean.ToDo;

public interface Model {

    public List<ToDo> getAllToDos() throws Exception;
    public ToDo getToDo(long id) throws Exception;
    public boolean addToDoItem(String toDoItem, String place) throws Exception;
    public boolean removeToDoItem(long id) throws Exception;
    public boolean modifyToDoItem(long id, String newToDoValuel) throws Exception;
}
