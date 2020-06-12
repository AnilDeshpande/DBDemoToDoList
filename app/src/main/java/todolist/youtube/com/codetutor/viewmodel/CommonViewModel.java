package todolist.youtube.com.codetutor.viewmodel;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;
import todolist.youtube.com.codetutor.repository.ToDosRepository;
import todolist.youtube.com.codetutor.repository.ToDosRepositoryImpl;

public interface CommonViewModel extends LifecycleObserver {

    public LiveData<List<ToDo>> getMutableToDoList();
    public LiveData<String> getErrorStatus();
    public LiveData<ToDo> getToDo(long id);

    public void addToDoList(String todoItem, String place);
    public void removeToDo(long id);
    public void modifyToDo(long id, String newToDo);

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void refreshData();
}
