package todolist.youtube.com.codetutor.presenter;

import todolist.youtube.com.codetutor.MainScreenContract;
import todolist.youtube.com.codetutor.MyApplication;
import todolist.youtube.com.codetutor.model.Model;

public class MainScreenPresenterImp implements MainScreenContract.Presenter {


    MainScreenContract.View view;
    Model model;


    public MainScreenPresenterImp(MainScreenContract.View view){
        this.view = view;
        this.model = MyApplication.getModel();
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        try{
            this.view.showAllToDos(model.getAllToDos());
        }catch (Exception e){
            view.showError(e.getMessage());
        }

    }

    @Override
    public void onAddButtonClicked(String toDoItem, String place) {
        try{
            boolean success = model.addToDoItem( toDoItem,  place);
            if(success){
                view.updateViewOnAdd(model.getAllToDos());
            }
        }catch (Exception e){
            view.showError(e.getMessage());
        }
    }

    @Override
    public void onToDoItemSelected(long toDoId) {
        view.navigateToDataManipulationActivity(toDoId);
    }
}
