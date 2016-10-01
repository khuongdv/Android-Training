package jpbyvideo.com.w4d2_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by KhuongDV on 6/20/2016.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startActivity(new Intent(context, MainActivity.class)
                .putExtra("fromBroadcast", intent.getStringExtra("fromBroadcast"))
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
