package com.example.gymtracker.ui.dashboard;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.gymtracker.ui.dashboard.tabfrag1;
import com.example.gymtracker.ui.dashboard.tabfrag2;
import com.example.gymtracker.ui.dashboard.tabfrag3;


public class TabPagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public TabPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tabfrag1 tab1 = new tabfrag1();
                return tab1;
            case 1:
                tabfrag2 tab2 = new tabfrag2();
                return tab2;
            case 2:
                tabfrag3 tab3 = new tabfrag3();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

