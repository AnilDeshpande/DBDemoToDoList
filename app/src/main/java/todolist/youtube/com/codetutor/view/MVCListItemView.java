package todolist.youtube.com.codetutor.view;

import android.view.View;

public interface MVCListItemView<T> {

    interface ListItemClickListener{
        void onItemClicked(long position);
    }

    void setListItemClickListener(ListItemClickListener listener);

    View getRootView();
    void initViews();
    void bindDataToView(T object);
}
