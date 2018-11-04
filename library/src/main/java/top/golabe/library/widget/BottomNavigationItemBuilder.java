package top.golabe.library.widget;

import android.content.Context;
import android.support.annotation.ColorInt;

import static top.golabe.library.DefaultValue.DEFAULT_VALUE;
import static top.golabe.library.DefaultValue.DEFAULT_ICON_SIZE;
import static top.golabe.library.DefaultValue.DEFAULT_TITLE_SIZE;

public class BottomNavigationItemBuilder {

    private BottomNavigationItem mBottomNavigationItem;

    public BottomNavigationItemBuilder(Builder builder) {
        mBottomNavigationItem = new BottomNavigationItem(builder.context);
        mBottomNavigationItem.setIconAnim(builder.isIconAnim);
        mBottomNavigationItem.setSelectedTint(builder.selectedTint);
        mBottomNavigationItem.setNormalTint(builder.normalTint);
        mBottomNavigationItem.setIconNormal(builder.iconNormal);
        mBottomNavigationItem.setIconSelected(builder.iconSelected);
        mBottomNavigationItem.setIconSize(builder.iconSize);
        mBottomNavigationItem.setRipple(builder.isRipple);
        mBottomNavigationItem.setTitle(builder.title);
        mBottomNavigationItem.setTitleSize(builder.titleSize);
        mBottomNavigationItem.setTitleColorSelected(builder.titleColorSelected);
        mBottomNavigationItem.setTitleColorNormal(builder.titleColorNormal);

    }

    public BottomNavigationItem getBottomNavigationItem() {
        return mBottomNavigationItem;
    }


    public static class Builder {

        private String title = "";
        private float titleSize = DEFAULT_TITLE_SIZE;
        private int titleColorNormal =DEFAULT_VALUE;
        private int titleColorSelected =DEFAULT_VALUE;
        private int iconNormal = DEFAULT_VALUE;
        private int iconSelected = DEFAULT_VALUE;
        private int iconSize = DEFAULT_ICON_SIZE;
        private int normalTint = DEFAULT_VALUE;
        private int selectedTint = DEFAULT_VALUE;

        private boolean isRipple = true;
        private boolean isIconAnim = true;

        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder tint(@ColorInt int normalTint, @ColorInt int selectedTint) {
            this.normalTint = normalTint;
            this.selectedTint = selectedTint;
            return this;
        }

        public Builder titleSize(float titleSize) {
            this.titleSize = titleSize;
            return this;
        }

        public Builder titleColor(@ColorInt int normalColor, @ColorInt int selectedColor) {
            this.titleColorNormal = normalColor;
            this.titleColorSelected = selectedColor;
            return this;
        }

        public Builder icon(int normal, int selected) {
            this.iconNormal = normal;
            this.iconSelected = selected;
            return this;
        }

        public Builder iconSize(int iconSize) {
            this.iconSize = iconSize;
            return this;
        }


        public Builder isRipple(boolean isRipple) {
            this.isRipple = isRipple;
            return this;
        }

        public Builder isIconAnim(boolean isIconAnim) {
            this.isIconAnim = isIconAnim;
            return this;
        }

        public Builder fragment() {
            return this;
        }

        public BottomNavigationItemBuilder build() {
            return new BottomNavigationItemBuilder(this);
        }
    }
}
