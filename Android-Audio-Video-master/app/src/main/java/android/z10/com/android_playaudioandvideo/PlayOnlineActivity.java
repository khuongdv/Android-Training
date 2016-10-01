package android.z10.com.android_playaudioandvideo;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.IOException;

/**
 * Created by Admin on 6/28/2016.
 */
public class PlayOnlineActivity extends Activity implements View.OnClickListener {
    MediaPlayer mp;
    ProgressBar progressBar;
    Button btnPlay, btnPause;
    boolean isMpPrepared = false;
    String url = "http://download.f9.stream.nixcdn.com/ecaacfc9da1b6dfdd3ab39e95ffeeb29/5771657a/NhacCuaTui922/ChotTinhGiac-MinhVuongM4UThanhDucTrung-4472098.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // Observe player and update to progressbar
        new Thread(){
            @Override
            public void run() {
                while(true) {
                    if(mp.getCurrentPosition() >= mp.getDuration()){
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(mp.getCurrentPosition());
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        if (v == btnPause) {
            if (mp.isPlaying()) {
                mp.pause();
            }
            return;
        }
        if (v == btnPlay) {
            if(isMpPrepared){
                mp.start();
            } else
            try {
                mp.setDataSource(url);
                mp.prepareAsync();
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mpl) {
                        mp.start();
                        isMpPrepared = true;
                        progressBar.setMax(mp.getDuration());
                        progressBar.setProgress(0);
                    }
                });
            } catch (Exception e) {
            }

        }
    }
}
