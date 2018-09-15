package todolist.youtube.com.codetutor.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;

@Database(entities = {ToDo.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase{

    List<ToDo> todos;

    public abstract ToDoDao toDoDao();

    private static AppDataBase appDataBase;

    public static AppDataBase getInstance(Context context){
        if(appDataBase==null){
            appDataBase = Room.databaseBuilder(context, AppDataBase.class,"todo.db").
                    fallbackToDestructiveMigration().
                    build();
        }
        return appDataBase;
    }


}
