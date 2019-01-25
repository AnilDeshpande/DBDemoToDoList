package todolist.youtube.com.codetutor.view;

import java.util.List;

import todolist.youtube.com.codetutor.model.bean.ToDo;

public interface MVCDataManipulatorView extends MVCView {
    public void showRemovalSuccess();
    public void showUpdatedValue(ToDo toDo);
    public void showErrorToast(String errorMessage);
}
