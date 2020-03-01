package todolist.youtube.com.codetutor;

import android.app.Application;
import android.content.Context;

import todolist.youtube.com.codetutor.db.ToDoListDBAdapter;
import todolist.youtube.com.codetutor.repository.ToDosRepository;

public class MyApplication extends Application {

    static ToDoListDBAdapter toDoListDBAdapter;

    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //toDoListDBAdapter = ToDoListDBAdapter.getToDoListDBAdapterInstance(this);
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }

    /*public static ToDoListDBAdapter getToDoListDBAdapter() {
        return toDoListDBAdapter;
    }*/
}
