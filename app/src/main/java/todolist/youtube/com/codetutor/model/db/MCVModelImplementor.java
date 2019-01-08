package todolist.youtube.com.codetutor.model.db;

import java.util.List;

import todolist.youtube.com.codetutor.model.MVCModel;
import todolist.youtube.com.codetutor.model.bean.ToDo;

public class MCVModelImplementor implements MVCModel {

    ToDoListDBAdapter toDoListDBAdapter;

    List<ToDo> toDoItems;



    public MCVModelImplementor(ToDoListDBAdapter toDoListDBAdapter){
        this.toDoListDBAdapter = toDoListDBAdapter;
        toDoItems = this.toDoListDBAdapter.getAllToDos();
    }

    public List<ToDo> getAllToDos(){
        return this.toDoItems;
    }

    public boolean addToDoItem(String toDoItem, String place){
        boolean addSuccess = toDoListDBAdapter.insert(toDoItem, place);
        if (addSuccess){
            refresh();

        }

        return toDoListDBAdapter.insert(toDoItem, place);
    }
    public boolean removeToDoItem(int id){

        boolean deleteSuccess = toDoListDBAdapter.delete(id);
        if(deleteSuccess){
            refresh();
        }
        return deleteSuccess;

    }
    public boolean modifyToDoItem(int id, String newToDoValuel){
        boolean modifySuccess = toDoListDBAdapter.modify(id,newToDoValuel);
        if(modifySuccess){
            refresh();
        }
        return modifySuccess;
    }

    private void refresh(){
        toDoItems = this.toDoListDBAdapter.getAllToDos();
    }


}
