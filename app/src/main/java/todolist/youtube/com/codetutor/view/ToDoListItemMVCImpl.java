package todolist.youtube.com.codetutor.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import todolist.youtube.com.codetutor.R;
import todolist.youtube.com.codetutor.model.bean.ToDo;

public class ToDoListItemMVCImpl implements MVCView {

    View rootView;
    ToDo toDo;

    public LinearLayout layoutContainer;
    public TextView textViewId, textViewToDo, textViewPlace;

    ListItemClickListener listItemClickListener;

    public interface ListItemClickListener{
        void onItemClicked(long position);
    }

    public void setListItemClickListener(ListItemClickListener listItemClickListener){
        this.listItemClickListener = listItemClickListener;
    }


    public ToDoListItemMVCImpl(LayoutInflater layoutInflater, ViewGroup parent){
        rootView = layoutInflater.inflate(R.layout.todo_row_item, parent, false);

    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initViews() {
        layoutContainer = (LinearLayout)rootView.findViewById(R.id.layoutContainer);
        textViewId = (TextView)rootView.findViewById(R.id.textViewId);
        textViewToDo = (TextView)rootView.findViewById(R.id.textViewToDo);
        textViewPlace = (TextView)rootView.findViewById(R.id.textViewPlace);
    }

    @Override
    public void bindDataToView() {
        textViewId.setText("Id: "+toDo.getId());
        textViewToDo.setText("To Do: "+toDo.getToDo());
        textViewPlace.setText("Place: "+toDo.getPlace());
        layoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listItemClickListener!=null){
                    listItemClickListener.onItemClicked(toDo.getId());
                }
            }
        });
    }
}
