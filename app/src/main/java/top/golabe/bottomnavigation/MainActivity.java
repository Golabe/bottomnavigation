package top.golabe.bottomnavigation;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
        BottomNavigationViewBuilder navigationViewBuilder = new BottomNavigationViewBuilder.Builder(this)
                .findView(R.id.bnv_bottom_navigation_view)
                .addItem(new BottomNavigationItemBuilder.Builder(this)
                        .title("Home")
                        .tint(Color.RED,Color.BLACK)
                        .icon(R.mipmap.a1, R.mipmap.a2)
                        .titleColor(Color.GRAY, Color.BLACK)
                        .fragment()
                        .build())
                .addItem(new BottomNavigationItemBuilder.Builder(this)
                        .titleColor(Color.GRAY, Color.BLACK)
                        .title("Find")
                        .icon(R.mipmap.b1, R.mipmap.b2)
                        .fragment()
                        .build())
                .addItem(new BottomNavigationItemBuilder.Builder(this)
                        .title("Map")
                        .titleColor(Color.GRAY, Color.BLACK)
                        .icon(R.mipmap.c1, R.mipmap.c2)
                        .fragment()
                        .build())
                .addItem(new BottomNavigationItemBuilder.Builder(this)
                        .title("Me")
                        .titleColor(Color.GRAY, Color.BLACK)
                        .icon(R.mipmap.d1, R.mipmap.d2)
                        .fragment()
                        .build())
                .defaultSelected(0)
                .setOnReselectListener(new OnReselectListener() {
                    @Override
                    public void onReselect(int position) {
                        Log.d(TAG, "onReselect: " + position);
                    }
                })
                .bgColor(Color.BLUE)
                .finishRefresh(0)
                .setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
                    @Override
                    public void onNavigationItemSelected(int position) {
                        Log.d(TAG, "onNavigationItemSelected: " + position);
                    }
                })
                .navigationHeight(52F)
                .build();


    }
}
