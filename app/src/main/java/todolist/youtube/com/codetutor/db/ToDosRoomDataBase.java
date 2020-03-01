package todolist.youtube.com.codetutor.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import todolist.youtube.com.codetutor.MyApplication;
import todolist.youtube.com.codetutor.bean.ToDo;

@Database(entities = {ToDo.class}, version = 1, exportSchema = false)
public abstract class ToDosRoomDataBase extends RoomDatabase {

    public abstract ToDosDAO toDosDAO();

    private static volatile ToDosRoomDataBase instance;

    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService dataBaseExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static ToDosRoomDataBase getDatabaseInstance(){
        if(instance==null){
            synchronized (ToDosRoomDataBase.class){
                if(instance==null){
                    instance = Room.databaseBuilder(MyApplication.getContext(), ToDosRoomDataBase.class, "todo_database").build();
                }
            }
        }

        return instance;
    }

}
