package jpbyvideo.com.w4d2_broadcastreceiver;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by KhuongDV on 6/20/2016.
 */
public class SecondActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        TextView tx = (TextView) findViewById(R.id.txtHello);
        tx.setText("2nd screen, open from notification");
    }
}
