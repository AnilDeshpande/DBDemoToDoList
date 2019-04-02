package todolist.youtube.com.codetutor.controller;

import todolist.youtube.com.codetutor.model.MCVModelImplementor;
import todolist.youtube.com.codetutor.view.MainActivityViewImplementor;

public class MVCMainActivityController implements MVCController{
    MCVModelImplementor mvcModel;
    MainActivityViewImplementor mvcView;

   public MVCMainActivityController(MCVModelImplementor mvcModel, MainActivityViewImplementor mvcView){
        this.mvcModel = mvcModel;
        this.mvcView = mvcView;
    }

   @Override
   public void onViewLoaded() {
        try{
            mvcView.showAllToDos(mvcModel.getAllToDos());
        }catch (Exception e){
            mvcView.showErrorToast(e.getMessage());
        }
   }


   public void onAddButtonClicked(String toDoItem, String place) {
       try{
           boolean success = mvcModel.addToDoItem( toDoItem,  place);
           if(success){
               mvcView.updateViewonAdd(mvcModel.getAllToDos());
           }
       }catch (Exception e){
           mvcView.showErrorToast(e.getMessage());
       }
   }

   public void onToDoItemSelected(long toDoId){
       mvcView.navigateToDataManipulationActivity(toDoId);
   }

}
