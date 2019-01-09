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
           mvcModel.addToDoItem( toDoItem,  place);
       }catch (Exception e){
           mvcView.showErrorToast(e.getMessage());
       }
   }

   public void onRemoveBottonClicked(int id){
       try{
          mvcModel.removeToDoItem(id);
       }catch (Exception e){
           mvcView.showErrorToast(e.getMessage());
       }

   }

   public void onModifyButtonClicked(int id, String newValue){
       try{
           mvcModel.modifyToDoItem(id,newValue);
       }catch (Exception e){
           mvcView.showErrorToast(e.getMessage());
       }
   }


}
