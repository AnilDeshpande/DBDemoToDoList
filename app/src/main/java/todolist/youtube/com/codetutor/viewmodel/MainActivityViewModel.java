package todolist.youtube.com.codetutor.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;
import todolist.youtube.com.codetutor.repository.ToDosRepository;
import todolist.youtube.com.codetutor.repository.ToDosRepositoryImpl;

public class MainActivityViewModel extends ViewModel  {


    private ToDosRepository toDosRepository = ToDosRepositoryImpl.getInstance();


    private MutableLiveData<List<ToDo>> toDoList = null;
    private MutableLiveData<String> errorMessage = null;

    public MainActivityViewModel(){
        toDoList = new MutableLiveData<List<ToDo>>();
        errorMessage = new MutableLiveData<>();
    }

    public LiveData<List<ToDo>> getToDoList(){
        try{
            toDoList = toDosRepository.getAllToDos();
        }catch (Exception e){
            errorMessage.setValue(e.getMessage());
            toDoList.setValue(new ArrayList<ToDo>());
        }
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
