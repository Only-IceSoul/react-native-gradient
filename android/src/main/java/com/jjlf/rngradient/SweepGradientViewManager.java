package com.jjlf.rngradient;

import android.graphics.Color;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;

public class SweepGradientViewManager  extends ViewGroupManager<SweepGradientView> {


    @Override
    public String getName() { return "ConicGradient"; }


    @Override
    protected SweepGradientView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new SweepGradientView(reactContext);
    }

    @Override
    @ReactProp(
            name = ViewProps.BACKGROUND_COLOR,
            defaultInt = Color.TRANSPARENT,
            customType = "Color")
    public void setBackgroundColor(@NonNull SweepGradientView view, int backgroundColor) { }
    @ReactProp(name = "startPoint")
    public void setStartPoint(SweepGradientView view , ReadableMap m){}
    @ReactProp(name = "endPoint")
    public void setEndPoint(SweepGradientView view , ReadableMap m){}

    @ReactProp(name = "centerX",defaultFloat = 0.5f)
    public void setCenterX(SweepGradientView view , float v){
        view.getDrawable().setCenterX(v);
        view.getDrawable().invalidateSelf();
    }
    @ReactProp(name = "centerY",defaultFloat = 0.5f)
    public void setCenterY(SweepGradientView view , float v){
        view.getDrawable().setCenterY(v);
        view.getDrawable().invalidateSelf();
    }
    @ReactProp(name = "conicRotation",defaultFloat = 0f)
    public void setConicRotation(SweepGradientView view,float v){
        view.getDrawable().setSweepRotation(v);
        view.getDrawable().invalidateSelf();
    }

    @ReactProp(name = "colors")
    public void setColors(SweepGradientView view, ReadableArray colors)  {
        int[] c = colors != null ? ModUtil.toIntArray(colors) : new int[] { Color.WHITE, Color.BLACK};
        view.getDrawable().setColors(c);
        view.getDrawable().invalidateSelf();
    }
    @ReactProp(name = "positions")
    public void setPositions(SweepGradientView view, ReadableArray positions)  {
        float[] p = positions != null ? ModUtil.toFloatArray(positions) : null;
        view.getDrawable().setPositions(p);
        view.getDrawable().invalidateSelf();
    }
}
