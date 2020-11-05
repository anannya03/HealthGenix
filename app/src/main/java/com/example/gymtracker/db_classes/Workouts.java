package com.example.gymtracker.db_classes;

import java.sql.Date;
import java.sql.Time;

public class Workouts {
    int work_id, booked, cap, gym_id;
    String work_name, type;
    Date date;
    Time time;
    Workouts()
    {   }
    Workouts(int gym_id, Date date, Time time, String work_name, String type)
    {
        this.gym_id=gym_id;
        this.date=date;
        this.time=time;
        this.work_name=work_name;
        this.type=type;
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
    public void setType(String type)
    {
        this.type=type;
    }
    public void setDate(Date date)
    {
        this.date= date;
    }
    public void setTime(Time time)
    {
        this.time= time;
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
    public String getType()
    {
        return(type);
    }
    public Date getDate()
    {
        return(date);
    }
    public Time getTime()
    {
        return(time);
    }
}
