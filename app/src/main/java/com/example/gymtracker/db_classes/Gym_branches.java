package com.example.gymtracker.db_classes;

public class Gym_branches {
    int gym_id;
    String city, address;
    Gym_branches(){}
    public  void setGym_id(int gym_id)
    {
        this.gym_id=gym_id;
    }
    public void setCity(String city)
    {
        this.city=city;
    }


    public void setAddress(String address)
    {
        this.address=address;
    }

    public int getGym_id()
    {
        return(gym_id);
    }
    public String getCity()
    {
        return(city);
    }


    public String getAddress()
    {
        return(address);
    }

}
