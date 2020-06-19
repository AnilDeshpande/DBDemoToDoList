package todolist.youtube.com.codetutor.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import todolist.youtube.com.codetutor.MyApplication;
import todolist.youtube.com.codetutor.bean.ToDo;
import todolist.youtube.com.codetutor.db.ToDoListDBAdapter;
import todolist.youtube.com.codetutor.exception.ToDoNotFoundException;

public class ToDosRepositoryImpl implements ToDosRepository {

    private static final String TAG = "ToDosRepositoryImpl";

    private MutableLiveData<List<ToDo>> mutableToDoItems;
    private MutableLiveData<ToDo> toDoMutableLiveData;

    private List<ToDo> toDoList;

    private ToDoListDBAdapter toDoListDBAdapter;

    private static  ToDosRepository instance = null;

    public static ToDosRepository getInstance(){
        if(instance == null){
            instance = new ToDosRepositoryImpl();
        }
        return instance;
    }

    private ToDosRepositoryImpl(){
        this.toDoListDBAdapter = MyApplication.getToDoListDBAdapter();
        this.mutableToDoItems = new MutableLiveData<>();
        this.mutableToDoItems.setValue(new ArrayList<ToDo>());
    }

    @Override
    public MutableLiveData<List<ToDo>> getAllToDos() throws Exception{
        this.toDoList = toDoListDBAdapter.getAllToDos();
        mutableToDoItems.setValue(this.toDoList);
        Log.i(TAG," mutableToDoItems reference: "+mutableToDoItems);
        return mutableToDoItems;
    }

    @Override
    public void addToDoItem(String toDoItem, String place) throws Exception{
        boolean addSuccess = toDoListDBAdapter.insert(toDoItem, place);
        if (!addSuccess){
            throw new Exception("Some thing went wrong!!!");
        }else{
            toDoList = toDoListDBAdapter.getAllToDos();
            mutableToDoItems.setValue(toDoList);
        }
    }

    @Override
    public void removeToDoItem(long id) throws Exception{
        boolean deleteSuccess = toDoListDBAdapter.delete(id);
        if(!deleteSuccess){
            throw new ToDoNotFoundException("Id is wrong");
        }else {
            this.toDoMutableLiveData.setValue(null);
            this.toDoList = toDoListDBAdapter.getAllToDos();
            this.mutableToDoItems.setValue(this.toDoList);
        }
    }

    @Override
    public void modifyToDoItem(long id, String newToDoValue) throws Exception{
        boolean modifySuccess = toDoListDBAdapter.modify(id, newToDoValue);
        if(!modifySuccess){
            throw new ToDoNotFoundException("Id is wrong");
        }else {
            this.toDoMutableLiveData.setValue(toDoListDBAdapter.getToDo(id));
            this.mutableToDoItems.setValue(toDoListDBAdapter.getAllToDos());
        }
    }

    public MutableLiveData<ToDo> getToDo(long id) throws Exception{
        ToDo toDo = null;
        for(ToDo toDo1: mutableToDoItems.getValue()){
            if(toDo1.getId()==id){
                toDo = toDo1;
                break;
            }
        }
        if(toDo==null){
            throw new ToDoNotFoundException("Id is wrong");
        }
        toDoMutableLiveData = new MutableLiveData<>(toDo);
        return this.toDoMutableLiveData;
    }
}
