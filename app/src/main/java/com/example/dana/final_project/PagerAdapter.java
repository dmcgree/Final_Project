package com.example.dana.final_project;

/**
 * Created by Dana McGree, Ashley Stalvig, Ted Jacobi on 4/29/2016.
 * Tab code/layout source http://javapapers.com/android/android-tab-layout-tutorial/
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Welcome tab1 = new Welcome();
                return tab1;
            case 1:
                Weight tab2 = new Weight();
                return tab2;
            case 2:
                Recipes tab3 = new Recipes();
                return tab3;
            case 3:
                StatisticsFragment tab4 = new StatisticsFragment();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
