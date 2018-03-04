package com.rb2750.timetable;

import android.app.*;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.support.v4.app.NotificationCompat;

import java.util.Date;
import java.util.Locale;

import static android.content.Context.ALARM_SERVICE;

public class NotificationScheduler
{
    public static int DAILY_REMINDER_REQUEST_CODE = 100;

    public static void setAllReminders(Context context)
    {
        DAILY_REMINDER_REQUEST_CODE = 100;

        for (int i = DAILY_REMINDER_REQUEST_CODE; i < DAILY_REMINDER_REQUEST_CODE + 40; i++) cancelReminder(context, AlarmReceiver.class, i);
        java.util.Calendar c = java.util.Calendar.getInstance(Locale.UK);
        c.setTime(new Date());

        int dayOfWeek = c.get(java.util.Calendar.DAY_OF_WEEK) - 2;

        MainActivity.dayOfWeek = dayOfWeek;

        if (MainActivity.other == 2) MainActivity.createOtherData();
        for (int i = 1; i <= 5; i++)
        {
            if (i >= dayOfWeek + 1)
            {
                for (DataObject object : MainActivity.createDataObjects(i, MainActivity.other))
                {
                    if (object.getSubject().equalsIgnoreCase("Free"))
                    {
                        NotificationScheduler.setReminder(context, AlarmReceiver.class, object.getDay(), object.getStartHour(), object.getStartMinute() + 5);
                    }
                }
            }
        }
    }

    public static void showNotification(Context context, Class<?> cls, String title, String content)
    {
        Intent notificationIntent = new Intent(context, cls);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(cls);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(
                DAILY_REMINDER_REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Notification notification = builder.setContentTitle(title)
                .setContentText(content).setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_calendar).setVibrate(new long[]{0, 100, 100, 100, 100, 100})
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(DAILY_REMINDER_REQUEST_CODE, notification);
    }

    public static void setReminder(Context context, Class<?> cls, int date, int hour, int min)
    {
        Calendar calendar = Calendar.getInstance();
        Calendar setcalendar = Calendar.getInstance();
        setcalendar.set(Calendar.DAY_OF_WEEK, /*date + 1*/1);
        setcalendar.set(Calendar.HOUR_OF_DAY, hour);
        setcalendar.set(Calendar.MINUTE, min);
        setcalendar.set(Calendar.SECOND, 0);

        if (calendar.get(Calendar.DAY_OF_WEEK) == setcalendar.get(Calendar.DAY_OF_WEEK) && (setcalendar.get(Calendar.HOUR_OF_DAY) < calendar.get(Calendar.HOUR_OF_DAY) || setcalendar.get(Calendar.MINUTE) < calendar.get(Calendar.MINUTE)))
            return;
        if (setcalendar.before(calendar)) setcalendar.add(Calendar.DATE, 1);

        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
//
        Intent intent1 = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                DAILY_REMINDER_REQUEST_CODE, intent1,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        am.setAlarmClock(new AlarmManager.AlarmClockInfo(setcalendar.getTimeInMillis(), pendingIntent), pendingIntent);

        DAILY_REMINDER_REQUEST_CODE += 1;
    }

    public static void cancelReminder(Context context, Class<?> cls, int code)
    {
        // Disable a receiver
        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        Intent intent1 = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                code, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        am.cancel(pendingIntent);
        pendingIntent.cancel();
    }
}
