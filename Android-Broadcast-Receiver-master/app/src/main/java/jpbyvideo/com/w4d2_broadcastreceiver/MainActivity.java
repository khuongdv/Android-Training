package jpbyvideo.com.w4d2_broadcastreceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txt = (TextView) findViewById(R.id.txtHello);
        // Nếu activity này được gọi từ broadcast receiver thì sẽ có string fromBroadcast, nên get ra và
        // fill vào textview
        txt.setText(getIntent().getStringExtra("fromBroadcast"));
        // Nút show notif, bấm vào sẽ bắn 1 notification
        Button btnNotif = (Button) findViewById(R.id.btnNotif);
        btnNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle("Demo App");
                builder.setContentText("Hello From Notification");

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this,
                        (int) System.currentTimeMillis(), intent, 0);
                builder.setContentIntent(pIntent);
                Notification noti = builder.build();
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // hide the notification after its selected
                noti.flags |= Notification.FLAG_AUTO_CANCEL;
                notificationManager.notify(0, noti);

            }
        });
    }
}
