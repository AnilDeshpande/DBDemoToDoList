package todolist.youtube.com.codetutor.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import todolist.youtube.com.codetutor.MyApplication;
import todolist.youtube.com.codetutor.bean.ToDo;
import todolist.youtube.com.codetutor.db.ToDoListDBAdapter;
import todolist.youtube.com.codetutor.db.ToDosDAO;
import todolist.youtube.com.codetutor.db.ToDosRoomDataBase;
import todolist.youtube.com.codetutor.exception.ToDoNotFoundException;

public class ToDosRepositoryImpl implements ToDosRepository {

    private static final String TAG = "ToDosRepositoryImpl";

    private LiveData<List<ToDo>> mutableToDoItems;
    private LiveData<ToDo> toDoMutableLiveData;
    private ToDosRoomDataBase dataBase;


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
        dataBase.dataBaseExecutorService.execute(()->{
            this.mutableToDoItems = toDosDAO.getAllToDos();
        });
        this.toDoMutableLiveData = null;
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
            this.mutableToDoItems = toDosDAO.getAllToDos();
        });

    }

    @Override
    public void removeToDoItem(long id) throws Exception{
        dataBase.dataBaseExecutorService.execute(()->{
            toDosDAO.delete(getToDo(id));
            this.mutableToDoItems = toDosDAO.getAllToDos();
        });

    }

    @Override
    public void modifyToDoItem(long id, String newToDoValue) throws Exception{
        ToDo temp = getToDo(id);
        temp.setToDo(newToDoValue);

        dataBase.dataBaseExecutorService.execute(()->{
            toDosDAO.updateToDo(temp);
            this.mutableToDoItems = toDosDAO.getAllToDos();
        });

    }

    public LiveData<ToDo> getToDoLiveData(long id) throws Exception{
        dataBase.dataBaseExecutorService.execute(()->{
            toDoMutableLiveData = toDosDAO.getToDo(id);
        });
        return toDoMutableLiveData;
    }

    private ToDo getToDo(long id){
        return toDosDAO.getToDo(id).getValue();
    }
}
