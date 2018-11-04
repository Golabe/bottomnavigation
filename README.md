# BottomNavigationView 底部导航栏 第一次写,代码有点垃圾

<div align="center">
<image src="https://github.com/Golabe/bottomnavigation/blob/master/gif/description.gif?raw=true"/ width="260">
</div>


## 使用
在xml布局里面添加 BottomNavigationView容器
```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
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
    
#### 不设置icon()
<div  >
<image src="https://github.com/Golabe/bottomnavigation/blob/master/gif/no_icon.png?raw=true" width="240"/></div>


#### 不设置title()
<div  >
<image src="https://github.com/Golabe/bottomnavigation/blob/master/gif/no_title.png?raw=true" width="240"/>
</div>


