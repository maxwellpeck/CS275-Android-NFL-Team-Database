package com.example.nflteamapp.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.nflteamapp.Team;
import com.example.nflteamapp.database.TeamDbSchema.TeamTable;

import java.util.Date;
import java.util.UUID;

//==================================================================================================

public class TeamCursorWrapper extends CursorWrapper {
    public TeamCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    //----------------------------------------------------------------------------------------------

    public Team getTeam() {
        String uuidString = getString(getColumnIndex(TeamTable.Cols.UUID));
        String title = getString(getColumnIndex(TeamTable.Cols.TITLE));
        String qb = getString(getColumnIndex(TeamTable.Cols.QB));
        String venue = getString(getColumnIndex(TeamTable.Cols.VENUE));
        String city = getString(getColumnIndex(TeamTable.Cols.CITY));
        long date = getLong(getColumnIndex(TeamTable.Cols.DATE));
        int isActive = getInt(getColumnIndex(TeamTable.Cols.ACTIVE));
        String coach = getString(getColumnIndex(TeamTable.Cols.COACH));

        Team team = new Team(UUID.fromString(uuidString));
        team.setTitle(title);
        team.setQb(qb);
        team.setVenue(venue);
        team.setCity(city);
        team.setDate(new Date(date));
        team.setActive(isActive != 0);
        team.setCoach(coach);

        return team;
    }
}
