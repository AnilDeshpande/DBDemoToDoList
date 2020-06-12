package todolist.youtube.com.codetutor.viewmodel;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;

public interface CommonViewModel extends LifecycleObserver {

    LiveData<List<ToDo>> getToDoList();
    LiveData<String> getErrorStatus();
    LiveData<ToDo> getToDo(long id);

    void addToDo(String todoItem, String place);
    void removeToDo(long id);
    void modifyToDo(long id, String newToDo);

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void refreshData();
}
