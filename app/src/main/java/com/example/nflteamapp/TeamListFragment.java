package com.example.nflteamapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//==================================================================================================

public class TeamListFragment extends Fragment {

//    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView mTeamRecyclerView;
    private TeamAdapter mAdapter;
//    private boolean mSubtitleVisible;

    private boolean darkTheme = false;

    //----------------------------------------------------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_list, container, false);
        mTeamRecyclerView = (RecyclerView) view
                .findViewById(R.id.team_recycler_view);
        mTeamRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
//            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();

        return view;
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_team_list, menu);

//        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
//        if (mSubtitleVisible) {
//            subtitleItem.setTitle(R.string.hide_subtitle);
//        } else {
//            subtitleItem.setTitle(R.string.show_subtitle);
//        }
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_team:
                Team team = new Team();
                TeamLab.get(getActivity()).addTeam(team);
                Intent intent = TeamPagerActivity
                        .newIntent(getActivity(), team.getId());
                startActivity(intent);
                return true;
//            case R.id.show_subtitle:
//                mSubtitleVisible = !mSubtitleVisible;
//                getActivity().invalidateOptionsMenu();
//                updateSubtitle();
//                return true;
            case R.id.change_theme:
                if (!darkTheme) {
                    getView().setBackgroundColor(Color.GRAY);
                    Toast.makeText(getActivity(), "Dark Mode Enabled",
                            Toast.LENGTH_LONG).show();
                    darkTheme = true;
                } else {
                    getView().setBackgroundColor(Color.WHITE);
                    Toast.makeText(getActivity(), "Dark Mode Disabled",
                            Toast.LENGTH_LONG).show();
                    darkTheme = false;
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //----------------------------------------------------------------------------------------------

//    private void updateSubtitle() {
//        TeamLab teamLab = TeamLab.get(getActivity());
//        int teamCount = teamLab.getTeams().size();
//        String subtitle = getString(R.string.subtitle_format, teamCount);

//        if (!mSubtitleVisible) {
//            subtitle = null;
//        }

//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.getSupportActionBar().setSubtitle(subtitle);
//    }

    //----------------------------------------------------------------------------------------------


    private void updateUI() {
        TeamLab teamLab = TeamLab.get(getActivity());
        List<Team> teams = teamLab.getTeams();

        if (mAdapter == null) {
            mAdapter = new TeamAdapter(teams);
            mTeamRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setTeams(teams);
            mAdapter.notifyDataSetChanged();
        }

//        updateSubtitle();
    }

    //==============================================================================================

    private class TeamHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Team mTeam;
        private TextView mTitleTextView;
        //        private TextView mQbTextView;
//        private TextView mDateTextView;
        private TextView mCityTextView;
        private ImageView mActiveImageView;

        //------------------------------------------------------------------------------------------

        public TeamHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_team, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.team_title);
//            mQbTextView = (TextView) itemView.findViewById(R.id.team_qb;
//            mDateTextView = (TextView) itemView.findViewById(R.id.team_date);
            mCityTextView = (TextView) itemView.findViewById(R.id.team_city);
            mActiveImageView = (ImageView) itemView.findViewById(R.id.team_active);
        }

        //------------------------------------------------------------------------------------------

        public void bind(Team team) {
            mTeam = team;
            mTitleTextView.setText(mTeam.getTitle());
//            mQbTextView.setText(mTeam.getQb());
//            mDateTextView.setText(mTeam.getDate().toString());
            String locationText = "Location: ";
            try {
                locationText = locationText + mTeam.getCity().toString();
            } catch (Exception e) {
                locationText = "Location: Not Set";
            }
            mCityTextView.setText(locationText);
            mActiveImageView.setVisibility(team.isActive() ? View.VISIBLE : View.GONE);
        }

        //------------------------------------------------------------------------------------------

        @Override
        public void onClick(View view) {
            Intent intent = TeamPagerActivity.newIntent(getActivity(), mTeam.getId());
            startActivity(intent);
        }
    }

    //==============================================================================================

    private class TeamAdapter extends RecyclerView.Adapter<TeamHolder> {
        private List<Team> mTeams;
        public TeamAdapter(List<Team> teams) {
            mTeams = teams;
        }

        //------------------------------------------------------------------------------------------

        @Override
        public TeamHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TeamHolder(layoutInflater, parent);
        }

        //------------------------------------------------------------------------------------------

        @Override
        public void onBindViewHolder(TeamHolder holder, int position) {
            Team team = mTeams.get(position);
            holder.bind(team);
        }

        //------------------------------------------------------------------------------------------

        @Override
        public int getItemCount() {
            return mTeams.size();
        }

        //------------------------------------------------------------------------------------------

        public void setTeams(List<Team> teams) {
            mTeams = teams;
        }
    }
}
