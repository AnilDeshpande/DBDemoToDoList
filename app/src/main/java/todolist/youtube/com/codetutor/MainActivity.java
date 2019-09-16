package todolist.youtube.com.codetutor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;
import todolist.youtube.com.codetutor.db.ToDoListDBAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextNewToDoString, editTextNewToDo, editTextPlace;

    private Button buttonAddToDo;

    private ToDoListDBAdapter toDoListDBAdapter;

    private List<ToDo> toDos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toDoListDBAdapter=ToDoListDBAdapter.getToDoListDBAdapterInstance(getApplicationContext());
        try {
            toDos=toDoListDBAdapter.getAllToDos();
        }catch (Exception e){
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        editTextNewToDoString=(EditText)findViewById(R.id.editTextNewToDoString);

        editTextNewToDo=(EditText)findViewById(R.id.editTextNewToDo);
        editTextPlace=(EditText)findViewById(R.id.editTextPlace);

        buttonAddToDo=(Button)findViewById(R.id.buttonAddToDo);
        buttonAddToDo.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
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
        try{
            toDoListDBAdapter.insert(editTextNewToDoString.getText().toString(), editTextPlace.getText().toString());
        }catch (Exception e){
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        setNewList();
    }

    private void removeToDo(){
        try{
            toDoListDBAdapter.delete(Integer.parseInt(editTextToDoId.getText().toString()));
            setNewList();
        }catch (Exception e){
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void modifyToDo(){
        int id=Integer.parseInt(editTextToDoId.getText().toString());
        String newToDO=editTextNewToDo.getText().toString();
        try{
            toDoListDBAdapter.modify(id,newToDO);
        }catch (Exception e){
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        setNewList();
    }



    private String getToDoListString(){
        String message = null;
        try{
            toDos=toDoListDBAdapter.getAllToDos();
            if(toDos!=null && toDos.size()>0){
                StringBuilder stringBuilder=new StringBuilder("");
                for(ToDo toDo:toDos){
                    stringBuilder.append(toDo.getId()+", "+toDo.getToDo()+", "+toDo.getPlace()+"\n");
                }
                message =  stringBuilder.toString();
            }
        }catch (Exception e){
            message = e.getMessage();
        }
        return message;
    }
}
