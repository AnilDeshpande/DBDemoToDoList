package todolist.youtube.com.codetutor.view;

import java.util.List;

import todolist.youtube.com.codetutor.model.bean.ToDo;

public interface MVCMainActivityView extends MVCView{
    public void showAllToDos(List<ToDo> toDoList);
    public void updateViewonAdd(List<ToDo> toDoList);
    public void showErrorToast(String errorMessage);
    public void navigateToDataManipulationActivity(long id);
}
