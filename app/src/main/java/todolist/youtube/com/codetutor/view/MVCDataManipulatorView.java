package todolist.youtube.com.codetutor.view;

import java.util.List;

import todolist.youtube.com.codetutor.model.bean.ToDo;

public interface MVCDataManipulatorView extends MVCView {
    public void showSelectedToDo();
    public void updateViewOnRemove();
    public void updateViewOnModify(ToDo toDo);
    public void showErrorToast(String errorMessage);
}
