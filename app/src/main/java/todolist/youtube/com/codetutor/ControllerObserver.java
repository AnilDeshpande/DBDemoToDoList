package todolist.youtube.com.codetutor;

import android.os.Bundle;

import java.util.HashSet;
import java.util.Set;

import todolist.youtube.com.codetutor.controller.FragmentActionListener;

public class ControllerObserver {

    Set<FragmentActionListener> fragmentActionListenerList;

    private static ControllerObserver instance;

    private ControllerObserver(){
        fragmentActionListenerList = new HashSet<>();
    }

    public static ControllerObserver getInstance(){
        if(instance==null){
            instance = new ControllerObserver();
        }
        return instance;
    }


    public void registerFragmentActionListener(FragmentActionListener listener){
        fragmentActionListenerList.add(listener);
    }

    public void unregisterFragmentActionListener(FragmentActionListener listener){
        fragmentActionListenerList.remove(listener);
    }

    public void notify(FragmentActionListener.ACTION_PERFORMED action_performed, Bundle bundle){
        for(FragmentActionListener listener: fragmentActionListenerList){
            listener.onActionPerformed(action_performed,bundle);
        }
    }

}
