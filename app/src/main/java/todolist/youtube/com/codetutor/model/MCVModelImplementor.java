package todolist.youtube.com.codetutor.model;

import java.util.ArrayList;
import java.util.List;

import todolist.youtube.com.codetutor.exception.ToDoNotFoundException;
import todolist.youtube.com.codetutor.model.bean.ToDo;
import todolist.youtube.com.codetutor.model.db.ToDoListDBAdapter;

public class MCVModelImplementor implements MVCModel {

    ToDoListDBAdapter toDoListDBAdapter;
    List<ToDo> toDoItems;

    List<Observer> observers;


    public MCVModelImplementor(ToDoListDBAdapter toDoListDBAdapter){
        this.toDoListDBAdapter = toDoListDBAdapter;
        toDoItems = this.toDoListDBAdapter.getAllToDos();
        observers = new ArrayList<Observer>();
    }

    public void registerObserver(Observer observer){
        observers.add(observer);
    }

    private void notifyObservers(){
        for(Observer observer: observers){
            observer.update();
        }
    }

    @Override
    public List<ToDo> getAllToDos() throws Exception{
        if(this.toDoItems!=null && this.toDoItems.size()>0){
            return this.toDoItems;
        } else {
          throw new Exception("Empty To Do List");
        }

    }

    @Override
    public void addToDoItem(String toDoItem, String place) throws Exception{
        boolean addSuccess = toDoListDBAdapter.insert(toDoItem, place);
        if (addSuccess){
            refresh();
            notifyObservers();
        }else{
            throw new Exception("Some thing went wrong!!!");
        }
    }

    @Override
    public void removeToDoItem(int id) throws Exception{

        boolean deleteSuccess = toDoListDBAdapter.delete(id);
        if(deleteSuccess){
            refresh();
            notifyObservers();
        }else{
            throw new ToDoNotFoundException("Id is wrong");
        }

    }

    @Override
    public void modifyToDoItem(int id, String newToDoValuel) throws Exception{
        boolean modifySuccess = toDoListDBAdapter.modify(id,newToDoValuel);
        if(modifySuccess){
            refresh();
            notifyObservers();
        } else{
            throw new ToDoNotFoundException("Id is wrong");
        }
    }

    private void refresh(){
        toDoItems.clear();
        toDoItems = this.toDoListDBAdapter.getAllToDos();
    }


}
