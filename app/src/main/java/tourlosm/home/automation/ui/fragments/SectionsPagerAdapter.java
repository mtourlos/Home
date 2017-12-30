package tourlosm.home.automation.ui.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tourlosm.home.automation.R;

/**
 * Created by michael on 17/10/2017.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    Context context;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new GeneralFragment();
            case 1:
                return new SwitchesFragment();
            case 2:
                return new GarageFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.general_section);
            case 1:
                return context.getString(R.string.switches_section);
            case 2:
                return context.getString(R.string.garage_section);
        }
        return null;
    }
}
