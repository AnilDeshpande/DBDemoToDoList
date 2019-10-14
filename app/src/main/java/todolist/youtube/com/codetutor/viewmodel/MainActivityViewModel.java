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


    public LiveData<List<ToDo>> getToDoList(){
        errorMessage = new MutableLiveData<>();
        try{
            toDoList = toDosRepository.getAllToDos();
        }catch (Exception e){
            errorMessage = new MutableLiveData<>();
            errorMessage.setValue(e.getMessage());
            toDoList = new MutableLiveData<List<ToDo>>();
            toDoList.setValue(new ArrayList<ToDo>());
        }
        return toDoList;
    }

    public void addToDoList(String todoItem, String place){
        try{
            toDosRepository.addToDoItem(todoItem, place);
            toDoList = toDosRepository.getAllToDos();
        }catch (Exception e){
            errorMessage = new MutableLiveData<>();
            errorMessage.setValue(e.getMessage());
        }
    }

    public LiveData<String> getErrorStatus(){
        return errorMessage;
    }
}
