package todolist.youtube.com.codetutor.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import todolist.youtube.com.codetutor.R;
import todolist.youtube.com.codetutor.bean.ToDo;
import todolist.youtube.com.codetutor.viewmodel.CommonViewModel;
import todolist.youtube.com.codetutor.viewmodel.CommonViewModelImplementor;

public class DataManipulationActivity extends AppCompatActivity{

    private TextView textViewToBeModifiedToDoId, textViewToBeModifiedToDo, textViewToBeModifiedToDoPlace;
    private Button buttonRemoveToDo, buttonModifyToDo;
    private EditText editTextNewToDo;

    private long toDoId;

    private CommonViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_data_manipulate);

        textViewToBeModifiedToDoId  = (TextView)findViewById(R.id.textViewToBeModifiedToDoId);
        textViewToBeModifiedToDo = (TextView)findViewById(R.id.textViewToBeModifiedToDo);
        textViewToBeModifiedToDoPlace = (TextView)findViewById(R.id.textViewToBeModifiedToDoPlace);

        buttonRemoveToDo = (Button)findViewById(R.id.buttonRemoveToDo);
        buttonModifyToDo = (Button)findViewById(R.id.buttonModifyToDo);
        editTextNewToDo = (EditText)findViewById(R.id.editTextNewToDo);

        toDoId = getIntent().getLongExtra("todoId",1);
        viewModel = ViewModelProviders.of(this).get(CommonViewModelImplementor.class);

        viewModel.getToDo(toDoId).observe(this, new Observer<ToDo>() {
            @Override
            public void onChanged(ToDo toDo) {
                if(toDo==null){
                    updateViewOnRemove();
                }else{
                    showSelectedToDo(toDo);
                }
            }
        });

        viewModel.getErrorStatus().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(DataManipulationActivity.this,s, Toast.LENGTH_SHORT).show();
            }
        });


        buttonRemoveToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.removeToDo(toDoId);
            }
        });

        buttonModifyToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.modifyToDo(toDoId, editTextNewToDo.getText().toString());
            }
        });
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
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.getToDoList().removeObservers(this);
        viewModel.getErrorStatus().removeObservers(this);
    }
}
