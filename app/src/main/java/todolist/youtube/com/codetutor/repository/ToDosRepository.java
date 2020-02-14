package todolist.youtube.com.codetutor.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;

public interface ToDosRepository {
    MutableLiveData<List<ToDo>> getAllToDos() throws Exception;
    MutableLiveData<ToDo> getToDo(long id) throws Exception;
    void addToDoItem(String toDoItem, String place) throws Exception;
    void removeToDoItem(long id) throws Exception;
    void modifyToDoItem(long id, String newToDoValue) throws Exception;
}
