package com.example.gymtracker.db_classes;
import java.sql.Date;
public class User_log {
    int entry_id;
    String email_id;
    String date_tracked;
    double consumed_cal, burnt_cal;
    int water_tracked;
    User_log()
    {}
    public int getEntry_id()
    {
        return(entry_id);
    }
    public String getEmail_id(){return email_id;}
    public int getWater_tracked()
    {
        return(water_tracked);
    }
    public String getDate()
    {
        return(date_tracked);
    }
    public double getConsumed_cal()
    {
        return(consumed_cal);
    }
    public double getBurnt_cal()
    {
        return(burnt_cal);
    }
    public void setEntry_id(int entry_id)
    {
        this.entry_id=entry_id;
    }
    public void setEmail_id(String email_id){
        this.email_id= email_id;
    }
    public void setDate(String date_tracked)
    {
        this.date_tracked=date_tracked;
    }
    public void setConsumed_cal(double consumed_cal)
    {
        this.consumed_cal=consumed_cal;
    }
    public void setBurnt_cal(double burnt_cal)
    {
        this.burnt_cal=burnt_cal;
    }
    public void setWater_tracked(int water_tracked)
    {
        this.water_tracked=water_tracked;
    }

}
