package todolist.youtube.com.codetutor.view;

import android.view.View;

public interface MVCListItemView<T> {

    public interface ListItemClickListener{
        void onItemClicked(long position);
    }

    public void setListItemClickListener(ListItemClickListener listener);

    public View getRootView();
    public void initViews();
    public void bindDataToView(T object);
}
