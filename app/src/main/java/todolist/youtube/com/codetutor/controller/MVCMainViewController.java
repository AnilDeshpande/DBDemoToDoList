package todolist.youtube.com.codetutor.controller;

import android.os.Bundle;

import todolist.youtube.com.codetutor.model.MCVModelImplementor;
import todolist.youtube.com.codetutor.view.MainViewImplementor;

public class MVCMainViewController {
    MCVModelImplementor mvcModel;
    MainViewImplementor mvcView;

    FragmentActionListener fragmentActionListener;

    public void setFragmentActionListener(FragmentActionListener fragmentActionListener){
        this.fragmentActionListener = fragmentActionListener;
    }


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
       if(fragmentActionListener!=null){
           Bundle bundle = new Bundle();
           bundle.putLong("todoId", toDoId);
           fragmentActionListener.onActionPerformed(FragmentActionListener.ACTION_PERFORMED.ON_ITEM_SELECTED, bundle);
       }
   }

}
