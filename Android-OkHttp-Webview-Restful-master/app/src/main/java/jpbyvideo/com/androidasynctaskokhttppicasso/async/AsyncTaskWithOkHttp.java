package jpbyvideo.com.androidasynctaskokhttppicasso.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.webkit.WebView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by KhuongDV on 6/17/2016.
 */
public class AsyncTaskWithOkHttp extends AsyncTask<String, Long, String> {
    ProgressDialog $;
    Context context;
    WebView webView;

    public AsyncTaskWithOkHttp(Context contxt, WebView webView) {
        this.context = contxt;
        this.webView = webView;
    }

    @Override
    protected void onPreExecute() {
        $ = new ProgressDialog(context);
        $.setTitle("Vui lòng chờ khi tải trang web");
        $.setCancelable(false);
        $.setCanceledOnTouchOutside(false);
        $.show();
    }

    @Override
    protected String doInBackground(String... params) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(params[0])
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "<b>NO DATA</b>";
    }

    @Override
    protected void onPostExecute(String s) {
        webView.loadData(s, "text/html; charset=UTF-8", "");
        $.dismiss();
    }
}
