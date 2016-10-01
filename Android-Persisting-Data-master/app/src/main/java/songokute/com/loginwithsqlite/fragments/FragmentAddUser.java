package songokute.com.loginwithsqlite.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import songokute.com.loginwithsqlite.R;
import songokute.com.loginwithsqlite.model.User;
import songokute.com.loginwithsqlite.utils.Constants;
import songokute.com.loginwithsqlite.utils.MyDatabase;

/**
 * Created by Admin on 6/9/2016.
 */
public class FragmentAddUser extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnThem = (Button) view.findViewById(R.id.btnThem);
        final EditText txtUsername = (EditText) view.findViewById(R.id.txtUsername);
        final EditText txtDescription = (EditText) view.findViewById(R.id.txtDescription);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(txtUsername.getText().toString()) || TextUtils.isEmpty(txtUsername.getText().toString().trim())) {
                    Toast.makeText(getActivity(), "Hãy điền vào username", Toast.LENGTH_LONG).show();
                    txtUsername.setText("");
                    return;
                }
                User user = new User().setDescription(txtDescription.getText().toString())
                        .setUsername(txtUsername.getText().toString().trim());
                long ret = MyDatabase.getInstance(getActivity().getApplicationContext()).saveUser(user);
                if (ret == -1L) {
                    Toast.makeText(getActivity(), "Thêm không thành công", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_LONG).show();
                    // Go to listview
                    FragmentListView fm = (FragmentListView) getFragmentManager().findFragmentByTag(Constants.F_LISTVIEW);
                    if(fm == null){
                        fm = new FragmentListView();
                    }
                    getActivity().getFragmentManager().beginTransaction()
                            .replace(R.id.placeHolder, fm, Constants.F_LISTVIEW).commit();
                }
            }
        });
    }
}
