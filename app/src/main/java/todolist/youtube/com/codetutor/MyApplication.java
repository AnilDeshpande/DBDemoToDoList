package todolist.youtube.com.codetutor;

import android.app.Application;

import todolist.youtube.com.codetutor.model.ModelImplementor;
import todolist.youtube.com.codetutor.model.Model;
import todolist.youtube.com.codetutor.model.db.ToDoListDBAdapter;

public class MyApplication extends Application {

    static ToDoListDBAdapter toDoListDBAdapter;
    static Model model;

    @Override
    public void onCreate() {
        super.onCreate();
        toDoListDBAdapter = ToDoListDBAdapter.getToDoListDBAdapterInstance(this);
        model = new ModelImplementor(toDoListDBAdapter);
    }

    public static ToDoListDBAdapter getToDoListDBAdapter() {
        return toDoListDBAdapter;
    }

    public static Model getModel(){
        return model;
    }
}
