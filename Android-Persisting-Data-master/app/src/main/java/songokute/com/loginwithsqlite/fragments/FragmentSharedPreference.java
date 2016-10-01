package songokute.com.loginwithsqlite.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import songokute.com.loginwithsqlite.R;
import songokute.com.loginwithsqlite.utils.Constants;

/**
 * Created by Admin on 6/9/2016.
 */
public class FragmentSharedPreference extends Fragment implements View.OnClickListener {
    EditText txtInput;
    TextView txtOutput;
    Button btnLoad, btnSave, btnBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shared_preference, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        txtInput = (EditText) view.findViewById(R.id.txtContent);
        txtOutput = (TextView) view.findViewById(R.id.txtOutput);
        btnLoad = (Button) view.findViewById(R.id.btnLoad);
        btnBack = (Button) view.findViewById(R.id.btnBack);

        btnLoad.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnLoad) {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
            String input = pref.getString("key", "-N/A-");
            txtOutput.setText(input);
        }
        if (v == btnSave) {
            String input = txtInput.getText().toString();
            saveToSharedPreference("key", input);
        }
        if (v ==btnBack){
            FragmentListView fm = (FragmentListView) getFragmentManager().findFragmentByTag(Constants.F_LISTVIEW);
            if(fm == null){
                fm = new FragmentListView();
            }
            getFragmentManager().beginTransaction().replace(R.id.placeHolder, fm, Constants.F_LISTVIEW).commit();
        }
    }

    private void saveToSharedPreference(String key, String input) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, input);
        editor.commit();
        Toast.makeText(getActivity(), "Lưu vào SharedPreference thành công, key = " + key
                + ", value = " + input, Toast.LENGTH_LONG).show();
    }
}
