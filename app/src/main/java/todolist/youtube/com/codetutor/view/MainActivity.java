package todolist.youtube.com.codetutor.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import todolist.youtube.com.codetutor.MainScreenContract;
import todolist.youtube.com.codetutor.R;
import todolist.youtube.com.codetutor.model.bean.ToDo;
import todolist.youtube.com.codetutor.presenter.MainScreenPresenterImp;
import todolist.youtube.com.codetutor.view.adapters.ToDoAdapter;

public class MainActivity extends AppCompatActivity implements MainScreenContract.View, ToDoAdapter.ListItemClickListener {

    MainScreenContract.Presenter presenter;

    private EditText editTextNewToDoString, editTextPlace;
    private RecyclerView recyclerView;
    private Button buttonAddToDo;

    ToDoAdapter toDoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainScreenPresenterImp(this);

        editTextNewToDoString=(EditText)findViewById(R.id.editTextNewToDoString);
        editTextPlace=(EditText)findViewById(R.id.editTextPlace);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerListViewToDos);
        recyclerView.setLayoutManager(linearLayoutManager);

        buttonAddToDo=(Button)findViewById(R.id.buttonAddToDo);
        buttonAddToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddButtonClicked(editTextNewToDoString.getText().toString(), editTextPlace.getText().toString());
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void showAllToDos(List<ToDo> toDoList) {
        toDoAdapter = new ToDoAdapter(this ,toDoList, this);
        recyclerView.setAdapter(toDoAdapter);
    }

    @Override
    public void updateViewOnAdd(List<ToDo> toDoList) {
        this.showAllToDos(toDoList);
        clearEditTexts();
    }

    @Override
    public void showError(String errorMessage) {
        if(errorMessage.equals("Empty To Do List")){
            clearListView();
        }
        Toast.makeText(this,errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToDataManipulationActivity(long id) {
        Intent intent = new Intent(this, DataManipulationActivity.class);
        intent.putExtra("todoId", id);
        startActivity(intent);
    }

    @Override
    public void setPresenter(MainScreenContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemClicked(long position) {
        presenter.onToDoItemSelected(position);
    }

    private void clearEditTexts(){
        editTextNewToDoString.setText("");
        editTextPlace.setText("");
    }

    private void clearListView(){
        toDoAdapter = new ToDoAdapter(this, new ArrayList<ToDo>(), this);
        recyclerView.setAdapter(toDoAdapter);
    }
}
