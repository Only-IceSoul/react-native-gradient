package com.jjlf.rngradient;

import android.graphics.Color;
import android.graphics.Shader;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;



public class LinearGradientViewManager extends ViewGroupManager<LinearGradientView> {
    

    @Override
    public String getName() { return "LinearGradient"; }


    @Override
    protected LinearGradientView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new LinearGradientView(reactContext);
    }

    @Override
    @ReactProp(
            name = ViewProps.BACKGROUND_COLOR,
            defaultInt = Color.TRANSPARENT,
            customType = "Color")
    public void setBackgroundColor(@NonNull LinearGradientView view, int backgroundColor) {
        
    }

    @ReactProp(name = "startPoint")
    public void setStartPoint(LinearGradientView view, ReadableMap m){
        float x = (float) ModUtil.getDouble(m,"x",0.5);
        float y = (float) ModUtil.getDouble(m,"y",0.0);
        view.getDrawable().setStartX(x);
        view.getDrawable().setStartY(y);
        view.getDrawable().invalidateSelf();

    }

    @ReactProp(name = "endPoint")
    public void setEndPoint(LinearGradientView view, ReadableMap m){
        float x = (float) ModUtil.getDouble(m,"x",0.5);
        float y = (float) ModUtil.getDouble(m,"y",1.0);
        view.getDrawable().setEndX(x);
        view.getDrawable().setEndY(y);
        view.getDrawable().invalidateSelf();
    }

    @ReactProp(name = "tileMode")
    public void setTileMode(LinearGradientView view, String v){
        Shader.TileMode mode = "mirror".equals(v) ? Shader.TileMode.MIRROR
                : ( "repeat".equals(v) ? Shader.TileMode.REPEAT : Shader.TileMode.CLAMP );
        view.getDrawable().setTileMode(mode);
        view.getDrawable().invalidateSelf();
    }
    @ReactProp(name = "colors")
    public void setColors(LinearGradientView view, ReadableArray colors)  {
        int[] c = colors != null ? ModUtil.toIntArray(colors) : new int[] { Color.WHITE, Color.BLACK};
        view.getDrawable().setColors(c);
        view.getDrawable().invalidateSelf();
    }
    @ReactProp(name = "positions")
    public void setPositions(LinearGradientView view, ReadableArray positions)  {
        float[] p = positions != null ? ModUtil.toFloatArray(positions) : null;
        view.getDrawable().setPositions(p);
        view.getDrawable().invalidateSelf();
    }
}
