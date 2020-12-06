package com.example.gymtracker.db_classes;

import java.sql.Date;
import java.sql.Time;

public class Workouts {
    int work_id, booked, cap, gym_id;
    String work_name;
    String branch;
    String work_date;
    String work_time;
    Workouts()
    {   }
    Workouts(int gym_id, String date, String time, String work_name)
    {
        this.gym_id=gym_id;
        this.work_date=date;
        this.work_time=time;
        this.work_name=work_name;

    }
    public void setBranch(String branch){
        this.branch=branch;
    }
    public String getBranch()
    {
        return(branch);
    }
    public void setWork_id(int work_id)
    {
        this.work_id=work_id;
    }
    public void setWork_name(String work_name)
    {
        this.work_name=work_name;
    }
    public void setBooked(int booked)
    {
        this.booked=booked;
    }
    public void setCap(int cap)
    {
        this.cap=cap;
    }
    public void setGym_id(int gym_id)
    {
        this.gym_id=gym_id;
    }

    public void setDate(String date)
    {
        this.work_date= date;
    }
    public void setTime(String time)
    {
        this.work_time= time;
    }
    public int getWork_id()
    {
        return(work_id);
    }
    public int getBooked()
    {
        return(booked);
    }
    public int getCap()
    {
        return(cap);
    }
    public int getGym_id()
    {
        return(gym_id);
    }
    public String getWork_name()
    {
        return(work_name);
    }

    public String getDate()
    {
        return(work_date);
    }
    public String getTime()
    {
        return(work_time);
    }
}
