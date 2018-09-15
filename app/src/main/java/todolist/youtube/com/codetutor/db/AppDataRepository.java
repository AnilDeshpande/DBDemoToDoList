package todolist.youtube.com.codetutor.db;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import todolist.youtube.com.codetutor.bean.ToDo;

public class AppDataRepository {

    List<ToDo> todos;

    private Executor executor;
    private AppDataBase appDataBase;

    public static AppDataRepository appDataRepository;


    private AppDataRepository(Context context){
        executor = Executors.newFixedThreadPool(4);
        appDataBase = AppDataBase.getInstance(context);
    }

    public static synchronized AppDataRepository getAppDataRepositoryInstance(Context context){
        if(appDataRepository==null){
            appDataRepository = new AppDataRepository(context);
        }
        return appDataRepository;
    }


    public void insert(ToDo toDo){

    }


    public void insertAll(ToDo... toDos){

    }

    public List<ToDo> getAll(){
        return this.todos;
    }


    public void delete(ToDo toDo){

    }


    public void update(ToDo toDo){

    }

    public ToDo findById(int id){
        return null;
    }

}
