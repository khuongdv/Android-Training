package jpbyvideo.com.w4d2_broadcastintents;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Sau 30 giây sẽ trigger ra 1 broadcast
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setAction("demo.action.CUSTOM_ACTION");
                intent.putExtra("fromBroadcast", "Hello from BroadCastIntent project");
                sendBroadcast(intent);
            }
        }, 20000);
    }
}
