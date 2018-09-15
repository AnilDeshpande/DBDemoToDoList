package todolist.youtube.com.codetutor.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import todolist.youtube.com.codetutor.bean.ToDo;

@Database(entities = {ToDo.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase{

    public abstract ToDoDao toDoDao();

    private static AppDataBase appDataBase;

    public static AppDataBase getInstance(Context context){
        if(appDataBase==null){
            appDataBase = Room.databaseBuilder(context,AppDataBase.class,"todo.db").build();
        }
        return appDataBase;
    }
}
