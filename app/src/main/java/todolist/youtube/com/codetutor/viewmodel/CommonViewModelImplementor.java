package todolist.youtube.com.codetutor.viewmodel;

import android.app.Service;
import android.speech.tts.TextToSpeechService;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import todolist.youtube.com.codetutor.MyApplication;
import todolist.youtube.com.codetutor.Speaker;
import todolist.youtube.com.codetutor.bean.ToDo;
import todolist.youtube.com.codetutor.repository.ToDosRepository;
import todolist.youtube.com.codetutor.repository.ToDosRepositoryImpl;

public class CommonViewModelImplementor extends ViewModel implements CommonViewModel {

    private static final String TAG = CommonViewModelImplementor.class.getSimpleName();

    private ToDosRepository toDosRepository ;

    private LiveData<List<ToDo>> liveToDoList;
    private MutableLiveData<String> errorMessage ;
    private LiveData<ToDo> toDoMutableLiveData;

    private Speaker speaker;

    public void setSpeaker(Speaker speaker){
        this.speaker = speaker;
    }

    public CommonViewModelImplementor(){
        toDosRepository = ToDosRepositoryImpl.getInstance();
        errorMessage  = new MutableLiveData<>();
        try{
            liveToDoList = toDosRepository.getAllToDos();
        }catch (Exception e){
            Log.i(TAG,e.getMessage());
            errorMessage.setValue(e.getMessage());
        }
    }

    public LiveData<List<ToDo>> getToDoList(){
        return liveToDoList;
    }

    public void addToDo(String todoItem, String place){
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

    @Override
    public void speakAllToDos() {
        // ToDo - Speak all ToDos in one single go
        if(speaker!=null){
            for(ToDo toDo: liveToDoList.getValue()){
                speaker.speak(toDo);
            }
        }
    }
}
