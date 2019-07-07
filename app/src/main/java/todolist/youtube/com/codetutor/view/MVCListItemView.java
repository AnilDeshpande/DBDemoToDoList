package todolist.youtube.com.codetutor.view;

import android.view.View;

public interface MVCListItemView {
    public View getRootView();
    public void initViews();
    public void bindDataToView(Object object);
}
