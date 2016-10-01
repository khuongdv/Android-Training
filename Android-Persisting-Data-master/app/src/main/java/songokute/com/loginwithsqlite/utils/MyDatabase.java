package songokute.com.loginwithsqlite.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import songokute.com.loginwithsqlite.UserInterface;
import songokute.com.loginwithsqlite.model.User;

/**
 * Created by KhuongDV on 6/8/2016.
 */
public class MyDatabase extends SQLiteOpenHelper implements UserInterface {
    //<editor-fold desc="Setting up">
    public static final String DATABASE_NAME = "myapp";
    public static final int DATABASE_VERSION = 1;
    private static MyDatabase sInstance;

    public static synchronized MyDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MyDatabase(context.getApplicationContext());
        }
        return sInstance;
    }


    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Được gọi khi DB dược tạo lần ĐẦU TIÊN
    // Nếu DB đã tồn tại với DATABASE_NAME như trên, thì hàm này KHÔNG ĐƯỢC GỌI
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE
                = "CREATE TABLE USER " +
                "(" +
                "USERNAME TEXT PRIMARY KEY, " +
                "DESCRIPTION TEXT" +
                ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    // Gọi khi DB cần update, hàm này đc gọi nếu DB đã tồn tại với same name, và có version DATABASE_VERSION khác
    // version đang lưu
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement change on the upgrade version
    }
    //</editor-fold>

    //<editor-fold desc="Method for manipulating User">
    @Override
    public long saveUser(User user) {
        // Get đối tượng dataabse để dành cho việc ghi dữ liệu
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();// Bắt đầu transaction
        long id = -1;
        try {
            // Tham số cho câu lệnh insert là ContentValues
            ContentValues values = new ContentValues();
            values.put("USERNAME", user.getUsername());
            values.put("DESCRIPTION", user.getDescription());
            id = db.insertOrThrow("USER", null, values);
            db.setTransactionSuccessful();// Commit
            return id;
        } catch (Exception e) {
            Log.d("", "Lỗi thêm User");
        } finally {
            db.endTransaction();// Đóng transaction
        }
        return id;
    }

    @Override
    public void deleteUser(String username) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        db.delete("USER", "USERNAME = ?", new String[]{username});
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public List<User> getAllUser() {
        List<User> lstUsers = new ArrayList<>();
        String GET_ALL_USER_QUERY = "SELECT * FROM USER";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_ALL_USER_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    User newUser = new User().setUsername(cursor.getString(cursor.getColumnIndex("USERNAME")))
                            .setDescription(cursor.getString(cursor.getColumnIndex("DESCRIPTION")));
                    lstUsers.add(newUser);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("", "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return lstUsers;
    }

    @Override
    public boolean checkIfUsernameExist(String username) {
        if (TextUtils.isEmpty(username)) {
            return false;
        }
        String MY_QUERY = "SELECT * FROM USER WHERE USER.USERNAME = " + username;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(MY_QUERY, null);
        return cursor.getCount() == 1;
    }

    @Override
    public boolean deleteAllUser() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            // Order of deletions is important when foreign key relationships exist.
            db.delete("USER", null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("", "Error while trying to delete all users");
            return false;
        } finally {
            db.endTransaction();
        }
        return true;
    }

    //</editor-fold>
}
