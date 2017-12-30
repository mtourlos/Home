package tourlosm.home.automation.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tourlosm.home.automation.R;

/**
 * Created by michael on 7/6/2017.
 */

public class GeneralFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.general_fragment, container, false);
        return rootView;
    }
}
