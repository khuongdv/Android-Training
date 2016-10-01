package android.z10.com.androidrealm.adapters;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.z10.com.androidrealm.R;
import android.z10.com.androidrealm.entity.Item;
import android.z10.com.androidrealm.utils.RealmUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 6/13/2016.
 */
public class ItemAdapter extends ArrayAdapter<Item> {
    List<Item> list = new ArrayList<>();
    Activity activity;


    public ItemAdapter(Activity context, int resID, List<Item> lst) {
        super(context, resID, lst);
        list.addAll(lst);
        this.activity = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Item getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Item myitem = list.get(position);
        View view = activity.getLayoutInflater().inflate(R.layout.item_row, parent, false);
        TextView txtName = (TextView) view.findViewById(R.id.txtName);
        TextView txtDesc = (TextView) view.findViewById(R.id.txtDesc);
        TextView txtQuantity = (TextView) view.findViewById(R.id.txtQuantity);
        txtName.setText(myitem.getName());
        txtDesc.setText(myitem.getDescription());
        txtQuantity.setText("Số lượng: " + myitem.getQuantity());
        Button btnPopup = (Button) view.findViewById(R.id.btnAction);
        btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(activity, v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menuDelete:
                                // Thực hiện xóa
                                list.remove(myitem);
                                notifyDataSetChanged();
                                RealmUtils.getInstance(activity.getApplicationContext()).delete(myitem);
                                return true;
                            case R.id.menuProm:
                                Toast.makeText(activity, "Đã cho vào danh sách khuyến mại!", Toast.LENGTH_LONG).show();
                                return true;
                        }
                        return false;
                    }
                });
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });
        return view;
    }
}
