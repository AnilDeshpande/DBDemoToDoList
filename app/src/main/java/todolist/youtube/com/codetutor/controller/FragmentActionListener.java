package todolist.youtube.com.codetutor.controller;

import android.os.Bundle;

public interface FragmentActionListener {
    enum ACTION_PERFORMED{
        ON_ITEM_SELECTED
    }
    public void onActionPerformed(ACTION_PERFORMED  actionPerformed, Bundle bundle);
}
