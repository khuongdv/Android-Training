package songokute.com.loginwithsqlite.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import songokute.com.loginwithsqlite.R;
import songokute.com.loginwithsqlite.utils.Constants;

/**
 * Created by Admin on 6/9/2016.
 */
public class FragmentInternalStorage extends Fragment implements View.OnClickListener{
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
        btnLoad.setText("Load từ Internal Storage");
        btnSave.setText("Lưu vào Internal Storage");
    }

    @Override
    public void onClick(View v) {
        if (v == btnLoad) {
            // Mở file có tên là data ra, lấy dữ liệu và fill lên textbox
            try {
                BufferedReader bf = new BufferedReader(new InputStreamReader(getActivity().openFileInput("data")));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bf.readLine()) != null){
                    sb.append(line).append("\n");
                }
                txtOutput.setText(sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
                txtOutput.setText("Lỗi đọc file");
                txtOutput.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
            }

        }
        if (v == btnSave) {
            String input = txtInput.getText().toString();
            saveToInternalStorage(input);
        }
        if (v ==btnBack){
            FragmentListView fm = (FragmentListView) getFragmentManager().findFragmentByTag(Constants.F_LISTVIEW);
            if(fm == null){
                fm = new FragmentListView();
            }
            getFragmentManager().beginTransaction().replace(R.id.placeHolder, fm, Constants.F_LISTVIEW).commit();
        }
    }

    private void saveToInternalStorage(String input) {
        try {
            FileOutputStream fos = getActivity().openFileOutput("data", getActivity().MODE_PRIVATE);
            fos.write(input.getBytes());
            fos.close();
            Toast.makeText(getActivity(), "Lưu file thành công", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
