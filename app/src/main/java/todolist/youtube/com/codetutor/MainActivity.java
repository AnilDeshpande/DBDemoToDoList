package todolist.youtube.com.codetutor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import todolist.youtube.com.codetutor.controller.MVCController;
import todolist.youtube.com.codetutor.model.bean.ToDo;
import todolist.youtube.com.codetutor.model.db.MCVModelImplementor;
import todolist.youtube.com.codetutor.model.db.ToDoListDBAdapter;
import todolist.youtube.com.codetutor.view.MVCView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MVCView {

    private EditText editTextNewToDoString, editTextToDoId, editTextNewToDo, editTextPlace;
    private TextView textViewToDos;
    private Button buttonAddToDo, buttonRemoveToDo, buttonModifyToDo;

    MVCController mvcController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mvcController = new MVCController(new MCVModelImplementor(((MyApplication)getApplication()).getToDoListDBAdapter()), this);

        editTextNewToDoString=(EditText)findViewById(R.id.editTextNewToDoString);
        editTextToDoId=(EditText)findViewById(R.id.editTextToDoId);
        editTextNewToDo=(EditText)findViewById(R.id.editTextNewToDo);
        editTextPlace=(EditText)findViewById(R.id.editTextPlace);
        textViewToDos=(TextView)findViewById(R.id.textViewToDos);


        buttonAddToDo=(Button)findViewById(R.id.buttonAddToDo);
        buttonRemoveToDo=(Button)findViewById(R.id.buttonRemoveToDo);
        buttonModifyToDo=(Button)findViewById(R.id.buttonModifyToDo);

        buttonModifyToDo.setOnClickListener(this);
        buttonRemoveToDo.setOnClickListener(this);
        buttonAddToDo.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mvcController.onScreenLoad();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonAddToDo: mvcController.onAddButtonClicked(editTextNewToDoString.getText().toString(), editTextPlace.getText().toString()); break;
            case R.id.buttonRemoveToDo: mvcController.onRemoveBottonClicked(Integer.parseInt(editTextToDoId.getText().toString())); break;
            case R.id.buttonModifyToDo: mvcController.onModifyButtonClicked(Integer.parseInt(editTextToDoId.getText().toString()), editTextNewToDo.getText().toString()); break;
            default: break;
        }
    }


    @Override
    public void updateViewonAdd(List<ToDo> toDoList) {
        textViewToDos.setText(toDoList.toString());
        clearEditTexts();


    }

    @Override
    public void upDateViewOnRemove(List<ToDo> toDoList) {
        textViewToDos.setText(toDoList.toString());
        clearEditTexts();
    }



    @Override
    public void updateViewOnModify(List<ToDo> toDoList) {
        textViewToDos.setText(toDoList.toString());
        clearEditTexts();
    }

    @Override
    public void showAllToDos(List<ToDo> toDoList) {
        textViewToDos.setText(toDoList.toString());
        clearEditTexts();
    }

    private void clearEditTexts(){
        editTextNewToDo.setText("");
        editTextToDoId.setText("");
        editTextNewToDo.setText("");
        editTextPlace.setText("");
    }
}
