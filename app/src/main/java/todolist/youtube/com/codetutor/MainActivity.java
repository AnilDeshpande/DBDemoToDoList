package todolist.youtube.com.codetutor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import todolist.youtube.com.codetutor.view.MVCView;
import todolist.youtube.com.codetutor.view.MVCViewFactory;
import todolist.youtube.com.codetutor.view.MainActivityViewImplementor;

public class MainActivity extends AppCompatActivity {

    MVCView mvcView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mvcView = MVCViewFactory.getMVCView(MVCViewFactory.VIEW_TYPE.MAIN_VIEW_TYPE, MainActivity.this, null, null);
        setContentView(mvcView.getRootView());
        mvcView.initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvcView.bindDataToView();
    }
}
