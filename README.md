# BottomNavigationView 底部导航栏 第一次写,代码有点垃圾

<image src="https://github.com/Golabe/bottomnavigation/blob/master/gif/description.gif?raw=true" width="260"> </iamge>
<image src="https://github.com/Golabe/bottomnavigation/blob/master/gif/viewPager.gif?raw=true" width="260"> </iamge>
<image src="https://github.com/Golabe/bottomnavigation/blob/master/gif/iconSize.gif?raw=true" width="260"> </iamge>



## gradle 添加
```xml
implementation 'top.golabe.bottomnavigation:library:1.0.0'
```
### fragments()和viewPager()方法不能同时使用
### fragments()和viewPager()方法不能同时使用
### fragments()和viewPager()方法不能同时使用

## fragment 使用
在xml布局里面添加 BottomNavigationView容器
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
    <FrameLayout
        android:id="@+id/fl_container"
        android:layout_above="@id/bnv_bottom_navigation_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
    <top.golabe.library.widget.BottomNavigationView
        android:elevation="@dimen/bottom_navigation_elevation"
        android:id="@+id/bnv_bottom_navigation_view"
        android:layout_alignParentBottom="true"
        android:background="?android:attr/windowBackground"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
</RelativeLayout>
```

在java代码里面配置属性
```java
   BottomNavigationViewBuilder navigationViewBuilder = new BottomNavigationViewBuilder.Builder(this)
                .findView(R.id.bnv_bottom_navigation_view)
                .addItem(new BottomNavigationItemBuilder.Builder(this)
                        .title("精选")
                        .icon(R.mipmap.auslese, R.mipmap.auslese)
                        .tint(Color.parseColor("#8a8a8a"), Color.parseColor("#2c2c2c"))
                        .titleColor(Color.parseColor("#bfbfbf"), Color.parseColor("#2c2c2c"))
                        .build())
                .addItem(new BottomNavigationItemBuilder.Builder(this)
                        .tint(Color.parseColor("#8a8a8a"), Color.parseColor("#2c2c2c"))
                        .titleColor(Color.parseColor("#bfbfbf"), Color.parseColor("#2c2c2c"))
                        .title("Search")
                        .icon(R.mipmap.search, R.mipmap.search)
                        .build())
                .addItem(new BottomNavigationItemBuilder.Builder(this)
                        .title("Map")
                        .tint(Color.parseColor("#8a8a8a"), Color.parseColor("#2c2c2c"))
                        .titleColor(Color.parseColor("#bfbfbf"), Color.parseColor("#2c2c2c"))
                        .icon(R.mipmap.map, R.mipmap.map)
                        .build())
                .addItem(new BottomNavigationItemBuilder.Builder(this)
                        .title("Me")
                        .tint(Color.parseColor("#8a8a8a"), Color.parseColor("#2c2c2c"))
                        .titleColor(Color.parseColor("#bfbfbf"), Color.parseColor("#2c2c2c"))
                        .icon(R.mipmap.me, R.mipmap.me)
                        .build())
                .defaultSelected(0)
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
                .navigationHeight(52F)
                .build();
```


## viewPager 使用
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
    <android.support.v4.view.ViewPager
        android:id="@+id/vp_container"
        android:layout_above="@id/bnv_bottom_navigation_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
    <top.golabe.library.widget.BottomNavigationView
        android:elevation="@dimen/bottom_navigation_elevation"
        android:id="@+id/bnv_bottom_navigation_view"
        android:layout_alignParentBottom="true"
        android:background="?android:attr/windowBackground"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>


</RelativeLayout>
```
```java
 BottomNavigationViewBuilder navigationViewBuilder = new BottomNavigationViewBuilder.Builder(this)
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
                .bgColor(Color.WHITE).setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
                    @Override
                    public void onNavigationItemSelected(int position) {
                        Toast.makeText(MainActivity.this, "选中" + position, Toast.LENGTH_SHORT).show();
                    }
                })
                .viewPager(R.id.vp_container,FragmentA.class,FragmentB.class,FragmentC.class,FragmentD.class)
                .defaultSelected(2)
                .navigationHeight(52F)
                .build();
```

## 方法

### BottomNavigationItemBuilder 方法
    title(String)
    tint(int,int)
    titleSize(float)
    titleColor(int,int)
    icon(int,int)
    iconSize(float)

### BottomNavigationViewBuilder 方法
    findView(int)
    addItem(BottomNavigationItemBuilder)
    setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener)
    setOnReselectListener(OnReselectListener)
    defaultSelected(int)
    bgColor(int)
    navigationHeight(int)
    fragments(int,class)
    viewPager(int,class)
    

#### 不设置icon()
<div  >
<image src="https://github.com/Golabe/bottomnavigation/blob/master/gif/no_icon.png?raw=true" width="240"/></div>


#### 不设置title()
<div  >
<image src="https://github.com/Golabe/bottomnavigation/blob/master/gif/no_title.png?raw=true" width="240"/>
</div>


