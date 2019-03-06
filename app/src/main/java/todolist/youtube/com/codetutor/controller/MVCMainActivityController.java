package todolist.youtube.com.codetutor.controller;

import android.os.Bundle;

import todolist.youtube.com.codetutor.view.MVCMainActivityViewImplementor;

public class MVCMainActivityController implements FragmentActionListener{

    MVCMainActivityViewImplementor activityViewImplementor;

    public MVCMainActivityController(MVCMainActivityViewImplementor activityViewImplementor){
        this.activityViewImplementor = activityViewImplementor;
    }

    public void onViewLoaded(){
        this.activityViewImplementor.showMainScreen();
    }

    @Override
    public void onActionPerformed(ACTION_PERFORMED actionPerformed, Bundle bundle) {
        switch (actionPerformed){
            case ON_ITEM_SELECTED: this.activityViewImplementor.showDataManipulationScreen(bundle.getLong("todoId")); break;
        }
    }
}
