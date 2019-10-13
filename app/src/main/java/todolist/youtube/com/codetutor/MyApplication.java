package todolist.youtube.com.codetutor;

import android.app.Application;

import todolist.youtube.com.codetutor.db.ToDoListDBAdapter;
import todolist.youtube.com.codetutor.repository.ToDosRepository;

public class MyApplication extends Application {

    static ToDoListDBAdapter toDoListDBAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
        toDoListDBAdapter = ToDoListDBAdapter.getToDoListDBAdapterInstance(this);

    }

    public static ToDoListDBAdapter getToDoListDBAdapter() {
        return toDoListDBAdapter;
    }
}
