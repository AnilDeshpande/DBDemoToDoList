package todolist.youtube.com.codetutor.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import todolist.youtube.com.codetutor.MyApplication;
import todolist.youtube.com.codetutor.bean.ToDo;
import todolist.youtube.com.codetutor.db.ToDoListDBAdapter;
import todolist.youtube.com.codetutor.exception.ToDoNotFoundException;

public class ToDosRepositoryImpl implements ToDosRepository {

    MutableLiveData<List<ToDo>> toDoItems;
    MutableLiveData<ToDo> toDoMutableLiveData;

    ToDoListDBAdapter toDoListDBAdapter;

    private static  ToDosRepository instance = null;

    public static ToDosRepository getInstance(){
        if(instance == null){
            instance = new ToDosRepositoryImpl();
        }
        return instance;
    }

    private ToDosRepositoryImpl(){
        this.toDoListDBAdapter = MyApplication.getToDoListDBAdapter();
        this.toDoItems = new MutableLiveData<>();
        this.toDoItems.setValue(new ArrayList<ToDo>());
    }

    @Override
    public MutableLiveData<List<ToDo>> getAllToDos() throws Exception{
        toDoItems = new MutableLiveData<>();
        toDoItems.setValue(toDoListDBAdapter.getAllToDos());
        return toDoItems;
    }

    @Override
    public void addToDoItem(String toDoItem, String place) throws Exception{
        boolean addSuccess = toDoListDBAdapter.insert(toDoItem, place);
        if (!addSuccess){
            throw new Exception("Some thing went wrong!!!");
        }else{
            if(toDoItems.getValue()==null){
                toDoItems = new MutableLiveData<>();
                toDoItems.setValue(toDoListDBAdapter.getAllToDos());
            }else {
                toDoItems.getValue().clear();
                toDoItems.setValue(toDoListDBAdapter.getAllToDos());
            }
        }
    }

    @Override
    public void removeToDoItem(long id) throws Exception{

        boolean deleteSuccess = toDoListDBAdapter.delete(id);
        if(!deleteSuccess){
            throw new ToDoNotFoundException("Id is wrong");
        }else {
            this.toDoMutableLiveData.setValue(null);
            this.toDoItems.setValue(toDoListDBAdapter.getAllToDos());
        }
    }

    @Override
    public void modifyToDoItem(long id, String newToDoValuel) throws Exception{
        boolean modifySuccess = toDoListDBAdapter.modify(id,newToDoValuel);
        if(!modifySuccess){
            throw new ToDoNotFoundException("Id is wrong");
        }else {
            this.toDoMutableLiveData.setValue(toDoListDBAdapter.getToDo(id));
            this.toDoItems.setValue(toDoListDBAdapter.getAllToDos());
        }
    }

    public MutableLiveData<ToDo> getToDo(long id) throws Exception{
        ToDo toDo = null;
        for(ToDo toDo1: toDoItems.getValue()){
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
