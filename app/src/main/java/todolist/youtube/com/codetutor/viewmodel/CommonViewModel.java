package todolist.youtube.com.codetutor.viewmodel;

import android.util.Log;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;
import todolist.youtube.com.codetutor.repository.ToDosRepository;
import todolist.youtube.com.codetutor.repository.ToDosRepositoryImpl;

public class CommonViewModel extends ViewModel implements LifecycleObserver {

    private static final String TAG = CommonViewModel.class.getSimpleName();


    private ToDosRepository toDosRepository ;


    private LiveData<List<ToDo>> liveToDoList;
    private MutableLiveData<String> errorMessage  = new MutableLiveData<>();
    private LiveData<ToDo> toDoMutableLiveData;


    public CommonViewModel(){
        try{
            toDosRepository = ToDosRepositoryImpl.getInstance();
            liveToDoList = toDosRepository.getAllToDos();
        }catch (Exception e){
            Log.i(TAG,e.getMessage());
            errorMessage.setValue(e.getMessage());
        }
    }

    public LiveData<List<ToDo>> getToDoList(){
        return liveToDoList;
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

    public LiveData<ToDo> getToDo(long id){
        try{
            this.toDoMutableLiveData = toDosRepository.getToDoLiveData(id);
        }catch (Exception e){
            this.errorMessage = new MutableLiveData<>(e.getMessage());

        }
        return this.toDoMutableLiveData;
    }

    public void removeToDo(long id){
        try{
            toDosRepository.removeToDoItem(id);
        }catch (Exception e){
            this.errorMessage.setValue(e.getMessage());
        }
    }

    public void modifyToDo(long id, String newToDo){
        try{
            toDosRepository.modifyToDoItem(id,newToDo);
        }catch (Exception e){
            this.errorMessage.setValue(e.getMessage());
        }
    }
}
