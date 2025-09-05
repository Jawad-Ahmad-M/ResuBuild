package com.resumebuilder.app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.resumebuilder.app.fragments.DashboardFragment;
import com.resumebuilder.app.fragments.atsScoreFragment;
import com.resumebuilder.app.fragments.DetailsFragment;
import com.resumebuilder.app.fragments.profileFragment;

public class MainPageAdapter extends FragmentStateAdapter {

    private final Fragment[] fragmentCache = new Fragment[4];

    public MainPageAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (fragmentCache[position] == null) {
            switch (position) {
                case 0:
                    fragmentCache[0] = new DetailsFragment();
                    break;
                case 1:
                    fragmentCache[1] = new DashboardFragment();
                    break;
                case 2:
                    fragmentCache[2] = new atsScoreFragment();
                    break;
                case 3:
                    fragmentCache[3] = new profileFragment();
                    break;
            }
        }
        return fragmentCache[position];
    }

    @Override
    public int getItemCount() {
        return 4;
    }

}