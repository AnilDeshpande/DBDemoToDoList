package todolist.youtube.com.codetutor.viewmodel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;
import todolist.youtube.com.codetutor.repository.ToDosRepository;
import todolist.youtube.com.codetutor.repository.ToDosRepositoryImpl;

public class CommonViewModelImplementor extends ViewModel implements CommonViewModel {

    private static final String TAG = CommonViewModel.class.getSimpleName();

    private ToDosRepository toDosRepository;

    private MutableLiveData<List<ToDo>> mutableToDoList;
    private MutableLiveData<String> errorMessage;
    private MutableLiveData<ToDo> toDoMutableLiveData;

    public CommonViewModelImplementor(){
        toDosRepository = ToDosRepositoryImpl.getInstance();
        mutableToDoList = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
        toDoMutableLiveData = new MutableLiveData<>();

        try{
            mutableToDoList = toDosRepository.getAllToDos();
        }catch (Exception e){
            mutableToDoList.setValue(new ArrayList<>());
        }
    }

    public void addToDo(String todoItem, String place){
        try{
            toDosRepository.addToDoItem(todoItem, place);
            mutableToDoList.setValue(toDosRepository.getAllToDos().getValue());
        }catch (Exception e){
            errorMessage.setValue(e.getMessage());
        }
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

    public LiveData<List<ToDo>> getToDoList(){
        return mutableToDoList;
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

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void refreshData(){
        try{
            mutableToDoList.setValue(toDosRepository.getAllToDos().getValue());
        }catch (Exception e){
            errorMessage.setValue(e.getMessage());
            mutableToDoList.setValue(new ArrayList<>());
        }
    }
}
