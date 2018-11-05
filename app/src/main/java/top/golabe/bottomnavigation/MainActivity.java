package top.golabe.bottomnavigation;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import top.golabe.library.widget.BottomNavigationItemBuilder;
import top.golabe.library.widget.BottomNavigationViewBuilder;
import top.golabe.library.callback.OnNavigationItemSelectedListener;
import top.golabe.library.callback.OnReselectListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity===========";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BottomNavigationViewBuilder navigationViewBuilder = new BottomNavigationViewBuilder.Builder(this)
                .findView(R.id.bnv_bottom_navigation_view)
                .addItem(new BottomNavigationItemBuilder.Builder(this)
                        .title("精选")
                        .titleSize(12f)
                        .icon(R.mipmap.auslese, R.mipmap.auslese)
                        .tint(Color.parseColor("#8a8a8a"), Color.parseColor("#2c2c2c"))
                        .titleColor(Color.parseColor("#bfbfbf"), Color.parseColor("#2c2c2c"))
                        .build())
                .addItem(new BottomNavigationItemBuilder.Builder(this)
                        .tint(Color.parseColor("#8a8a8a"), Color.parseColor("#2c2c2c"))
                        .titleColor(Color.parseColor("#bfbfbf"), Color.parseColor("#2c2c2c"))
                        .title("Search")
                        .titleSize(12f)
                        .icon(R.mipmap.search, R.mipmap.search)
                        .build())
                .addItem(new BottomNavigationItemBuilder.Builder(this)
                        .title("Map")
                        .titleSize(12f)
                        .tint(Color.parseColor("#8a8a8a"), Color.parseColor("#2c2c2c"))
                        .titleColor(Color.parseColor("#bfbfbf"), Color.parseColor("#2c2c2c"))
                        .icon(R.mipmap.map, R.mipmap.map)
                        .build())
                .addItem(new BottomNavigationItemBuilder.Builder(this)
                        .title("Me")
                        .titleSize(12f)
                        .tint(Color.parseColor("#8a8a8a"), Color.parseColor("#2c2c2c"))
                        .titleColor(Color.parseColor("#bfbfbf"), Color.parseColor("#2c2c2c"))
                        .icon(R.mipmap.me, R.mipmap.me)
                        .build())
                .setOnReselectListener(new OnReselectListener() {
                    @Override
                    public void onReselect(int position) {
                        Toast.makeText(MainActivity.this, "再次选中" + position, Toast.LENGTH_SHORT).show();
                    }
                })
                .bgColor(Color.WHITE)

                .setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
                    @Override
                    public void onNavigationItemSelected(int position) {
                        Toast.makeText(MainActivity.this, "选中" + position, Toast.LENGTH_SHORT).show();
                    }
                })
                .fragments(R.id.fl_container,FragmentA.class,FragmentB.class,FragmentC.class,FragmentD.class)
                .defaultSelected(2)
                .navigationHeight(52F)
                .build();


    }
}
