package top.golabe.library.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import top.golabe.library.callback.OnNavigationItemSelectedListener;
import top.golabe.library.callback.OnReselectListener;
import top.golabe.library.utils.Dimens;

import static top.golabe.library.DefaultValue.DEFAULT_VALUE;

public class BottomNavigationView extends LinearLayout {
    private static final String TAG = "BottomNavigationView";
    private float navigationHeight;
    private int defaultSelected;
    private BottomNavigationItem itemView;

    public void setNavigationHeight(float navigationHeight) {
        this.navigationHeight = navigationHeight;
        invalidate();
    }

    private OnReselectListener onReselectListener;

    public void setOnReselectListener(OnReselectListener listener) {
        this.onReselectListener = listener;
    }

    private OnNavigationItemSelectedListener onNavigationItemSelectedListener;

    public void setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener listener) {
        this.onNavigationItemSelectedListener = listener;
    }

    public BottomNavigationView(Context context) {
        this(context, null);
    }

    public BottomNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
    }


    private List<Integer> iconNormal = new ArrayList<>();
    private List<Integer> iconSelected = new ArrayList<>();
    private List<Integer> titleColorNormal = new ArrayList<>();
    private List<Integer> titleColorSelected = new ArrayList<>();
    private List<Integer> normalTint = new ArrayList<>();
    private List<Integer> selectedTint = new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int childCount = getChildCount();
        int w = getMeasuredWidth() / childCount;
        int h = getMeasuredHeight();
        for (int i = 0; i < childCount; i++) {
            itemView = (BottomNavigationItem) getChildAt(i);
            iconNormal.add(itemView.getIconNormal());
            iconSelected.add(itemView.getIconSelected());
            titleColorNormal.add(itemView.getTitleColorNormal());
            titleColorSelected.add(itemView.getTitleColorSelected());
            normalTint.add(itemView.getNormalTint());
            selectedTint.add(itemView.getSelectedTint());
            LayoutParams params = new LayoutParams(w, h);
            params.gravity = Gravity.CENTER;
            itemView.setLayoutParams(params);
            click(i);

        }

        if (isfirst) {
            setCurrentPosition(defaultSelected);
            isfirst = false;
        }
    }

    private boolean isfirst = true;
    private int currentPosition;

    private void click(final int position) {
        itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition != position) {
                    setCurrentPosition(position);
                    if (onNavigationItemSelectedListener != null) {
                        onNavigationItemSelectedListener.onNavigationItemSelected(position);
                    }
                } else {
                    if (onReselectListener != null) {
                        onReselectListener.onReselect(position);
                    }
                }

            }
        });
    }

    private void updateTabSelection(int position) {
        for (int i = 0; i < getChildCount(); ++i) {
            BottomNavigationItem item = (BottomNavigationItem) getChildAt(i);
            final boolean isSelect = i == position;
            item.setImageRes(isSelect ? iconSelected.get(i) : iconNormal.get(i));
            item.setTitleColor(isSelect ? titleColorSelected.get(i) : titleColorNormal.get(i));
            item.setImageTint(isSelect ? iconSelected.get(i) : iconNormal.get(i), isSelect ? selectedTint.get(i) : normalTint.get(i));
        }
    }


    public void setCurrentPosition(int position) {
        this.currentPosition = position;
        updateTabSelection(position);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int hModel = MeasureSpec.getMode(heightMeasureSpec);
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        if (hModel == MeasureSpec.AT_MOST) {
            setMeasuredDimension(w, Dimens.dp2px(getContext(), navigationHeight));
        } else {
            setMeasuredDimension(w, h);
        }
    }

    public void setDefaultSelected(int defaultSelected) {
        this.defaultSelected = defaultSelected;
    }

    public void setBgColor(int bgColor) {
        if (bgColor != DEFAULT_VALUE) {
            setBackgroundColor(bgColor);
        }
    }


    public void addItemView(List<BottomNavigationItemBuilder> items) {
        for (int i = 0; i < items.size(); i++) {
            BottomNavigationItem bottomNavigationItem = items.get(i).getBottomNavigationItem();
            addView(bottomNavigationItem, i);

        }
    }
}



