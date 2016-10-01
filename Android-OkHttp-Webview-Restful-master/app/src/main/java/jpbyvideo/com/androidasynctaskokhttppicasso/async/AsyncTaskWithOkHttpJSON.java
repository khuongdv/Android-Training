package jpbyvideo.com.androidasynctaskokhttppicasso.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by KhuongDV on 6/17/2016.
 */
public class AsyncTaskWithOkHttpJSON extends AsyncTask<String, Long, String> {
    ProgressDialog $_$;
    Context _$_$;
    ListView $$_;

    public AsyncTaskWithOkHttpJSON(Context contxt, ListView listView) {
        this._$_$ = contxt;
        this.$$_ = listView;
    }

    @Override
    protected void onPreExecute() {
        $_$ = new ProgressDialog(_$_$);
        $_$.setTitle("Vui lòng chờ khi tải trang web");
        $_$.setCancelable(false);
        $_$.setCanceledOnTouchOutside(false);
        $_$.show();
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
        List<String> ____ = new ArrayList<>();
        try {
            JSONObject __ = new JSONObject(s);
            JSONArray ___ = __.getJSONArray("Data");
            for (int $ = 0; $ < ___.length(); $++) {
                JSONObject _ = ___.getJSONObject($);
                ____.add(_.getString("ChannelCode"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(_$_$, "Không có dữ liệu", Toast.LENGTH_LONG).show();
        }
        $_$.dismiss();
        $$_.setAdapter(new ArrayAdapter<String>(_$_$, android.R.layout.simple_list_item_1, ____));
    }
}
