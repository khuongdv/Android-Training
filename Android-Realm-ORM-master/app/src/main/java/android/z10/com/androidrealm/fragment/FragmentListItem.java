package android.z10.com.androidrealm.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.z10.com.androidrealm.MainActivity;
import android.z10.com.androidrealm.R;
import android.z10.com.androidrealm.adapters.ItemAdapter;
import android.z10.com.androidrealm.entity.Item;
import android.z10.com.androidrealm.utils.RealmUtils;

import java.util.List;

/**
 * Created by Admin on 6/13/2016.
 */
public class FragmentListItem extends Fragment implements View.OnClickListener {
    Button btnAdd;
    ListView listView;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        listView = (ListView) view.findViewById(R.id.listView);
        List<Item> listItem = RealmUtils.getInstance(getActivity().getApplicationContext()).getAllItem();
        ItemAdapter itemAdapter = new ItemAdapter(getActivity(),R.layout.item_row, listItem);
        listView.setAdapter(itemAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onClick(View v) {
        ((MainActivity)getActivity()).gotoAddFragment();
    }
}
