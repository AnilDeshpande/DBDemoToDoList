package todolist.youtube.com.codetutor;

public interface MVCView {
    public void onAddButtonClicked();
    public void onRemoveButtonClicked(int id);
    public void onModifyButtonClicked(int id, String newToDoValue);
}
