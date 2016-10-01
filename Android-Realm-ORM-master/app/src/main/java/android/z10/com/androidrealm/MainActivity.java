package android.z10.com.androidrealm;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.z10.com.androidrealm.fragment.FragmentAddItem;
import android.z10.com.androidrealm.fragment.FragmentListItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotoListFragment();
    }

    public void gotoListFragment() {
        getFragmentManager().beginTransaction().replace(R.id.placeholder, new FragmentListItem())
                .commit();
    }

    public void gotoAddFragment() {
        getFragmentManager().beginTransaction().replace(R.id.placeholder, new FragmentAddItem())
                .commit();
    }
}
