package com.example.nflteamapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Date;
import java.util.UUID;

//==================================================================================================

public class TeamFragment extends Fragment {

    private static final String ARG_TEAM_ID = "team_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_CONTACT = 1;

    private Team mTeam;
    private EditText mTitleField;
    private EditText mQbField;
    private EditText mVenueField;
    private EditText mCityField;
    private Button mDateButton;
    private CheckBox mActiveCheckBox;
    private Button mCoachButton;
    private Button mAddButton;

    //----------------------------------------------------------------------------------------------

    public static TeamFragment newInstance(UUID teamId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TEAM_ID, teamId);
        TeamFragment fragment = new TeamFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID teamId = (UUID) getArguments().getSerializable(ARG_TEAM_ID);
        mTeam = TeamLab.get(getActivity()).getTeam(teamId);
        setHasOptionsMenu(true);
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_team, menu);
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_item_delete_team:
                UUID teamId = (UUID) getArguments().getSerializable(ARG_TEAM_ID);
                TeamLab crimeLab = TeamLab.get(getActivity());
                mTeam = crimeLab.getTeam(teamId);
                crimeLab.deleteTeam(mTeam);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onPause() {
        super.onPause();
        TeamLab.get(getActivity())
                .updateTeam(mTeam);
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_team, container, false);

        mTitleField = (EditText) v.findViewById(R.id.team_title);
        try {
            if (mTeam.getTitle().equals("")) {
                mTeam.setTitle("New Untitled NFL Team");
            }
        } catch(Exception e) {
            mTeam.setTitle("New Untitled NFL Team");
        }
        mTitleField.setText(mTeam.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mTeam.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mQbField = (EditText) v.findViewById(R.id.team_qb);
        mQbField.setText(mTeam.getQb());
        mQbField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mTeam.setQb(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mVenueField = (EditText) v.findViewById(R.id.team_venue);
        mVenueField.setText(mTeam.getVenue());
        mVenueField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mTeam.setVenue(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mCityField = (EditText) v.findViewById(R.id.team_city);
        mCityField.setText(mTeam.getCity());
        mCityField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mTeam.setCity(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mDateButton = (Button) v.findViewById(R.id.team_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mTeam.getDate());
                dialog.setTargetFragment(TeamFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            } });

        mActiveCheckBox = (CheckBox)v.findViewById(R.id.team_active);
        mTeam.setActive(true);
        mActiveCheckBox.setChecked(mTeam.isActive());
        mActiveCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                mTeam.setActive(isChecked);
            }
        });

//        mAddButton = (Button) v.findViewById(R.id.team_add);
//        mAddButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_SEND);
//                i.setType("text/plain");
//                i.putExtra(Intent.EXTRA_TEXT, getTeamAdd());
//                i.putExtra(Intent.EXTRA_SUBJECT,
//                        getString(R.string.team_add_coach));
//                i = Intent.createChooser(i, getString(R.string.team_add));
//                startActivity(i);
//            } });

        final Intent pickContact = new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI);
//        mCoachButton = (Button) v.findViewById(R.id.team_coach);
//        mCoachButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivityForResult(pickContact, REQUEST_CONTACT);
//            }
//        });
//        if (mTeam.getCoach() != null) {
//            mCoachButton.setText(mTeam.getCoach());
//        }
//
//        PackageManager packageManager = getActivity().getPackageManager();
//        if (packageManager.resolveActivity(pickContact,
//                PackageManager.MATCH_DEFAULT_ONLY) == null) {
//            mCoachButton.setEnabled(false);
//        }

        return v;
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return; }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mTeam.setDate(date);
            updateDate();
        } else if (requestCode == REQUEST_CONTACT && data != null) { Uri contactUri = data.getData();
//            Specify which fields you want your query to return
//                    values for
            String[] queryFields = new String[] {
                    ContactsContract.Contacts.DISPLAY_NAME
            };
            // Perform your query - the contactUri is like a "where"
            // clause here
            Cursor c = getActivity().getContentResolver()
                    .query(contactUri, queryFields, null, null, null);
            try {
                // Double-check that you actually got results
                if (c.getCount() == 0) {
                    return; }
                // Pull out the first column of the first row of data -
                // that is your coach's name
                c.moveToFirst();
                String coach = c.getString(0);
                mTeam.setCoach(coach);
                mCoachButton.setText(coach);
            } finally {
                c.close(); }
        }
    }

    //----------------------------------------------------------------------------------------------

    private void updateDate() {
        mDateButton.setText(mTeam.getDate().toString());
    }

    private String getTeamAdd() {
        String solvedString = null;
        if (mTeam.isActive()) {
            solvedString = getString(R.string.team_add_active);
        } else {
            solvedString = getString(R.string.team_add_inactive);
        }
        String dateFormat = "EEE, MMM dd";
        String dateString = DateFormat.format(dateFormat,
                mTeam.getDate()).toString();
        String coach = mTeam.getCoach();
        if (coach == null) {
            coach = getString(R.string.team_add_no_coach);
        } else {
            coach = getString(R.string.team_add_coach, coach);
        }
        String add = getString(R.string.team_add,
                mTeam.getTitle(), dateString, solvedString, coach);
        return add;
    }

    //==============================================================================================

}