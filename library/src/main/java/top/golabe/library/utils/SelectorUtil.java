package top.golabe.library.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

public class SelectorUtil {

    public static StateListDrawable selectorDrawable(Context context, int normal, int selected) {
        StateListDrawable sld = new StateListDrawable();
        Drawable normalDraw = context.getResources().getDrawable(normal);
        Drawable selectedDraw = context.getResources().getDrawable(selected);
        sld.addState(new int[]{android.R.attr.state_activated},selectedDraw);
        sld.addState(new int[]{-android.R.attr.state_activated}, normalDraw);
        return sld;
    }
}
