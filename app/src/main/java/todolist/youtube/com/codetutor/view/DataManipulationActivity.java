package todolist.youtube.com.codetutor.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import todolist.youtube.com.codetutor.model.bean.ToDo;

public class DataManipulationActivity extends AppCompatActivity {

    DataManipulatorViewImplementor mvcView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvcView = new DataManipulatorViewImplementor(DataManipulationActivity.this, null, getIntent());
        setContentView(mvcView.getRootView());
        mvcView.initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvcView.bindDataToView();
    }
}
