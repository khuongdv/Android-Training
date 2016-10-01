package songokute.com.loginwithsqlite.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import songokute.com.loginwithsqlite.R;
import songokute.com.loginwithsqlite.model.User;
import songokute.com.loginwithsqlite.utils.MyDatabase;

/**
 * Created by KhuongDV on 6/8/2016.
 */
public class UserAdapter extends BaseAdapter {
    List<User> listUser;
    Activity context;

    public UserAdapter(Activity context, List<User> lst) {
        super();
        this.context = context;
        this.listUser = lst;
    }

    public UserAdapter() {
        super();
    }

    @Override
    public int getCount() {
        return listUser.size();
    }

    @Override
    public User getItem(int position) {
        return listUser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(R.layout.row_user, parent, false);
        TextView txtName = (TextView) view.findViewById(R.id.txtUsername);
        TextView txtDesc = (TextView) view.findViewById(R.id.txtDescription);
        Button btnDel = (Button) view.findViewById(R.id.btnXoa);
        final User user = listUser.get(position);
        txtName.setText(user.getUsername());
        txtDesc.setText(user.getDescription());
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabase.getInstance(context.getApplicationContext()).deleteUser(user.getUsername());
                listUser.remove(user);
                notifyDataSetChanged();// Update len list view
                Snackbar snackbar = Snackbar.make(v, "Xoá thành công user = " + user.getUsername(), Snackbar.LENGTH_LONG);
                snackbar.setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listUser.add(user);
                        MyDatabase.getInstance(context.getApplicationContext()).saveUser(user);
                        notifyDataSetChanged();
                    }
                });
                snackbar.show();
            }
        });
        return view;
    }
}
