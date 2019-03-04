package todolist.youtube.com.codetutor.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import todolist.youtube.com.codetutor.MyApplication;
import todolist.youtube.com.codetutor.R;
import todolist.youtube.com.codetutor.controller.MVCController;
import todolist.youtube.com.codetutor.model.bean.ToDo;
import todolist.youtube.com.codetutor.model.MCVModelImplementor;

public class MainActivityViewImplementor implements MVCMainActivityView {

    View rootView;

    MVCController mvcController;

    private MCVModelImplementor mvcModel;


    private EditText editTextNewToDoString, editTextToDoId, editTextNewToDo, editTextPlace;
    private TextView textViewToDos;
    private Button buttonAddToDo, buttonRemoveToDo, buttonModifyToDo;

    public MainActivityViewImplementor (Context context, ViewGroup continer){
        rootView = LayoutInflater.from(context).inflate(R.layout.activity_main,continer);
        mvcModel = new MCVModelImplementor(MyApplication.getToDoListDBAdapter());

        mvcController = new MVCController(mvcModel, this);
    }


    @Override
    public void initViews() {
        editTextNewToDoString=(EditText)rootView.findViewById(R.id.editTextNewToDoString);
        editTextToDoId=(EditText)rootView.findViewById(R.id.editTextToDoId);
        editTextNewToDo=(EditText)rootView.findViewById(R.id.editTextNewToDo);
        editTextPlace=(EditText)rootView.findViewById(R.id.editTextPlace);
        textViewToDos=(TextView)rootView.findViewById(R.id.textViewToDos);


        buttonAddToDo=(Button)rootView.findViewById(R.id.buttonAddToDo);
        buttonRemoveToDo=(Button)rootView.findViewById(R.id.buttonRemoveToDo);
        buttonModifyToDo=(Button)rootView.findViewById(R.id.buttonModifyToDo);


        buttonModifyToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvcController.onModifyButtonClicked(Integer.parseInt(editTextToDoId.getText().toString()), editTextNewToDo.getText().toString());
            }
        });
        buttonRemoveToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvcController.onRemoveBottonClicked(Integer.parseInt(editTextToDoId.getText().toString()));
            }
        });
        buttonAddToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvcController.onAddButtonClicked(editTextNewToDoString.getText().toString(), editTextPlace.getText().toString());
            }
        });
    }

    @Override
    public void bindDataToView() {
        mvcController.onViewLoaded();
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void upDateViewOnRemove(List<ToDo> toDoList) {
        this.showAllToDos(toDoList);
        clearEditTexts();
    }

    @Override
    public void updateViewOnModify(List<ToDo> toDoList) {
        this.showAllToDos(toDoList);
        clearEditTexts();
    }

    @Override
    public void updateViewonAdd(List<ToDo> toDoList) {
        this.showAllToDos(toDoList);
        clearEditTexts();
    }

    @Override
    public void showAllToDos(List<ToDo> toDoList) {
        textViewToDos.setText(toDoList.toString());
    }

    private void clearEditTexts(){
        editTextNewToDo.setText("");
        editTextToDoId.setText("");
        editTextNewToDoString.setText("");
        editTextPlace.setText("");
    }

    @Override
    public void showErrorToast(String errorMessage) {
        Toast.makeText(rootView.getContext(),errorMessage, Toast.LENGTH_LONG).show();
        if(errorMessage.equals("Empty To Do List")){
            clearEditTexts();
            textViewToDos.setText("");
        }
    }
}
