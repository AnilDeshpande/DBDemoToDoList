package todolist.youtube.com.codetutor;

import android.app.Application;

import todolist.youtube.com.codetutor.model.db.ToDoListDBAdapter;

public class MyApplication extends Application {

    ToDoListDBAdapter toDoListDBAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
        toDoListDBAdapter = ToDoListDBAdapter.getToDoListDBAdapterInstance(this);
    }

    public ToDoListDBAdapter getToDoListDBAdapter() {
        return toDoListDBAdapter;
    }
}
