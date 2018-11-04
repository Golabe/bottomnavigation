package top.golabe.library.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import top.golabe.library.R;
import top.golabe.library.utils.Dimens;

import static top.golabe.library.DefaultValue.DEFAULT_VALUE;

public class BottomNavigationItem extends LinearLayout {
    private String title;
    private float titleSize;
    private int titleColorNormal;
    private int titleColorSelected;
    private int iconNormal;
    private int iconSelected;
    private int iconSize;
    private int normalTint;
    private int selectedTint;
    private boolean isRipple;
    private boolean isIconAnim;
    public ImageView mIconView;
    public TextView mTitleView;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        if (TextUtils.isEmpty(title)) {
            mTitleView.setVisibility(GONE);
        } else {
            mTitleView.setText(title);
        }
    }

    public float getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(float titleSize) {
        this.titleSize = titleSize;
        if (!TextUtils.isEmpty(title)) {
            mTitleView.setTextSize(titleSize);
        }
    }


    public int getTitleColorNormal() {
        return titleColorNormal;
    }

    public void setTitleColorNormal(int titleColorNormal) {
        this.titleColorNormal = titleColorNormal;

    }

    public int getTitleColorSelected() {
        return titleColorSelected;
    }

    public void setTitleColorSelected(int titleColorSelected) {
        this.titleColorSelected = titleColorSelected;
    }

    public int getIconNormal() {
        return iconNormal;
    }

    public void setIconNormal(int iconNormal) {
        this.iconNormal = iconNormal;
    }

    public int getIconSelected() {
        return iconSelected;
    }

    public void setIconSelected(int iconSelected) {
        this.iconSelected = iconSelected;
    }

    public int getIconSize() {
        return iconSize;
    }

    public void setIconSize(int iconSize) {
        this.iconSize = iconSize;
        int size = Dimens.dp2px(getContext(), iconSize);
        mIconView.setLayoutParams(new LayoutParams(size, size));
    }

    public int getNormalTint() {
        return normalTint;
    }

    public void setNormalTint(int normalTint) {
        this.normalTint = normalTint;
    }

    public int getSelectedTint() {
        return selectedTint;
    }

    public void setSelectedTint(int selectedTint) {
        this.selectedTint = selectedTint;
    }

    public boolean isRipple() {
        return isRipple;
    }

    public void setRipple(boolean ripple) {
        this.isRipple = ripple;
    }

    public boolean isIconAnim() {
        return isIconAnim;
    }

    public void setIconAnim(boolean iconAnim) {
        this.isIconAnim = iconAnim;
    }

    public BottomNavigationItem(Context context) {
        super(context);
        setOrientation(VERTICAL);
        init();
    }


    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_navigation_item_view, null);
        mIconView = view.findViewById(R.id.iv_icon);
        mTitleView = view.findViewById(R.id.tv_title);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(view, params);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (iconNormal==DEFAULT_VALUE||iconSelected==DEFAULT_VALUE){
            mIconView.setVisibility(GONE);
        }else {
            mIconView.setVisibility(VISIBLE);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }


    public void setImageTint(int drawId, int color) {

        if (mIconView != null && color != DEFAULT_VALUE&&drawId!=DEFAULT_VALUE) {
            Drawable up = ContextCompat.getDrawable(getContext(), drawId);
            Drawable drawableUp = DrawableCompat.wrap(up);
            DrawableCompat.setTint(drawableUp, color);
            mIconView.setImageDrawable(drawableUp);
        }

    }

    public void setImageRes(int resId) {
        if (mIconView != null && resId != DEFAULT_VALUE) {
            mIconView.setImageResource(resId);
        }
    }

    public void setTitleColor(int color) {
        if (mTitleView != null && color != DEFAULT_VALUE) {
            mTitleView.setTextColor(color);
        }
    }
}
