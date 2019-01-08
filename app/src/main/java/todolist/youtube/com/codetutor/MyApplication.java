package todolist.youtube.com.codetutor;

import android.app.Application;

import todolist.youtube.com.codetutor.model.db.ToDoListDBAdapter;

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
