package todolist.youtube.com.codetutor.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import todolist.youtube.com.codetutor.MyApplication;
import todolist.youtube.com.codetutor.R;
import todolist.youtube.com.codetutor.controller.FragmentActionListener;
import todolist.youtube.com.codetutor.controller.MVCMainViewController;
import todolist.youtube.com.codetutor.model.bean.ToDo;
import todolist.youtube.com.codetutor.model.MCVModelImplementor;
import todolist.youtube.com.codetutor.view.adapters.ToDoAdapter;

public class MainViewImplementor implements MVCMainView, ToDoAdapter.ListItemClickListener {

    View rootView;

    MVCMainViewController mvcMainViewController;

    private MCVModelImplementor mvcModel;


    private EditText editTextNewToDoString, editTextPlace;
    private RecyclerView recyclerView;
    private Button buttonAddToDo;

    ToDoAdapter toDoAdapter;



    public MainViewImplementor(Context context, ViewGroup continer){
        rootView = LayoutInflater.from(context).inflate(R.layout.activity_fragment,continer,false);
        mvcModel = new MCVModelImplementor(MyApplication.getToDoListDBAdapter());
        mvcMainViewController = new MVCMainViewController(mvcModel, this);
    }

    @Override
    public void initViews() {
        editTextNewToDoString=(EditText)rootView.findViewById(R.id.editTextNewToDoString);
        editTextPlace=(EditText)rootView.findViewById(R.id.editTextPlace);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerListViewToDos);
        recyclerView.setLayoutManager(linearLayoutManager);

        buttonAddToDo=(Button)rootView.findViewById(R.id.buttonAddToDo);
        buttonAddToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvcMainViewController.onAddButtonClicked(editTextNewToDoString.getText().toString(), editTextPlace.getText().toString());
            }
        });
    }

    @Override
    public void bindDataToView() {
        mvcMainViewController.bindDatatoView();
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void updateViewonAdd(List<ToDo> toDoList) {
        this.showAllToDos(toDoList);
        clearEditTexts();
    }

    @Override
    public void showAllToDos(List<ToDo> toDoList) {
        toDoAdapter = new ToDoAdapter(rootView.getContext(),toDoList, this);
        recyclerView.setAdapter(toDoAdapter);
    }

    private void clearEditTexts(){
        editTextNewToDoString.setText("");
        editTextPlace.setText("");
    }

    @Override
    public void showErrorToast(String errorMessage) {
        Toast.makeText(rootView.getContext(),errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClicked(long position) {
        mvcMainViewController.onToDoItemSelected(position);
    }
}
