package todolist.youtube.com.codetutor.presenter;

import todolist.youtube.com.codetutor.DataManipulationScreenContract;
import todolist.youtube.com.codetutor.MyApplication;
import todolist.youtube.com.codetutor.model.Model;

public class DataManipulationScreenPresenterImp implements DataManipulationScreenContract.Presenter {


    DataManipulationScreenContract.View view;
    Model model;
    long toDoId;

    public DataManipulationScreenPresenterImp(DataManipulationScreenContract.View view){
        this.view = view;
        this.model = MyApplication.getModel();
        this.view.setPresenter(this);
    }

    public DataManipulationScreenPresenterImp(DataManipulationScreenContract.View view, long toDoId){
        this.view = view;
        this.model = MyApplication.getModel();
        this.toDoId = toDoId;
    }

    @Override
    public void start() {
        try {
            view.showSelectedToDo(model.getToDo(toDoId));
        }catch (Exception e){
            view.showError(e.getMessage());
        }

    }

    @Override
    public void onModifyButtonClicked(long id, String newValue) {
        try{
            boolean success = model.modifyToDoItem(id,newValue);
            if(success){
                view.updateViewOnModify(model.getToDo(id));
            }
        }catch (Exception e){
            view.showError(e.getMessage());
        }
    }

    @Override
    public void onRemoveButtonClicked(long id) {
        try{
            boolean success = model.removeToDoItem(id);
            if(success){
                view.updateViewOnRemove();
            }
        }catch (Exception e){
            view.showError(e.getMessage());
        }
    }
}
