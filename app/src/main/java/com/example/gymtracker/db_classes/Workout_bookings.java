package com.example.gymtracker.db_classes;

public class Workout_bookings {
    int user_id, work_id, gym_id;
    Workout_bookings()
    {}
    Workout_bookings(int user_id, int work_id, int gym_id)
    {
        this.user_id=user_id;
        this.gym_id=gym_id;
        this.work_id=work_id;
    }
    public void setUser_id(int user_id)
    {
        this.user_id=user_id;
    }
    public void setWork_id(int work_id)
    {
        this.work_id=work_id;
    }
    public void setGym_id(int gym_id)
    {
        this.gym_id=gym_id;
    }
    public  int getUser_id()
    {
        return(user_id);
    }
    public int getWork_id()
    {
        return(work_id);
    }
    public  int getGym_id()
    {
        return(gym_id);
    }
}
