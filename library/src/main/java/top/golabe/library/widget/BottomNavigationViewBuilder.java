package top.golabe.library.widget;


import android.app.Activity;
import android.support.annotation.ColorInt;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import top.golabe.library.callback.OnNavigationItemSelectedListener;
import top.golabe.library.callback.OnReselectListener;

import static top.golabe.library.DefaultValue.DEFAULT_NAVIGATION_HEIGHT;
import static top.golabe.library.DefaultValue.DEFAULT_SELECTED;
import static top.golabe.library.DefaultValue.DEFAULT_VALUE;

public class BottomNavigationViewBuilder {
    private static final String TAG = "BottomNavigationViewBui";
    private BottomNavigationView mBnView;

    public BottomNavigationViewBuilder(Builder builder) {
        this.mBnView = builder.bottomNavigationView;
        mBnView.setNavigationHeight(builder.navigationHeight);
        for (int i = 0; i < builder.items.size(); i++) {
            BottomNavigationItem bottomNavigationItem = builder.items.get(i).getBottomNavigationItem();
            mBnView.addView(bottomNavigationItem, i);
        }
        mBnView.setOnNavigationItemSelectedListener(builder.onNavigationItemSelectedListener);
        mBnView.setOnReselectListener(builder.onReselectListener);
        mBnView.setDefaultSelected(builder.defaultSelected);
        mBnView.setBgColor(builder.bgColor);

    }

    public static class Builder {
        private BottomNavigationView bottomNavigationView;
        private List<BottomNavigationItemBuilder> items = new ArrayList<>();
        private WeakReference<Activity> activity;
        private int defaultSelected = DEFAULT_SELECTED;
        private OnNavigationItemSelectedListener onNavigationItemSelectedListener;
        private OnReselectListener onReselectListener;
        private float navigationHeight = DEFAULT_NAVIGATION_HEIGHT;
        private int bgColor =DEFAULT_VALUE;

        public Builder(Activity activity) {
            this.activity = new WeakReference<>(activity);
        }

        public Builder navigationHeight(float height) {
            this.navigationHeight = height;
            return this;
        }

        public Builder findView(int viewId) {
            this.bottomNavigationView = activity.get().findViewById(viewId);
            return this;
        }

        public Builder addItem(BottomNavigationItemBuilder item) {
            if (item == null) {
                throw new IllegalStateException("item  can not be NULL or EMPTY !");
            }
            this.items.add(item);
            return this;
        }

        public Builder setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener listener) {
            this.onNavigationItemSelectedListener = listener;
            return this;
        }


        public Builder setOnReselectListener(OnReselectListener listener) {
            this.onReselectListener = listener;
            return this;
        }

        public BottomNavigationViewBuilder build() {
            return new BottomNavigationViewBuilder(this);
        }


        public Builder defaultSelected(int position) {
            if (position < items.size() && position >= 0) {
                this.defaultSelected = position;
            } else throw new IndexOutOfBoundsException("position not in range");
            return this;
        }

        public Builder bgColor(@ColorInt int bgColor) {
            this.bgColor = bgColor;
            return this;
        }
    }
}
