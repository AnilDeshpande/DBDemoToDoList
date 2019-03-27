package todolist.youtube.com.codetutor.view;

import java.util.List;

import todolist.youtube.com.codetutor.model.bean.ToDo;

public interface MVCMainActivityView extends MVCView{

    public void bindDataToView();

    public void showAllToDos(List<ToDo> toDoList);
    public void showErrorToast(String errorMessage);
}
