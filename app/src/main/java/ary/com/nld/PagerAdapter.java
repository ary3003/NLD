package ary.com.nld;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Ary on 20/01/2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    // Integer to define number of tabs

    int tabCount;

    // Constructor to the class
    public PagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                ReceivingFragment tab1 = new ReceivingFragment();
                return tab1;
            case 1:
                BillFragment tab2 = new BillFragment();
                return tab2;
            case 2:
                ProductFragment tab3 = new ProductFragment();
                return tab3;

            case 4:
                ExpiryFragment tab4 = new ExpiryFragment();
                return tab4;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}

