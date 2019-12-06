package com.example.nflteamapp;

//import java.util.Date;
import java.util.Date;
import java.util.UUID;

//==================================================================================================

public class Team {
    private UUID mId;
    private String mTitle;
    private String mQb;
    private String mVenue;
    private String mCity;
    private Date mDate;
    private boolean mActive;
    private String mCoach;

    //----------------------------------------------------------------------------------------------

    public Team() {
        this(UUID.randomUUID());
    }

    //----------------------------------------------------------------------------------------------

    public Team(UUID id) {
        mId = id;
        mDate = new Date();
    }

    //----------------------------------------------------------------------------------------------

    public UUID getId() {
        return mId;
    }

    //----------------------------------------------------------------------------------------------

    public String getTitle() {
        return mTitle;
    }

    //----------------------------------------------------------------------------------------------

    public void setTitle(String title) {
        mTitle = title;
    }

    //----------------------------------------------------------------------------------------------

    public String getQb() {
        return mQb;
    }

    //----------------------------------------------------------------------------------------------

    public void setQb(String qb) {
        mQb = qb;
    }

    //----------------------------------------------------------------------------------------------

    public String getVenue() {
        return mVenue;
    }

    //----------------------------------------------------------------------------------------------

    public void setVenue(String venue) {
        mVenue = venue;
    }

    //----------------------------------------------------------------------------------------------

    public String getCity() {
        return mCity;
    }

    //----------------------------------------------------------------------------------------------

    public void setCity(String city) {
        mCity = city;
    }

    //----------------------------------------------------------------------------------------------

    public Date getDate() {
        return mDate;
    }

    //----------------------------------------------------------------------------------------------

    public void setDate(Date date) {
        mDate = date;
    }

    //----------------------------------------------------------------------------------------------

    public boolean isActive() {
        return mActive;
    }

    //----------------------------------------------------------------------------------------------

    public void setActive(boolean active) {
        mActive = active;
    }

    //----------------------------------------------------------------------------------------------

    public String getCoach() {
        return mCoach;
    }

    //----------------------------------------------------------------------------------------------

    public void setCoach(String coach) {
        mCoach = coach;
    }
}