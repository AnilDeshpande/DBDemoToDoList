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

public class CommonViewModel extends ViewModel implements LifecycleObserver {

    private static final String TAG = CommonViewModel.class.getSimpleName();


    private ToDosRepository toDosRepository = ToDosRepositoryImpl.getInstance();


    private MutableLiveData<List<ToDo>> mutableToDoList = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<ToDo> toDoMutableLiveData;

    List<ToDo> toDoList = new ArrayList<>();

    public CommonViewModel(){
        try{
            mutableToDoList = toDosRepository.getAllToDos();
        }catch (Exception e){
            mutableToDoList.setValue(toDoList);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void refreshData(){
        try{
            mutableToDoList.setValue(toDosRepository.getAllToDos().getValue());
        }catch (Exception e){
            errorMessage.setValue(e.getMessage());
            mutableToDoList.setValue(toDoList);
        }
    }

    public LiveData<List<ToDo>> getMutableToDoList(){
        return mutableToDoList;
    }

    public void addToDoList(String todoItem, String place){
        try{
            toDosRepository.addToDoItem(todoItem, place);
            mutableToDoList.setValue(toDosRepository.getAllToDos().getValue());
        }catch (Exception e){
            errorMessage.setValue(e.getMessage());
        }
    }

    public LiveData<String> getErrorStatus(){
        return errorMessage;
    }

    public LiveData<ToDo> getToDo(long id){
        try{
            this.toDoMutableLiveData = toDosRepository.getToDo(id);
        }catch (Exception e){
            this.errorMessage = new MutableLiveData<>(e.getMessage());

        }
        return this.toDoMutableLiveData;
    }

    public void removeToDo(long id){
        try{
            toDosRepository.removeToDoItem(id);
            toDoMutableLiveData.setValue(null);
        }catch (Exception e){
            this.errorMessage.setValue(e.getMessage());
        }
    }

    public void modifyToDo(long id, String newToDo){
        try{
            toDosRepository.modifyToDoItem(id,newToDo);
            toDoMutableLiveData = toDosRepository.getToDo(id);
        }catch (Exception e){
            this.errorMessage.setValue(e.getMessage());
        }
    }
}
