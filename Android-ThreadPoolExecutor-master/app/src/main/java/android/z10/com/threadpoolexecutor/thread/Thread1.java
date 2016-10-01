package android.z10.com.threadpoolexecutor.thread;

import android.app.Activity;

/**
 * Created by Admin on 6/18/2016.
 */
public class Thread1 extends Thread {
    Activity activity;

    public Thread1(Activity c) {
        activity = c;
    }

    // Class nay loop tu 1 den 1000000, 5 don vi cap nhat man hinh 1 lan
    int value = 0;

    @Override
    public void run() {
        for(value = 0; value < 10000000; value ++){
            if(value % 5 == 0){

            }
        }
    }
}
