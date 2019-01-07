package todolist.youtube.com.codetutor;

import todolist.youtube.com.codetutor.model.MVCModel;

public class MVCController {
    MVCModel mvcModel;
    MVCView mvcView;

    MVCController(MVCModel mvcModel, MVCView mvcView){
        this.mvcModel = mvcModel;
        this.mvcView = mvcView;
    }

    public void onScreenLoad(){
        mvcView.showAllToDos();;
    }

    public void onAddButtonClicked(String toDoItem, String place){
        boolean addSucess = mvcModel.addToDoItem( toDoItem,  place);
    }
}
