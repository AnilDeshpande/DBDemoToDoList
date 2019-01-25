package todolist.youtube.com.codetutor.controller;

import todolist.youtube.com.codetutor.model.MCVModelImplementor;
import todolist.youtube.com.codetutor.view.MainActivityViewImplementor;

public class MVCController {
    MCVModelImplementor mvcModel;
    MainActivityViewImplementor mvcView;

   public MVCController(MCVModelImplementor mvcModel,MainActivityViewImplementor mvcView){
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

   /*public void onRemoveBottonClicked(int id){
       try{
           boolean success = mvcModel.removeToDoItem(id);
           if(success){
               mvcView.upDateViewOnRemove(mvcModel.getAllToDos());
           }
       }catch (Exception e){
           mvcView.showErrorToast(e.getMessage());
       }

   }

   public void onModifyButtonClicked(int id, String newValue){
       try{
           boolean success = mvcModel.modifyToDoItem(id,newValue);
           if(success){
               mvcView.updateViewOnModify(mvcModel.getAllToDos());
           }
       }catch (Exception e){
           mvcView.showErrorToast(e.getMessage());
       }
   }*/


}
