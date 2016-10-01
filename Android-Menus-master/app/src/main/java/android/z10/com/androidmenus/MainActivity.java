package android.z10.com.androidmenus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

/**
 * App này sẽ demo về các loại menu trong ứng dụng android
 * 1. Popup menu (Menu sổ ra tại element được click vào)
 * 2. Options menu: Menu của nút menu của máy
 * 3. Context menu
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // --- Button context menu, cần phải đăng kí với activity là thằng button này có context menu
        Button btnContextMenu = (Button) findViewById(R.id.btnContextMenu);
        registerForContextMenu(btnContextMenu);// Oke, giờ ấn nút này 1 lúc sẽ hiện ra menu context
        // Popup menu
        Button btnPopup = (Button) findViewById(R.id.btnPopup);
        btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.pMenu1:
                                Toast.makeText(MainActivity.this, "Xem video", Toast.LENGTH_LONG).show();
                                return true;
                            case R.id.pMenu2:
                                Toast.makeText(MainActivity.this, "Xem ảnh đẹp", Toast.LENGTH_LONG).show();
                                return true;
                        }
                        return false;
                    }
                });
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });
    }

    // 1. OPTIONS MENU
    // Hàm này tạo ra menu cho activity này (activity khác muốn có option menu phải viết hàm này như dưới đây)
    // Show menu này bằng cách ấn nút menu của  máy
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    // Khi option menu được chọn
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu1:
                Toast.makeText(this, "Bạn đang xem giỏ hàng", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu2:
                Toast.makeText(this, "Đã xóa hết giỏ hàng", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //-----------Context menu ------------------
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        //find out which menu item was pressed
        switch (item.getItemId()) {
            case R.id.cMenu1:
                Toast.makeText(this, "Chuyển đến trang đăng ký", Toast.LENGTH_LONG).show();
                return true;
            case R.id.cMenu2:
                Toast.makeText(this, "Đăng nhập nào", Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
    }
}
