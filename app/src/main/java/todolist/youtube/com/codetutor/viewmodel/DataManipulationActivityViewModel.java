package todolist.youtube.com.codetutor.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import todolist.youtube.com.codetutor.DataManipulationActivity;
import todolist.youtube.com.codetutor.bean.ToDo;
import todolist.youtube.com.codetutor.repository.ToDosRepository;
import todolist.youtube.com.codetutor.repository.ToDosRepositoryImpl;

public class DataManipulationActivityViewModel extends ViewModel {

    private ToDosRepository toDosRepository = ToDosRepositoryImpl.getInstance();

    private MutableLiveData<ToDo> toDoMutableLiveData;
    private MutableLiveData<String> errorMessage;

    public DataManipulationActivityViewModel(){
        toDoMutableLiveData = new MutableLiveData<>();
        errorMessage  = new MutableLiveData<>();
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

    public LiveData<String> getErrorMessage(){
        return errorMessage;
    }
}
