package top.golabe.library.utils;

import android.content.Context;

public class Dimens {

    public static int dp2px(Context context,float dp){
        float v = context.getResources().getDisplayMetrics().density;
        return (int) (v*dp+0.5F);
    }
}
