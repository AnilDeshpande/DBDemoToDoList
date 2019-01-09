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
import todolist.youtube.com.codetutor.view.MainActivityViewImplementor;

public class MainActivity extends AppCompatActivity {

    MainActivityViewImplementor mvcView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mvcView = new MainActivityViewImplementor(MainActivity.this,null);
        setContentView(mvcView.getRootView());
        mvcView.initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvcView.bindDataToView();
    }
}
