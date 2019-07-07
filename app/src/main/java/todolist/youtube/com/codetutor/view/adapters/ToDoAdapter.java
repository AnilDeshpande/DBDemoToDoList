package todolist.youtube.com.codetutor.view.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewViewHolder> implements ToDoListItemMVCImpl.ListItemClickListener {

    private static final String TAG = ToDoAdapter.class.getSimpleName();

    private Context context;
    private List<ToDo> todos;
    private ToDoListItemMVCImpl.ListItemClickListener listItemClickListener;

    public ToDoAdapter(Context context, List<ToDo> toDos, ToDoListItemMVCImpl.ListItemClickListener listItemClickListener){
        this.context = context;
        this.todos = toDos;
        this.listItemClickListener = listItemClickListener;
    }

    @Override
    public ToDoAdapter.ToDoViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ToDoListItemMVCImpl toDoListItemMVC = new ToDoListItemMVCImpl((LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE ),parent);
        toDoListItemMVC.setListItemClickListener(this);
        return new ToDoViewViewHolder(toDoListItemMVC);
    }

    @Override
    public void onBindViewHolder(ToDoAdapter.ToDoViewViewHolder holder, final int position) {
        final ToDo toDo = todos.get(position);
        holder.listItemMVC.bindDataToView(toDo);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.todo_row_item;
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    @Override
    public void onItemClicked(long position) {
        listItemClickListener.onItemClicked(position);
    }

    class ToDoViewViewHolder extends RecyclerView.ViewHolder{

        private ToDoListItemMVCImpl listItemMVC;

        public ToDoViewViewHolder(ToDoListItemMVCImpl view){
            super(view.getRootView());
            listItemMVC = view;
        }


    }
}
