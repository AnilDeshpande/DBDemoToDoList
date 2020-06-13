package todolist.youtube.com.codetutor.viewmodel;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;

public interface CommonViewModel {

    LiveData<List<ToDo>> getToDoList();
    LiveData<String> getErrorStatus();
    LiveData<ToDo> getToDo(long id);

    void addToDo(String todoItem, String place);
    void removeToDo(long id);
    void modifyToDo(long id, String newToDo);
}