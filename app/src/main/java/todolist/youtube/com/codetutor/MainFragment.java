package todolist.youtube.com.codetutor;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import todolist.youtube.com.codetutor.view.MainViewImplementor;

public class MainFragment extends Fragment {

    MainViewImplementor mvcView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mvcView = new MainViewImplementor(getActivity(),container);
        mvcView.getRootView();
        mvcView.initViews();
        return mvcView.getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
        mvcView.bindDataToView();
    }
}
