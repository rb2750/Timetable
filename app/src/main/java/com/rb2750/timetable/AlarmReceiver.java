package com.rb2750.timetable;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

public class AlarmReceiver extends WakefulBroadcastReceiver
{
    String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction() != null && context != null)
        {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED))
            {
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                NotificationScheduler.setAllReminders(context);
                Log.d(TAG, "Added all reminders.");
                return;
            }
        }
        Log.d(TAG, "onReceive: ");
//        if (MainActivity.dayOfWeek != 0)
//            for (DataObject object : MainActivity.createDataObjects(MainActivity.dayOfWeek + 1, MainActivity.other))
//                if (object.isCurrent())
//                {
//                    if (!object.getSubject().equalsIgnoreCase("Free")) return;
//                    else break;
//                }
        NotificationScheduler.showNotification(context, MainActivity.class, "Timetable", "Other has a free!");
    }
}
