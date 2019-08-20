package todolist.youtube.com.codetutor;

import java.util.List;

import todolist.youtube.com.codetutor.model.bean.ToDo;

public interface MainScreenContract {
    interface View extends BaseView<Presenter> {
        void showAllToDos(List<ToDo> toDoList);
        void updateViewOnAdd(List<ToDo> toDoList);
        void showError(String errorMessage);
        void navigateToDataManipulationActivity(long id);
    }

    interface Presenter extends BasePresenter{
        void onAddButtonClicked(String toDoItem, String place);
        void onToDoItemSelected(long toDoId);
    }
}
