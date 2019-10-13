package todolist.youtube.com.codetutor;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import todolist.youtube.com.codetutor.bean.ToDo;
import todolist.youtube.com.codetutor.db.ToDoListDBAdapter;

public class DataManipulationActivity extends AppCompatActivity{

    TextView textViewToBeModifiedToDoId, textViewToBeModifiedToDo, textViewToBeModifiedToDoPlace;
    Button buttonRemoveToDo, buttonModifyToDo;
    EditText editTextNewToDo;

    private ToDoListDBAdapter toDoListDBAdapter;

    long toDoId;
    ToDo toDo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_data_manipulate);

        toDoId = getIntent().getLongExtra("todoId",1);


        toDoListDBAdapter=ToDoListDBAdapter.getToDoListDBAdapterInstance(getApplicationContext());
        try{
            toDo = toDoListDBAdapter.getToDo(toDoId);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


        textViewToBeModifiedToDoId  = (TextView)findViewById(R.id.textViewToBeModifiedToDoId);
        textViewToBeModifiedToDo = (TextView)findViewById(R.id.textViewToBeModifiedToDo);
        textViewToBeModifiedToDoPlace = (TextView)findViewById(R.id.textViewToBeModifiedToDoPlace);

        buttonRemoveToDo = (Button)findViewById(R.id.buttonRemoveToDo);
        buttonModifyToDo = (Button)findViewById(R.id.buttonModifyToDo);

        editTextNewToDo = (EditText)findViewById(R.id.editTextNewToDo);

        showSelectedToDo(getToDo());

        buttonRemoveToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeToDo();
            }
        });

        buttonModifyToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyToDo();
            }
        });
    }

    private ToDo getToDo(){
        try{
           return toDo = toDoListDBAdapter.getToDo(toDoId);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void removeToDo(){
        try{
            toDoListDBAdapter.delete((int)toDoId);
            updateViewOnRemove();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void modifyToDo(){
        String newToDO=editTextNewToDo.getText().toString();
        try{
            toDoListDBAdapter.modify((int) toDo.getId(),newToDO);
            toDo = toDoListDBAdapter.getToDo(toDo.getId());
            showSelectedToDo(toDo);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void showSelectedToDo(ToDo toDo) {
        try{
            textViewToBeModifiedToDoId.setText("Id: "+ toDo.getId());
            textViewToBeModifiedToDo.setText("ToDo: "+toDo.getToDo());
            textViewToBeModifiedToDoPlace.setText("Place: "+toDo.getPlace());
        }catch (Exception ex){
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void updateViewOnRemove() {
        textViewToBeModifiedToDoId.setText("");
        textViewToBeModifiedToDo.setText("");
        textViewToBeModifiedToDoPlace.setText("");
        Toast.makeText(this,"Successfully removed", Toast.LENGTH_LONG).show();
    }
}
