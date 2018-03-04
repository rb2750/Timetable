package com.rb2750.timetable;

public class Period
{
    private String subject;
    private String teacher;
    private int day;
    private int length;
    private String classLocation;

    public Period(String subject, String teacher, String classLocation, int day, int length)
    {
        this.subject = subject;
        this.teacher = teacher;
        this.day = day;
        this.length = length;
        this.classLocation = classLocation;
    }

    public String getClassLocation()
    {
        return classLocation;
    }

    public void setClassLocation(String classLocation)
    {
        this.classLocation = classLocation;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getTeacher()
    {
        return teacher;
    }

    public void setTeacher(String teacher)
    {
        this.teacher = teacher;
    }

    public int getDay()
    {
        return day;
    }

    public void setDay(int day)
    {
        this.day = day;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }
}
