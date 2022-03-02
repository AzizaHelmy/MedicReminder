package com.example.medicationreminder.workmanger;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.medicationreminder.R;

public class ReminderWorkerDrugs extends Worker {
    public static final String CHANNEL_ID = "channelId";
    private static NotificationChannel notificationChannel;
    long time;
    String name;
     Context context;

    long[] list;
    public ReminderWorkerDrugs(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context=context;
    }

    @NonNull
    @Override
    public Result doWork() {

        time = getInputData().getLong("alarm",0);
        name = getInputData().getString("medName");
//        dose = getInputData().getInt("dose", 0);
//        food = getInputData().getString("medFood");
        list = getInputData().getLongArray("list");

        showNotification(context, 123, name, "you have " +  " dose should be taken " , null);

        return null;
    }
      private  void showReminderWindow(){



      }
    private static void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(CHANNEL_ID,
                    "Follow_Me", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Follow_Me");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            notificationChannel.enableVibration(true);
        }
    }

    public static void showNotification(Context context, int id, String title, String content, Intent intent) {
        PendingIntent pendingIntent = null;
        if(intent != null)
            pendingIntent = PendingIntent.getActivity(context,id,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            createChannel();
            notificationManager.createNotificationChannel(notificationChannel);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID);
            builder.setContentTitle(title)
                    .setContentText(content)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(content))
                    .setSound(defaultSoundUri)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(content))
                    .setVibrate(new long[]{0,1000,500,1000})
                    .setSound(defaultSoundUri)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_foreground));

            if(pendingIntent != null)
                builder.setContentIntent(pendingIntent);

            Notification notification = builder.build();
            notificationManager.notify(id,notification);


        } else {

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "App_Location");

            notificationBuilder.setAutoCancel(true)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_foreground))
                    .setPriority(Notification.PRIORITY_MAX) // this is deprecated in API 26 but you can still use for below 26. check below update for 26 API
                    .setContentTitle(title)
                    .setContentText(content)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(content))
                    .setVibrate(new long[]{0,1000,500,1000})
                    .setSound(defaultSoundUri)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManagerOldVersion = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManagerOldVersion.notify(id, notificationBuilder.build());

        }

    }

}
