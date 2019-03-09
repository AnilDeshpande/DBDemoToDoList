package todolist.youtube.com.codetutor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import todolist.youtube.com.codetutor.view.MVCMainActivityViewImplementor;

public class MainActivity extends AppCompatActivity {

    MVCMainActivityViewImplementor mainActivityViewImplementor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityViewImplementor = new MVCMainActivityViewImplementor(this,null);
        setContentView(mainActivityViewImplementor.getRootView());
        mainActivityViewImplementor.initViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mainActivityViewImplementor.bindDataToView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainActivityViewImplementor.unBindDataFromView();
    }
}
