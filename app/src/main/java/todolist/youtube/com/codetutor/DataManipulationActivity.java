package todolist.youtube.com.codetutor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import todolist.youtube.com.codetutor.view.MVCView;
import todolist.youtube.com.codetutor.view.MVCViewFactory;

public class DataManipulationActivity extends AppCompatActivity {

    MVCView mvcView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvcView = MVCViewFactory.getMVCView(MVCViewFactory.VIEW_TYPE.MANIPULATION_VIEW_TYPE, DataManipulationActivity.this, null, getIntent());
        setContentView(mvcView.getRootView());
        mvcView.initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvcView.bindDataToView();
    }
}
