package songokute.com.loginwithsqlite.fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import songokute.com.loginwithsqlite.R;
import songokute.com.loginwithsqlite.adapter.UserAdapter;
import songokute.com.loginwithsqlite.model.User;
import songokute.com.loginwithsqlite.utils.Constants;
import songokute.com.loginwithsqlite.utils.MyDatabase;

/**
 * Created by Admin on 6/9/2016.
 */
public class FragmentListView extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_listview, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyDatabase db = MyDatabase.getInstance(getActivity().getApplicationContext());
        db.saveUser(new User().setUsername("Đào Văn Khương").setDescription("Fsoft FSU1.Z10"));
        db.saveUser(new User().setUsername("Nguyễn Hải Yến").setDescription("Viettel Media"));
        ListView listView = (ListView) view.findViewById(R.id.listView);
        UserAdapter userAdapter = new UserAdapter(getActivity(), db.getAllUser());
        listView.setAdapter(userAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Click on listview", Toast.LENGTH_LONG).show();
            }
        });
        // Bind button them mới
        Button btnAdd = (Button) view.findViewById(R.id.btnThem);
        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentAddUser fm = (FragmentAddUser) getFragmentManager().findFragmentByTag(Constants.F_ADD);
                if (fm == null) {
                    fm = new FragmentAddUser();
                }
                getFragmentManager().beginTransaction().replace(R.id.placeHolder, fm, Constants.F_ADD).commit();
            }
        });

        Button sharedPref = (Button) view.findViewById(R.id.btnSharedPref);
        Button btnFile = (Button) view.findViewById(R.id.inexStorg);
        sharedPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentSharedPreference fm = (FragmentSharedPreference) getFragmentManager().findFragmentByTag(Constants.F_SHARED_PREFERENCES);
                if (fm == null) {
                    fm = new FragmentSharedPreference();
                }
                getFragmentManager().beginTransaction().replace(R.id.placeHolder, fm, Constants.F_SHARED_PREFERENCES).commit();
            }
        });
        btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentInternalStorage fm = (FragmentInternalStorage) getFragmentManager().findFragmentByTag(Constants.F_INTERNAL_STORAGE);
                if (fm == null) {
                    fm = new FragmentInternalStorage();
                }
                getFragmentManager().beginTransaction().replace(R.id.placeHolder, fm, Constants.F_INTERNAL_STORAGE).commit();
            }
        });

        Button btnExternalStorage = (Button) view.findViewById(R.id.externalStorage);
        btnExternalStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Buoc 1; check xem có ghi/đọc đc file ở externale storage ko đã
                String state = Environment.getExternalStorageState();
                if (Environment.MEDIA_MOUNTED.equals(state)) {
                    // Buoc 2: Luu file anh? ra ngoai gallery
                    File picturePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    File newImage = new File(picturePath, "Khuong.png");
                    try {
                        picturePath.mkdir();// Đảm bảo thư mục Pictures tồn tại
                        newImage.createNewFile();
                        InputStream is = getActivity().getResources().openRawResource(R.raw.cover);
                        OutputStream os = new FileOutputStream(newImage);
                        byte[] data = new byte[is.available()];
                        is.read(data);
                        os.write(data);
                        is.close();
                        os.close();
                        Toast.makeText(getActivity(), "Save successfully!", Toast.LENGTH_LONG).show();
                        // Load to picture
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        Bitmap bitmap = BitmapFactory.decodeFile(newImage.getPath());
                        imageView.setImageBitmap(bitmap);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "ERROR: " +e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Ko ghi đc
                    Toast.makeText(getActivity(), "Không đọc/ghi được External Storage", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
