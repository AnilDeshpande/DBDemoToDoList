package todolist.youtube.com.codetutor.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;

public interface ToDosRepository {
    public MutableLiveData<List<ToDo>> getAllToDos() throws Exception;
    public MutableLiveData<ToDo> getToDo(long id) throws Exception;
    public void addToDoItem(String toDoItem, String place) throws Exception;
    public void removeToDoItem(long id) throws Exception;
    public void modifyToDoItem(long id, String newToDoValuel) throws Exception;
}
