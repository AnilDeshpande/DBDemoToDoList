package todolist.youtube.com.codetutor.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import todolist.youtube.com.codetutor.MyApplication;
import todolist.youtube.com.codetutor.bean.ToDo;
import todolist.youtube.com.codetutor.db.ToDoListDBAdapter;
import todolist.youtube.com.codetutor.exception.ToDoNotFoundException;

class ToDosRepositoryImpl implements ToDosRepository {

    LiveData<List<ToDo>> toDoItems;

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
    }

    @Override
    public MutableLiveData<List<ToDo>> getAllToDos() throws Exception{
        MutableLiveData<List<ToDo>> dataSet = new MutableLiveData<>();
        toDoItems = dataSet;
        dataSet.setValue(toDoListDBAdapter.getAllToDos());
        return dataSet;
    }

    @Override
    public boolean addToDoItem(String toDoItem, String place) throws Exception{
        boolean addSuccess = toDoListDBAdapter.insert(toDoItem, place);
        if (!addSuccess){
            throw new Exception("Some thing went wrong!!!");
        }
        return addSuccess;
    }

    @Override
    public boolean removeToDoItem(long id) throws Exception{

        boolean deleteSuccess = toDoListDBAdapter.delete(id);
        if(!deleteSuccess){
            throw new ToDoNotFoundException("Id is wrong");
        }
        return deleteSuccess;

    }

    @Override
    public boolean modifyToDoItem(long id, String newToDoValuel) throws Exception{
        boolean modifySuccess = toDoListDBAdapter.modify(id,newToDoValuel);
        if(!modifySuccess){
            throw new ToDoNotFoundException("Id is wrong");
        }
        return modifySuccess;
    }

    public ToDo getToDo(long id) throws Exception{
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
        return toDo;
    }
}
