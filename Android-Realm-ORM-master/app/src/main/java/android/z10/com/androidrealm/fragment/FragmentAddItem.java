package android.z10.com.androidrealm.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.z10.com.androidrealm.MainActivity;
import android.z10.com.androidrealm.R;
import android.z10.com.androidrealm.entity.Item;
import android.z10.com.androidrealm.utils.RealmUtils;

/**
 * Created by Admin on 6/13/2016.
 */
public class FragmentAddItem extends Fragment implements OnClickListener {
    TextView txtName, txtDesc, txtQuantity;
    Button btnSave;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtName = (TextView) view.findViewById(R.id.txtName);
        txtDesc = (TextView) view.findViewById(R.id.txtDesc);
        txtQuantity = (TextView) view.findViewById(R.id.txtQuantity);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onClick(View v) {
        if (v == btnSave) {
            // Coi  như 3 fields đều có dữ liệu hợp lệ
            Item item = new Item().setDescription(txtDesc.getText().toString())
                    .setQuantity(Integer.parseInt(txtQuantity.getText().toString()))
                    .setName(txtName.getText().toString());
            RealmUtils.getInstance(getActivity().getApplicationContext()).saveItem(item);
            ((MainActivity) getActivity()).gotoListFragment();
        }
    }
}
