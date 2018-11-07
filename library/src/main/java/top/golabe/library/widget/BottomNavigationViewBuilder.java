package top.golabe.library.widget;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.golabe.library.callback.OnNavigationItemSelectedListener;
import top.golabe.library.callback.OnReselectListener;

import static top.golabe.library.DefaultValue.DEFAULT_NAVIGATION_HEIGHT;
import static top.golabe.library.DefaultValue.DEFAULT_SELECTED;
import static top.golabe.library.DefaultValue.DEFAULT_VALUE;

public class BottomNavigationViewBuilder {
    private static final String TAG = "BottomNavigationViewBui";
    private BottomNavigationView mBnView;
    private Activity activity;
    private List<Class> fragments;
    private Map<Integer, Fragment> frgMap;
    private int containerId;
    private int defaultSelected;
    private OnNavigationItemSelectedListener onNavigationItemSelectedListener;
    private FragmentManager fragmentManager;

    public BottomNavigationViewBuilder(final Builder builder) {
        this.activity = builder.activity.get();
        this.mBnView = builder.bottomNavigationView;
        this.containerId = builder.containerId;
        this.fragments = builder.fragments;
        this.defaultSelected = builder.defaultSelected;
        this.onNavigationItemSelectedListener = builder.onNavigationItemSelectedListener;
        mBnView.setNavigationHeight(builder.navigationHeight);
        mBnView.addItemView(builder.items);
        mBnView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        mBnView.setOnReselectListener(builder.onReselectListener);
        mBnView.setDefaultSelected(defaultSelected);
        mBnView.setBgColor(builder.bgColor);
        if (fragments != null && fragments.size() > 0) {
            if (builder.items.size() == fragments.size()) {
                initFragments();
            }
        }

        mBnView.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @Override
            public void onNavigationItemSelected(int position) {
                if (fragments != null && fragments.size() > 0) {
                    if (builder.items.size() == fragments.size()) {
                        switchFragment(position);
                    }
                }
                if (onNavigationItemSelectedListener != null) {
                    onNavigationItemSelectedListener.onNavigationItemSelected(position);
                }
            }
        });
    }


    @SuppressLint("UseSparseArrays")
    private void initFragments() {
        frgMap = new HashMap<>();
        fragmentManager = ((AppCompatActivity) activity).getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(containerId, newInstanceFrag(defaultSelected))
                .commit();
    }

    private Fragment newInstanceFrag(int index) {
        Fragment calsss = null;
        if (fragments != null && fragments.size() > 0) {
            try {
                calsss = (Fragment) fragments.get(index).newInstance();
                frgMap.put(index, calsss);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return calsss;
    }

    private void switchFragment(int index) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        hideFrg(ft);
        Fragment frg = null;

        frg = frgMap.get(index);
        if (frg != null) {
            ft.show(frg);
        } else {
            Fragment fragments = newInstanceFrag(index);
            ft.add(containerId, fragments);
        }
        ft.commit();
    }

    private void hideFrg(FragmentTransaction ft) {
        if (frgMap != null && frgMap.size() > 0) {
            for (Map.Entry<Integer, Fragment> entry : frgMap.entrySet()) {
                ft.hide(entry.getValue());
            }
        }

    }

    public static class Builder {
        private BottomNavigationView bottomNavigationView;
        private List<BottomNavigationItemBuilder> items = null;
        private WeakReference<Activity> activity;
        private int defaultSelected = DEFAULT_SELECTED;
        private OnNavigationItemSelectedListener onNavigationItemSelectedListener;
        private OnReselectListener onReselectListener;
        private float navigationHeight = DEFAULT_NAVIGATION_HEIGHT;
        private int bgColor = DEFAULT_VALUE;
        private int containerId;
        private List<Class> fragments = null;

        public Builder(Activity activity) {
            this.activity = new WeakReference<>(activity);
            items = new ArrayList<>();
            fragments = new ArrayList<>();
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

        public Builder fragments(@IdRes int containerId, Class... fragments) {
            this.containerId = containerId;
            this.fragments.addAll(Arrays.asList(fragments));
            return this;
        }

        public BottomNavigationViewBuilder build() {
            return new BottomNavigationViewBuilder(this);

        }
    }
}
