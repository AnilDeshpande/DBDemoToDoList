package todolist.youtube.com.codetutor.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import todolist.youtube.com.codetutor.DataManipulationFragment;
import todolist.youtube.com.codetutor.MainFragment;
import todolist.youtube.com.codetutor.R;
import todolist.youtube.com.codetutor.controller.MVCMainActivityController;

public class MVCMainActivityViewImplementor implements MainActivityView {

    View rootView;

    MVCMainActivityController mvcMainActivityController;

    FrameLayout fragmentContainer;

    Activity activity;

    public MVCMainActivityViewImplementor(Activity activity, ViewGroup viewGroup){
        this.activity = activity;
        rootView = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.main_activity,viewGroup);
        mvcMainActivityController = new MVCMainActivityController(this);
    }

    @Override
    public void bindDataToView() {
        mvcMainActivityController.onViewLoaded();
    }

    @Override
    public void initViews() {
        fragmentContainer = (FrameLayout)rootView.findViewById(R.id.fragmentContainer);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void showMainScreen() {
        FragmentManager fragmentManager = activity.getFragmentManager();
        MainFragment mainFragment = new MainFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer,mainFragment,"maincontainer");
        fragmentTransaction.commit();
    }

    @Override
    public void showDataManipulationScreen(long toDoId) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        DataManipulationFragment dataManipulationFragment = new DataManipulationFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,dataManipulationFragment,"maincontainer");
        fragmentTransaction.commit();
        fragmentTransaction.addToBackStack(null);
    }
}
