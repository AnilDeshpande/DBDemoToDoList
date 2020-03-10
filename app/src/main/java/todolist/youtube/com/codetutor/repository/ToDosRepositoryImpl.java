package todolist.youtube.com.codetutor.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;
import todolist.youtube.com.codetutor.db.ToDosDAO;
import todolist.youtube.com.codetutor.db.ToDosRoomDataBase;

public class ToDosRepositoryImpl implements ToDosRepository {

    private static final String TAG = "ToDosRepositoryImpl";

    private LiveData<List<ToDo>> mutableToDoItems;
    private MutableLiveData<ToDo> toDoMutableLiveData;
    private ToDosRoomDataBase dataBase;

    private MutableLiveData<String> message;

    ToDosDAO toDosDAO;

    private static  ToDosRepository instance = null;

    public static ToDosRepository getInstance(){
        if(instance == null){
            instance = new ToDosRepositoryImpl();
        }
        return instance;
    }

    private ToDosRepositoryImpl(){
        dataBase = ToDosRoomDataBase.getDatabaseInstance();
        toDosDAO = dataBase.toDosDAO();

        this.toDoMutableLiveData = new MutableLiveData<>();
        this.message = new MutableLiveData<>();
        this.mutableToDoItems = toDosDAO.getAllToDos();
    }

    @Override
    public LiveData<List<ToDo>> getAllToDos() throws Exception{
        if(this.mutableToDoItems==null){
            this.mutableToDoItems = new MutableLiveData<>();
        }
        return this.mutableToDoItems;
    }

    @Override
    public void addToDoItem(String toDoItem, String place) throws Exception{
        ToDo tempToDo = new ToDo(0,toDoItem, place);
        dataBase.dataBaseExecutorService.execute(()->{
            toDosDAO.insert(tempToDo);
        });
    }

    @Override
    public void removeToDoItem(long id) throws Exception{
        dataBase.dataBaseExecutorService.execute(() ->{
            if(toDosDAO.delete(toDosDAO.getToDo(id))>0){
                this.toDoMutableLiveData.postValue(null);
            }
        });
    }

    @Override
    public void modifyToDoItem(long id, String newToDoValue) throws Exception{
        dataBase.dataBaseExecutorService.execute(()->{
            ToDo temp = toDosDAO.getToDo(id);
            temp.setToDo(newToDoValue);
            if(toDosDAO.updateToDo(temp)>0){
                this.toDoMutableLiveData.postValue(toDosDAO.getToDo(id));
            }
        });
    }

    public LiveData<ToDo> getToDoLiveData(long id) throws Exception{
        dataBase.dataBaseExecutorService.execute(()->{
            toDoMutableLiveData.postValue(toDosDAO.getToDo(id));
        });
        return toDoMutableLiveData;
    }
}
