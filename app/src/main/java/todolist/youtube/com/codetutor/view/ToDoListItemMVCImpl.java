package todolist.youtube.com.codetutor.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import todolist.youtube.com.codetutor.R;
import todolist.youtube.com.codetutor.model.bean.ToDo;

public class ToDoListItemMVCImpl implements MVCListItemView {

    View rootView;
    ToDo toDo;

    public LinearLayout layoutContainer;
    public TextView textViewId, textViewToDo, textViewPlace;

    ListItemClickListener listItemClickListener;


    public ToDoListItemMVCImpl(LayoutInflater layoutInflater, ViewGroup parent){
        rootView = layoutInflater.inflate(R.layout.todo_row_item, parent, false);
    }

    @Override
    public void setListItemClickListener(ListItemClickListener listener) {
        this.listItemClickListener = listener;
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
    public void bindDataToView(Object object) {
        this.toDo = (ToDo)object;
        textViewId.setText("Id: "+this.toDo.getId());
        textViewToDo.setText("To Do: "+this.toDo.getToDo());
        textViewPlace.setText("Place: "+this.toDo.getPlace());
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
