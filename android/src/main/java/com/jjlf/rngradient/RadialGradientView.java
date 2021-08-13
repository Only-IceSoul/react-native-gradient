package com.jjlf.rngradient;

import android.content.Context;
import android.view.ViewGroup;

public class RadialGradientView extends ViewGroup {

    private final GradientLayer mDrawable = new GradientLayer();
    RadialGradientView(Context context){
        super(context);
        setBackground(mDrawable);
        setClipChildren(false);
        mDrawable.setType(GradientLayer.RADIAL);
    }

    GradientLayer getDrawable() {
        return mDrawable;
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
