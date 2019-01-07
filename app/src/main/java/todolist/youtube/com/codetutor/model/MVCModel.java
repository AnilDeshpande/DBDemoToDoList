package todolist.youtube.com.codetutor.model;

import todolist.youtube.com.codetutor.model.bean.ToDo;

public interface MVCModel {


    public boolean addToDoItem(String toDoItem, String place);
    public boolean removeToDoItem(int id);
    public boolean modifyToDoItem(int id, String newToDoValuel);
}
