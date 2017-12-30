package tourlosm.home.automation.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import tourlosm.home.automation.R;
import tourlosm.home.automation.listeners.SwitchesListener;

/**
 * Created by michael on 7/6/2017.
 */

public class SwitchesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.switches_fragment, container, false);
        Switch kitchenSwitch = (Switch) rootView.findViewById(R.id.kitchenLightSwitch);
        kitchenSwitch.setOnCheckedChangeListener(new SwitchesListener("KitchenLight"));
        return rootView;
    }

}
