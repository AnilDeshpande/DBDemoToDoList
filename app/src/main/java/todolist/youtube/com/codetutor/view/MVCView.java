package todolist.youtube.com.codetutor.view;

import android.view.View;

import java.util.List;

import todolist.youtube.com.codetutor.model.bean.ToDo;

public interface MVCView {
    public void showAllToDos(List<ToDo> toDoList);
    public void updateViewonAdd(List<ToDo> toDoList);
    public void upDateViewOnRemove(List<ToDo> toDoList);
    public void updateViewOnModify(List<ToDo> toDoList);
}
