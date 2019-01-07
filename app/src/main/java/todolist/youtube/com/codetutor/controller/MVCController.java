package todolist.youtube.com.codetutor.controller;

import todolist.youtube.com.codetutor.model.db.MCVModelImplementor;
import todolist.youtube.com.codetutor.view.MVCView;

public class MVCController {
    MCVModelImplementor mvcModel;
    MVCView mvcView;

   public MVCController(MCVModelImplementor mvcModel, MVCView mvcView){
        this.mvcModel = mvcModel;
        this.mvcView = mvcView;
    }

    public void onScreenLoad(){
        mvcView.showAllToDos(mvcModel.getAllToDos());
    }

    public void onAddButtonClicked(String toDoItem, String place){
        boolean addSucess = mvcModel.addToDoItem( toDoItem,  place);
        if(addSucess){
            mvcView.showAllToDos(mvcModel.getAllToDos());
        }
    }

    public void onRemoveBottonClicked(int id){
        boolean success = mvcModel.removeToDoItem(id);
        if(success){
            mvcView.upDateViewOnRemove(mvcModel.getAllToDos());
        }
    }

    public void onModifyButtonClicked(int id, String newValue){
        boolean success = mvcModel.modifyToDoItem(id,newValue);
        if(success){
            mvcView.upDateViewOnRemove(mvcModel.getAllToDos());
        }
    }


}
