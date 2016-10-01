package jpbyvideo.com.androidasynctaskokhttppicasso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import jpbyvideo.com.androidasynctaskokhttppicasso.async.AsyncTaskWithOkHttp;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnOK, btnPicasso, btnNext;
    WebView webView1, webView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
    }

    private void bindView() {
        btnOK = (Button) findViewById(R.id.btnOk);
        btnPicasso = (Button) findViewById(R.id.btnPi);
        btnNext = (Button) findViewById(R.id.btnGo);
        btnOK.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPicasso.setOnClickListener(this);
        webView1 = (WebView) findViewById(R.id.webView1);
        webView2 = (WebView) findViewById(R.id.webView2);
    }

    @Override
    public void onClick(View v) {
        if (btnNext == v) {
            Intent i = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(i);
        }
        if (v == btnOK) {
            loadContent(webView1, "http://dantri.com.vn/");
        }
        if (v == btnPicasso) {
            loadContent(webView2, "http://vnexpress.net/");
        }
    }

    void loadContent(WebView w, String url) {
        new AsyncTaskWithOkHttp(this, w).execute(url);
    }
}
