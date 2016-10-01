package android.z10.com.android_playaudioandvideo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MediaPlayer mediaPlayer;
    TextView txtName;
    Button btnPlay, btnPause, btnOnl,btnVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.bdmt);// create() already call prepare
//        try {
//            mediaPlayer.prepare(); // must call prepare first
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        txtName = (TextView) findViewById(R.id.txtFileName);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPause.setOnClickListener(this);
        btnPlay.setOnClickListener(this);

        btnOnl = (Button) findViewById(R.id.btnOnline);
        btnVideo = (Button) findViewById(R.id.btnVideo);
        btnOnl.setOnClickListener(this);
        btnVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnPause){
            mediaPlayer.pause();
            return;
        }
        if(v == btnPlay){
            if(mediaPlayer.isPlaying()){
                return;
            }
            mediaPlayer.start();
            return;
        }
        if(btnOnl == v) {
            startActivity(new Intent(this, PlayOnlineActivity.class));
        }
        if(btnVideo == v) {
            startActivity(new Intent(this, VideoActivity.class));
        }
    }
}
