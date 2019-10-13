package todolist.youtube.com.codetutor.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;

public interface ToDosRepository {
    public MutableLiveData<List<ToDo>> getAllToDos() throws Exception;
    public ToDo getToDo(long id) throws Exception;
    public boolean addToDoItem(String toDoItem, String place) throws Exception;
    public boolean removeToDoItem(long id) throws Exception;
    public boolean modifyToDoItem(long id, String newToDoValuel) throws Exception;
}
