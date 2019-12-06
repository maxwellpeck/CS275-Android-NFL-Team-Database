package com.example.nflteamapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

//==================================================================================================

public class TeamPagerActivity extends AppCompatActivity {

    private static final String EXTRA_TEAM_ID =
            "com.example.nflteamapp.team_id";

    private ViewPager mViewPager;
    private List<Team> mTeams;

    public static Intent newIntent(Context packageContext, UUID teamId) {
        Intent intent = new Intent(packageContext, TeamPagerActivity.class);
        intent.putExtra(EXTRA_TEAM_ID, teamId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_pager);

        UUID teamId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_TEAM_ID);

        mViewPager = (ViewPager) findViewById(R.id.team_view_pager);
        mTeams = TeamLab.get(this).getTeams();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Team team = mTeams.get(position);
                return TeamFragment.newInstance(team.getId());
            }
            @Override
            public int getCount() {
                return mTeams.size();
            }
        });

        for (int i = 0; i < mTeams.size(); i++) {
            if (mTeams.get(i).getId().equals(teamId)) {
                mViewPager.setCurrentItem(i);
                break; }
        }

    }
}
