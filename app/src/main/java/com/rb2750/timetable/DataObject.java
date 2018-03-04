package com.rb2750.timetable;

import android.graphics.Color;

public class DataObject
{
    private String subject;
    private String teacher;
    private int startHour;
    private int startMinute;
    private int endMinute;
    private int endHour;
    private int periodLength;
    private int day;
    private String classLocation;
    private boolean current;
    private int color;

    DataObject(String subject, String teacher, int periodLength, int day, int startHour, int startMinute, int endHour, int endMinute, String classLocation, int color, boolean current)
    {
        this.subject = subject;
        this.teacher = teacher;
        this.periodLength = periodLength;
        this.day = day;
        this.color = color;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.classLocation = classLocation;
        this.current = current;
    }

    DataObject(String subject, String teacher, int periodLength, int day, int startHour, int startMinute, int endHour, int endMinute, String classLocation, boolean current)
    {
        this(subject, teacher, periodLength, day, startHour, startMinute, endHour, endMinute, classLocation, Color.WHITE, current);
    }

    public boolean isCurrent()
    {
        return current;
    }

    public void setCurrent(boolean current)
    {
        this.current = current;
    }

    public int getEndMinute()
    {
        return endMinute;
    }

    public void setEndMinute(int endMinute)
    {
        this.endMinute = endMinute;
    }

    public int getEndHour()
    {
        return endHour;
    }

    public void setEndHour(int endHour)
    {
        this.endHour = endHour;
    }

    public String getClassLocation()
    {
        return classLocation;
    }

    public void setClassLocation(String classLocation)
    {
        this.classLocation = classLocation;
    }

    public int getStartHour()
    {
        return startHour;
    }

    public void setStartHour(int startHour)
    {
        this.startHour = startHour;
    }

    public int getStartMinute()
    {
        return startMinute;
    }

    public void setStartMinute(int startMinute)
    {
        this.startMinute = startMinute;
    }

    public int getPeriodLength()
    {
        return periodLength;
    }

    public void setPeriodLength(int periodLength)
    {
        this.periodLength = periodLength;
    }

    public int getDay()
    {
        return day;
    }

    public void setDay(int day)
    {
        this.day = day;
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

    public int getColor()
    {
        return color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }
}