package todolist.youtube.com.codetutor.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;

public interface ToDosRepository {
    LiveData<List<ToDo>> getAllToDos() throws Exception;
    LiveData<ToDo> getToDoLiveData(long id) throws Exception;

    void addToDoItem(String toDoItem, String place) throws Exception;
    void removeToDoItem(long id) throws Exception;
    void modifyToDoItem(long id, String newToDoValue) throws Exception;
}
