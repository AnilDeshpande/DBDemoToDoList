package todolist.youtube.com.codetutor;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;
import todolist.youtube.com.codetutor.db.ToDoListDBAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ToDoAdapter.ListItemClickListener {

    private EditText editTextNewToDoString, editTextPlace;

    private Button buttonAddToDo;

    private RecyclerView recyclerView;

    private ToDoListDBAdapter toDoListDBAdapter;

    private List<ToDo> toDos;

    ToDoAdapter toDoAdapter;


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

        editTextPlace=(EditText)findViewById(R.id.editTextPlace);

        buttonAddToDo=(Button)findViewById(R.id.buttonAddToDo);
        buttonAddToDo.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerListViewToDos);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setNewList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonAddToDo:
                addNewToDo(); break;
            default: break;
        }
    }

    private void setNewList(){
        try{
            toDos = getToDoListString();
            toDoAdapter = new ToDoAdapter(this ,toDos, this);
            recyclerView.setAdapter(toDoAdapter);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
            if(toDos!=null){
                toDos.clear();
                toDoAdapter = new ToDoAdapter(this ,toDos, this);
                recyclerView.setAdapter(toDoAdapter);
            }
        }
    }



    private void addNewToDo(){
        String newToDoString = editTextNewToDoString.getText().toString();
        String newPlace = editTextPlace.getText().toString();
        if(TextUtils.isEmpty(newToDoString) && TextUtils.isEmpty(newPlace)){
            Toast.makeText(MainActivity.this, "Fiends are empty", Toast.LENGTH_SHORT).show();
        }else{
            try{
                toDoListDBAdapter.insert( newToDoString, newPlace);
            }catch (Exception e){
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            setNewList();
            clearEditTexts();
        }

    }

    private List<ToDo> getToDoListString() throws Exception{
        List<ToDo> toDos=null;
        toDos=toDoListDBAdapter.getAllToDos();
        return toDos;
    }

    @Override
    public void onItemClicked(long position) {
        Intent newActivityIntent = new Intent(this, DataManipulationActivity.class);
        newActivityIntent.putExtra("todoId", position);
        startActivity(newActivityIntent);
    }

    private void clearEditTexts(){
        editTextNewToDoString.setText("");
        editTextPlace.setText("");
    }
}
