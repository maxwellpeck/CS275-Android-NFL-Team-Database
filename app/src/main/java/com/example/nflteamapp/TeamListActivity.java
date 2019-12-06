package com.example.nflteamapp;

import androidx.fragment.app.Fragment;

public class TeamListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new TeamListFragment();
    }
}
