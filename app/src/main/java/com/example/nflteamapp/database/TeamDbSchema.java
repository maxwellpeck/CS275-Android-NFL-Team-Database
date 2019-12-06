package com.example.nflteamapp.database;

public class TeamDbSchema {
    public static final class TeamTable {
        public static final String NAME = "teams";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String QB = "qb";
            public static final String VENUE = "venue";
            public static final String CITY = "city";
            public static final String DATE = "date";
            public static final String ACTIVE = "active";
            public static final String COACH = "coach";
        }
    }
}
