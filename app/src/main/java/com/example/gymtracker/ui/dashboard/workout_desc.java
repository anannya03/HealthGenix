package com.example.gymtracker.ui.dashboard;

public class workout_desc {
        public String work_name;
        public String desc;
        public int work_id, booked, cap;
        public workout_desc(String work_name, String desc, int work_id, int booked, int cap)
        {
            this.work_id=work_id;
            this.booked= booked;
            this.cap=cap;
            this.work_name=work_name;
            this.desc=desc;

        }
        public void setWork_name(String work_name)
        {
            this.work_name=work_name;
        }
        public void setDesc(String desc)
        {
            this.desc=desc;
        }
        public String getWork_name()
        {
            return(work_name);
        }
        public String getDesc()
        {
            return(desc);
        }
        public int getWork_id()
        {
            return (work_id);
        }
        public int getBooked()
        {
            return(booked);

        }
        public int getCap()
        {
            return(cap);
        }
    }


