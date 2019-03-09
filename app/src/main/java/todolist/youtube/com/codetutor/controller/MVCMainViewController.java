package todolist.youtube.com.codetutor.controller;

import android.os.Bundle;

import todolist.youtube.com.codetutor.ControllerObserver;
import todolist.youtube.com.codetutor.model.MCVModelImplementor;
import todolist.youtube.com.codetutor.view.MainViewImplementor;

public class MVCMainViewController {
    MCVModelImplementor mvcModel;
    MainViewImplementor mvcView;


   public MVCMainViewController(MCVModelImplementor mvcModel, MainViewImplementor mvcView){
        this.mvcModel = mvcModel;
        this.mvcView = mvcView;
    }

   public void bindDatatoView(){
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
       Bundle bundle = new Bundle();
       bundle.putLong("todoId", toDoId);
       ControllerObserver.getInstance().notify(FragmentActionListener.ACTION_PERFORMED.ON_ITEM_SELECTED, bundle);
   }

}
