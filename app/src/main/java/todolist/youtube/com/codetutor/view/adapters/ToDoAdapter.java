package todolist.youtube.com.codetutor.view.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import todolist.youtube.com.codetutor.R;
import todolist.youtube.com.codetutor.model.bean.ToDo;
import todolist.youtube.com.codetutor.view.MVCDataManipulatorView;
import todolist.youtube.com.codetutor.view.MVCView;
import todolist.youtube.com.codetutor.view.ToDoListItemMVCImpl;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewViewHolder> {

    private Context context;
    private List<ToDo> todos;

    public ToDoAdapter(Context context, List<ToDo> toDos){
        this.context = context;
        this.todos = toDos;
    }

    @Override
    public ToDoAdapter.ToDoViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ToDoListItemMVCImpl toDoListItemMVC = new ToDoListItemMVCImpl()

        return new ToDoViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ToDoAdapter.ToDoViewViewHolder holder, final int position) {
        final ToDo toDo = todos.get(position);
        holder.textViewId.setText("Id: "+toDo.getId());
        holder.textViewToDo.setText("To Do: "+toDo.getToDo());
        holder.textViewPlace.setText("Place: "+toDo.getPlace());
        holder.layoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listItemClickListener!=null){
                    listItemClickListener.onItemClicked(toDo.getId());
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.todo_row_item;
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    class ToDoViewViewHolder extends RecyclerView.ViewHolder{

        private ToDoListItemMVCImpl listItemMVC;

        public ToDoViewViewHolder(ToDoListItemMVCImpl view){
            super(view.getRootView());
            listItemMVC = view;
        }


    }
}
