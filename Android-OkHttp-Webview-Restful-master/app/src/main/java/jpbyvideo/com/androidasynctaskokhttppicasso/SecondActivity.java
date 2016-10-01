package jpbyvideo.com.androidasynctaskokhttppicasso;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import jpbyvideo.com.androidasynctaskokhttppicasso.async.AsyncTaskWithOkHttpJSON;

/**
 * Created by KhuongDV on 6/17/2016.
 */
public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        new AsyncTaskWithOkHttpJSON(this, (ListView) findViewById(R.id.listView))
                .execute(Constant.TEST_URL);
    }
}
