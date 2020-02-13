package todolist.youtube.com.codetutor.viewmodel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;
import todolist.youtube.com.codetutor.repository.ToDosRepository;
import todolist.youtube.com.codetutor.repository.ToDosRepositoryImpl;

public class MainActivityViewModel extends ViewModel implements LifecycleObserver {


    private ToDosRepository toDosRepository = ToDosRepositoryImpl.getInstance();


    private MutableLiveData<List<ToDo>> toDoList = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();



    public MainActivityViewModel(){
        errorMessage = new MutableLiveData<>();
        try{
            toDoList = toDosRepository.getAllToDos();
        }catch (Exception e){
            errorMessage.setValue("Something went wrong");
            toDoList.setValue(new ArrayList<>());
        }


    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void initData(){
        try{
            toDoList.setValue(toDosRepository.getAllToDos().getValue());
        }catch (Exception e){
            errorMessage.setValue(e.getMessage());
            toDoList.setValue(new ArrayList<ToDo>());
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void refreshData(){
        try{
            toDoList.setValue(toDosRepository.getAllToDos().getValue());
        }catch (Exception e){
            errorMessage.setValue(e.getMessage());
            toDoList.setValue(new ArrayList<ToDo>());
        }
    }

    public LiveData<List<ToDo>> getToDoList(){
        /*try{
            toDoList.setValue(toDosRepository.getAllToDos().getValue());
        }catch (Exception e){
            errorMessage.setValue(e.getMessage());
            toDoList.setValue(new ArrayList<ToDo>());
        }*/
        return toDoList;
    }

    public void addToDoList(String todoItem, String place){
        try{
            toDosRepository.addToDoItem(todoItem, place);
        }catch (Exception e){
            errorMessage.setValue(e.getMessage());
        }
    }

    public LiveData<String> getErrorStatus(){
        return errorMessage;
    }
}
