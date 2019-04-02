package todolist.youtube.com.codetutor.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import todolist.youtube.com.codetutor.MyApplication;
import todolist.youtube.com.codetutor.R;
import todolist.youtube.com.codetutor.controller.MVCDataManipulationController;
import todolist.youtube.com.codetutor.model.MCVModelImplementor;
import todolist.youtube.com.codetutor.model.bean.ToDo;

public class DataManipulatorViewImplementor implements MVCDataManipulatorView{


    View rootView;

    MVCDataManipulationController mvcController;

    private MCVModelImplementor mvcModel;

    TextView textViewToBeModifiedToDoId, textViewToBeModifiedToDo, textViewToBeModifiedToDoPlace;
    Button buttonRemoveToDo, buttonModifyToDo;
    EditText editTextNewToDo;

    ToDo toDo;

    long toDoId;

    public DataManipulatorViewImplementor(Context context, ViewGroup container, Intent intent){
        rootView = LayoutInflater.from(context).inflate(R.layout.acivity_data_manipulate, container);
        mvcModel = new MCVModelImplementor(MyApplication.getToDoListDBAdapter());
        toDoId = intent.getLongExtra("todoId",1);
        mvcModel = new MCVModelImplementor(MyApplication.getToDoListDBAdapter());
        mvcController = new MVCDataManipulationController(mvcModel,this);
    }

    @Override
    public void initViews() {
        textViewToBeModifiedToDoId  = (TextView)rootView.findViewById(R.id.textViewToBeModifiedToDoId);
        textViewToBeModifiedToDo = (TextView)rootView.findViewById(R.id.textViewToBeModifiedToDo);
        textViewToBeModifiedToDoPlace = (TextView)rootView.findViewById(R.id.textViewToBeModifiedToDoPlace);

        buttonRemoveToDo = (Button)rootView.findViewById(R.id.buttonRemoveToDo);
        buttonModifyToDo = (Button)rootView.findViewById(R.id.buttonModifyToDo);

        editTextNewToDo = (EditText)rootView.findViewById(R.id.editTextNewToDo);

        buttonRemoveToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvcController.onRemoveBottonClicked(toDoId);
            }
        });

        buttonModifyToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvcController.onModifyButtonClicked(toDoId,editTextNewToDo.getText().toString());
            }
        });


    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void showSelectedToDo() {
        try{
            toDo = mvcModel.getToDo(toDoId);
            textViewToBeModifiedToDoId.setText("Id: "+ toDo.getId());
            textViewToBeModifiedToDo.setText("ToDo: "+toDo.getToDo());
            textViewToBeModifiedToDoPlace.setText("Place: "+toDo.getPlace());
        }catch (Exception ex){
            Toast.makeText(rootView.getContext(),ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void bindDataToView() {
        mvcController.onViewLoaded();
    }

    @Override
    public void showErrorToast(String errorMessage) {
        Toast.makeText(rootView.getContext(),errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRemovalSuccess() {
        textViewToBeModifiedToDoId.setText("");
        textViewToBeModifiedToDo.setText("");
        textViewToBeModifiedToDoPlace.setText("");
        Toast.makeText(rootView.getContext(),"Successfully removed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showUpdatedValue(ToDo toDo) {
        this.toDo = toDo;
        textViewToBeModifiedToDo.setText(this.toDo.getToDo());
        Toast.makeText(rootView.getContext(),"Successfully updated", Toast.LENGTH_LONG).show();
    }
}
