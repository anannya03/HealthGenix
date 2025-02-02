package com.example.gymtracker.db_classes;

import java.sql.Date;
public class User {
    long user_id, gym_id;
    String fname, lname, email, pwd, phno, fitness, gen;
    double weight, height, bmi;
    int age;
    Date mem_start, mem_end;

    public User(){}
    public long getUser_id()
    {
        return(user_id);
    }
    public void setUser_id(long user_id)
    {
        this.user_id=user_id;
    }
    public long getGym_id()
    {
        return(gym_id);
    }
    public void setGym_id(long gym_id)
    {
        this.gym_id=gym_id;
    }
    public String getFname()
    {
        return(fname);
    }
    public void setFname(String fname)
    {
        this.fname= fname;
    }
    public String getLname()
    {
        return(lname);
    }
    public void setLname(String lname)
    {
        this.lname=lname;
    }
    public String getPwd()
    {
        return(pwd);
    }
    public void setPwd(String pwd)
    {
        this.pwd=pwd;
    }
    public String getEmail()
    {
        return(email);
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
    public double getWeight()
    {
        return(weight);
    }
    public void setWeight(double weight)
    {
        this.weight=weight;
    }
    public double getHeight()
    {
        return(height);
    }
    public void setHeight(double height)
    {
        this.height=height;
    }
    public double getBmi()
    {
        return(bmi);
    }
    public void setBmi(double bmi)
    {
        this.bmi=bmi;
    }
    public int getAge()
    {
        return(age);
    }
    public void setAge(int age)
    {
        this.age= age;
    }
    public void setPhno(String phno)
    {
        this.phno=phno;
    }
    public String getPhno()
    {
        return(phno);
    }
    public void setFitness(String fitness)
    {
        this.fitness=fitness;
    }
    public String getFitness()
    {
        return(fitness);
    }
    public String getGen()
    {
        return(gen);
    }
    public void setGen(String gen)
    {
        this.gen=gen;
    }
    public Date getMem_start()
    {
        return(mem_start);
    }
    public void setMem_start(Date mem_start)
    {
        this.mem_start=mem_start;
    }
    public Date getMem_end()
    {
        return(mem_end);
    }
    public void setMem_end(Date mem_end)
    {
        this.mem_end=mem_end;
    }

}
