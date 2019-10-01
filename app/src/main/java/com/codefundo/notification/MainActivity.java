package com.codefundo.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;

import androidx.core.app.RemoteInput;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import static com.codefundo.notification.App.channel_1;
import static com.codefundo.notification.App.channel_2;

public class MainActivity extends AppCompatActivity {
    AppCompatEditText title, msg;
    Button chn1, chn2, chn3, chn4, chn5;
    NotificationManagerCompat notificationManagerCompat;
    MediaSessionCompat mediaSessionCompat;
    public static List<Message> Messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chn1 = findViewById(R.id.chn1);
        chn2 = findViewById(R.id.chn2);
        chn3 = findViewById(R.id.chn3);
        chn4 = findViewById(R.id.chn4);
        chn5 = findViewById(R.id.chn5);
        title = findViewById(R.id.title);
        msg = findViewById(R.id.msg);

        mediaSessionCompat = new MediaSessionCompat(this, "tag");
        Messages.add(new Message("Good Morning!", "Jim"));
        Messages.add(new Message("Hello!", null));
        Messages.add(new Message("Hi!", "Jenny"));


        notificationManagerCompat = NotificationManagerCompat.from(this);
        chn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOnCHn1(view);
            }
        });
        chn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOnCHn2(view);
            }
        });
        chn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOnCHn3(view);
            }
        });
        chn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOnCHn4(view);
            }
        });
        chn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendOnCHn5(view);
            }
        });
    }

    private void sendOnCHn5(View view) {
        sendChannel1Notification(getApplicationContext());
    }

    public void sendOnCHn1(View v) {
        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);

        Intent Broadcaseintent = new Intent(this, NotificatioReciever.class);
        Broadcaseintent.putExtra("toastmessage", msg.getText().toString());
        PendingIntent pendingbroadcastIntent = PendingIntent.getBroadcast(this, 0, Broadcaseintent, PendingIntent.FLAG_UPDATE_CURRENT);
        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        Notification notification = new NotificationCompat.Builder(this, channel_1)
                .setSmallIcon(R.drawable.ic_looks_one_black_24dp)
                .setLargeIcon(picture)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.dummy))
                        .setBigContentTitle("Big Content")
                        .setSummaryText("Summary"))
                .setContentTitle(title.getText().toString())
                .setContentText(msg.getText().toString())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setColor(Color.GREEN)
                .addAction(R.mipmap.ic_launcher, "Toast", pendingbroadcastIntent)
                .build();
        notificationManagerCompat.notify(1, notification);


    }



    public void sendOnCHn3(View v) {
        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);


        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.a);

        Notification notification = new NotificationCompat.Builder(this, channel_1)
                .setSmallIcon(R.drawable.ic_looks_one_black_24dp)
                .setLargeIcon(picture)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(picture)
                        .bigLargeIcon(null))
                .setContentTitle(title.getText().toString())
                .setContentText(msg.getText().toString())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setColor(Color.BLUE)
                .build();
        notificationManagerCompat.notify(1, notification);


    }

    public void sendOnCHn4(View v) {

        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.a);

        Notification notification = new NotificationCompat.Builder(this, channel_2)
                .setSmallIcon(R.drawable.ic_looks_two_black_24dp)
                .setContentTitle(title.getText().toString())
                .setContentText(msg.getText().toString())
                .setLargeIcon(picture)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .addAction(R.drawable.ic_thumb_down_black_24dp, "Dislike", null)
                .addAction(R.drawable.ic_skip_previous_black_24dp, "Previous", null)
                .addAction(R.drawable.ic_play_circle_outline_black_24dp, "Play", null)
                .addAction(R.drawable.ic_skip_next_black_24dp, "Next", null)
                .addAction(R.drawable.ic_thumb_up_black_24dp, "like", null)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1, 2, 3))
                .setSubText("Sub Text")
                .build();
        notificationManagerCompat.notify(2, notification);


    }

    public void sendOnCHn5old(View v) {
     //   sendChnNotification(this);
        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        RemoteInput remoteInput = new RemoteInput.Builder("key_text_reply")
                .setLabel("Your answer..").build();
        Intent replyintent = new Intent(this, DirectReplyReciever.class);
        PendingIntent replypendingIntent = PendingIntent.getBroadcast(this, 0, replyintent, 0);

        NotificationCompat.Action replyaction = new NotificationCompat.Action.Builder(
                R.drawable.ic_send_black_24dp
                , "Reply"
                , replypendingIntent
        ).addRemoteInput(remoteInput).build();

        NotificationCompat.MessagingStyle messagingStyle = new NotificationCompat.MessagingStyle("Me");
        messagingStyle.setConversationTitle("Group Chat");

        for (Message chatMessage : Messages) {
            NotificationCompat.MessagingStyle.Message notificationmsg=new NotificationCompat.MessagingStyle.Message(
                    chatMessage.getText(),
                    chatMessage.getTimeStamp(),
                    chatMessage.getSender()
            );
            messagingStyle.addMessage(notificationmsg);
        }

        Notification notification = new NotificationCompat.Builder(this, channel_2)
                .setSmallIcon(R.drawable.ic_looks_two_black_24dp)
                .setContentTitle(title.getText().toString())
                .setContentText(msg.getText().toString())
                .setLargeIcon(picture)
                .addAction(replyaction)
                .setColor(Color.GREEN)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setStyle(messagingStyle)
                .setSubText("Sub Text")
                .build();
        notificationManagerCompat.notify(2, notification);


    }


    public static void sendChannel1Notification(Context context) {
        Intent activityIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, activityIntent, 0);

        RemoteInput remoteInput = new RemoteInput.Builder("key_text_reply")
                .setLabel("Your answer...")
                .build();

        Intent replyIntent;
        PendingIntent replyPendingIntent = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            replyIntent = new Intent(context, DirectReplyReciever.class);
            replyPendingIntent = PendingIntent.getBroadcast(context,
                    0, replyIntent, 0);
        } else {
            //start chat activity instead (PendingIntent.getActivity)
            //cancel notification with notificationManagerCompat.cancel(id)
        }

        NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(
                R.drawable.ic_send_black_24dp,
                "Reply",
                replyPendingIntent
        ).addRemoteInput(remoteInput).build();

        NotificationCompat.MessagingStyle messagingStyle =
                new NotificationCompat.MessagingStyle("Me");
        messagingStyle.setConversationTitle("Group Chat");

        for (Message chatMessage : Messages) {
            NotificationCompat.MessagingStyle.Message notificationMessage =
                    new NotificationCompat.MessagingStyle.Message(
                            chatMessage.getText(),
                            chatMessage.getTimeStamp(),
                            chatMessage.getSender()
                    );
            messagingStyle.addMessage(notificationMessage);
        }

        Notification notification = new NotificationCompat.Builder(context, channel_1)
                .setSmallIcon(R.drawable.ic_looks_one_black_24dp)
                .setStyle(messagingStyle)
                .addAction(replyAction)
                .setColor(Color.BLUE)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, notification);
    }
    public void sendOnCHn2(View v) {


        Notification notification = new NotificationCompat.Builder(this, channel_2)
                .setSmallIcon(R.drawable.ic_looks_two_black_24dp)
                .setContentTitle(title.getText().toString())
                .setContentText(msg.getText().toString())
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("This is line 1")
                        .addLine("This is line 2")
                        .addLine("This is line 3")
                        .addLine("This is line 4")
                        .setBigContentTitle("Big Content")
                        .setSummaryText("Summary"))
                .build();
        notificationManagerCompat.notify(2, notification);


    }
}
