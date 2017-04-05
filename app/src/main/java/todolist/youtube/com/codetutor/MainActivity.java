package todolist.youtube.com.codetutor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;
import todolist.youtube.com.codetutor.db.ToDoListDBAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextNewToDoString, editTextToDoId, editTextNewToDo;
    private TextView textViewToDos;
    private Button buttonAddToDo, buttonRemoveToDo, buttonModifyToDo;

    private ToDoListDBAdapter toDoListDBAdapter;

    List<ToDo> toDoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toDoListDBAdapter=ToDoListDBAdapter.getToDoListDBAdapterInstance(this);



        editTextNewToDoString=(EditText)findViewById(R.id.editTextNewToDoString);
        editTextToDoId=(EditText)findViewById(R.id.editTextToDoId);
        editTextNewToDo=(EditText)findViewById(R.id.editTextNewToDo);

        textViewToDos=(TextView)findViewById(R.id.textViewToDos);

        buttonAddToDo=(Button)findViewById(R.id.buttonAddToDo);
        buttonRemoveToDo=(Button)findViewById(R.id.buttonRemoveToDo);
        buttonModifyToDo=(Button)findViewById(R.id.buttonModifyToDo);

        buttonModifyToDo.setOnClickListener(this);
        buttonRemoveToDo.setOnClickListener(this);
        buttonAddToDo.setOnClickListener(this);
        setNewList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonAddToDo: addNewToDo(); break;
            case R.id.buttonRemoveToDo: removeToDo(); break;
            case R.id.buttonModifyToDo: modifyToDo(); break;
            default: break;
        }
    }

    private void setNewList(){
        textViewToDos.setText(getToDoListString());
    }

    private void addNewToDo(){
        toDoListDBAdapter.insert(editTextNewToDoString.getText().toString());
        setNewList();
    }

    private void removeToDo(){
        toDoListDBAdapter.delete(Integer.parseInt(editTextToDoId.getText().toString()));
        setNewList();
    }

    private void modifyToDo(){
        int id=Integer.parseInt(editTextToDoId.getText().toString());
        String newToDO=editTextNewToDo.getText().toString();
        toDoListDBAdapter.modify(id,newToDO);
        setNewList();
    }



    private String getToDoListString(){
        toDoList=toDoListDBAdapter.getAllToDos();
        StringBuilder stringBuilder=new StringBuilder("");
        if (toDoList.size()<=0){
            stringBuilder.append("No Item present");
        }else{
            for(ToDo toDo:toDoList){
                stringBuilder.append(toDo.getId()+", "+toDo.getToDo()+"\n");
            }
        }
        return stringBuilder.toString();
    }
}
