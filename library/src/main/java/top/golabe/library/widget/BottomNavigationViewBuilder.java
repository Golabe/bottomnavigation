package top.golabe.library.widget;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.golabe.library.ViewPagerAdapter;
import top.golabe.library.callback.OnNavigationItemSelectedListener;
import top.golabe.library.callback.OnReselectListener;

import static top.golabe.library.DefaultValue.DEFAULT_NAVIGATION_HEIGHT;
import static top.golabe.library.DefaultValue.DEFAULT_SELECTED;
import static top.golabe.library.DefaultValue.DEFAULT_VALUE;

public class BottomNavigationViewBuilder {
    private static final String TAG = "BottomNavigationViewBui";
    private Activity mActivity;
    private List<Class> mFragments;
    private Map<Integer, Fragment> mFragmentMap;
    private int mContainerId;
    private int mDefaultSelected;
    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;
    private FragmentManager mFragmentManager;
    private ViewPager mViewPager;
    private final BottomNavigationView mBottomNavigationView;

    public BottomNavigationViewBuilder(final Builder builder) {
        this.mActivity = builder.activity.get();
        mBottomNavigationView = builder.bottomNavigationView;
        this.mContainerId = builder.containerId;
        this.mFragments = builder.fragments;
        this.mDefaultSelected = builder.defaultSelected;
        this.mOnNavigationItemSelectedListener = builder.onNavigationItemSelectedListener;
        this.mViewPager = builder.viewPager;
        mBottomNavigationView.setNavigationHeight(builder.navigationHeight);
        mBottomNavigationView.addItemView(builder.items);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBottomNavigationView.setOnReselectListener(builder.onReselectListener);
        mBottomNavigationView.setDefaultSelected(mDefaultSelected);
        mBottomNavigationView.setBgColor(builder.bgColor);
        if (mViewPager == null && mFragments != null && mFragments.size() > 0) {
            if (builder.items.size() == mFragments.size()) {
                initFragments();
            }
        }

        if (mViewPager != null && mFragments != null && mFragments.size() > 0) {
            List<Fragment> fragmentList = new ArrayList<>();
            for (int i = 0; i < mFragments.size(); i++) {
                Fragment fragment = newInstanceFrag(i);
                fragmentList.add(fragment);
            }
            ViewPagerAdapter adapter = new ViewPagerAdapter(((FragmentActivity) mActivity).getSupportFragmentManager(), fragmentList);
            mViewPager.setAdapter(adapter);
            mViewPager.setCurrentItem(mDefaultSelected);
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
                    mBottomNavigationView.setCurrentPosition(i);
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });
        }

        mBottomNavigationView.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @Override
            public void onNavigationItemSelected(int position) {
                if (mViewPager==null&&mFragments != null && mFragments.size() > 0) {
                    if (builder.items.size() == mFragments.size()) {
                        switchFragment(position);
                    }
                }

                if (mViewPager != null && mFragments != null && mFragments.size() > 0) {
                    mViewPager.setCurrentItem(position);
                }
                if (mOnNavigationItemSelectedListener != null) {
                    mOnNavigationItemSelectedListener.onNavigationItemSelected(position);
                }
            }
        });
    }


    @SuppressLint("UseSparseArrays")
    private void initFragments() {
        mFragmentMap = new HashMap<>();
        mFragmentManager = ((AppCompatActivity) mActivity).getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(mContainerId, newInstanceFrag(mDefaultSelected))
                .commit();
    }

    private Fragment newInstanceFrag(int index) {
        Fragment fragment = null;
        if (mFragments != null && mFragments.size() > 0) {
            try {
                fragment = (Fragment) mFragments.get(index).newInstance();
                if (mViewPager == null) {
                    mFragmentMap.put(index, fragment);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return fragment;
    }

    private void switchFragment(int index) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        hideFrg(ft);
        Fragment frg = null;

        frg = mFragmentMap.get(index);
        if (frg != null) {
            ft.show(frg);
        } else {
            Fragment fragments = newInstanceFrag(index);
            ft.add(mContainerId, fragments);
        }
        ft.commit();
    }

    private void hideFrg(FragmentTransaction ft) {
        if (mFragmentMap != null && mFragmentMap.size() > 0) {
            for (Map.Entry<Integer, Fragment> entry : mFragmentMap.entrySet()) {
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

        private ViewPager viewPager = null;

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

        public Builder viewPager(@IdRes int containerId, Class... fragments) {
            this.viewPager = activity.get().findViewById(containerId);
            this.fragments.addAll(Arrays.asList(fragments));
            return this;
        }
    }
}
