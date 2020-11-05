package com.example.gymtracker.db_classes;

public class Gym_branches {
    int gym_id;
    String city, area, pincode, address, details;
    Gym_branches(){}
    public  void setGym_id(int gym_id)
    {
        this.gym_id=gym_id;
    }
    public void setCity(String city)
    {
        this.city=city;
    }
    public void setArea(String area)
    {
        this.area=area;
    }
    public void setPincode(String pincode)
    {
        this.pincode=pincode;
    }
    public void setAddress(String address)
    {
        this.address=address;
    }
    public void setDetails(String details)
    {
        this.details=details;
    }
    public int getGym_id()
    {
        return(gym_id);
    }
    public String getCity()
    {
        return(city);
    }
    public String getArea()
    {
        return(area);
    }
    public String getPincode()
    {
        return(pincode);
    }
    public String getAddress()
    {
        return(address);
    }
    public  String getDetails()
    {
        return(details);
    }
}
