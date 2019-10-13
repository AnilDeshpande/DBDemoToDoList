package todolist.youtube.com.codetutor.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;

public class MainActivityViewModel extends ViewModel  {

    private MutableLiveData<List<ToDo>> toDoList;

    public LiveData<List<ToDo>> getToDoList(){
        return toDoList;
    }
}
