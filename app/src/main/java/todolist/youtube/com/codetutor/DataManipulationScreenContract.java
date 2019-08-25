package todolist.youtube.com.codetutor;

import todolist.youtube.com.codetutor.model.bean.ToDo;

public class DataManipulationScreenContract  {

    public interface View extends BaseView<Presenter> {
        void showSelectedToDo(ToDo toDo);
        void updateViewOnRemove();
        void updateViewOnModify(ToDo toDo);
        void showError(String errorMessage);
    }

    public interface  Presenter extends BasePresenter{
        void onRemoveButtonClicked(long id);
        void onModifyButtonClicked(long id, String newValue);
    }
}
