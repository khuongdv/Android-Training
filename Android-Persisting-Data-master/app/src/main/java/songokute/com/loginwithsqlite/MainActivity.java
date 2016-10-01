package songokute.com.loginwithsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import songokute.com.loginwithsqlite.adapter.UserAdapter;
import songokute.com.loginwithsqlite.fragments.FragmentAddUser;
import songokute.com.loginwithsqlite.fragments.FragmentListView;
import songokute.com.loginwithsqlite.model.User;
import songokute.com.loginwithsqlite.utils.Constants;
import songokute.com.loginwithsqlite.utils.MyDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction().replace(R.id.placeHolder, new FragmentListView(), Constants.F_LISTVIEW).commit();
    }

}

