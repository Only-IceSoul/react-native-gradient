package com.jjlf.rngradient;

import android.graphics.Color;
import android.graphics.Shader;
import android.util.TypedValue;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;

public class RadialGradientViewManager extends ViewGroupManager<RadialGradientView> {


    @Override
    public String getName() { return "RadialGradient"; }


    @Override
    protected RadialGradientView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new RadialGradientView(reactContext);
    }

    @Override
    @ReactProp(
            name = ViewProps.BACKGROUND_COLOR,
            defaultInt = Color.TRANSPARENT,
            customType = "Color")
    public void setBackgroundColor(@NonNull RadialGradientView view, int backgroundColor) {

    }

    @ReactProp(name = "startPoint")
    public void setStartPoint(RadialGradientView view , ReadableMap m){}
    @ReactProp(name = "endPoint")
    public void setEndPoint(RadialGradientView view , ReadableMap m){}

    @ReactProp(name = "centerX",defaultFloat = 0.5f)
    public void setCenterX(RadialGradientView view , float v){
        view.getDrawable().setCenterX(v);
        view.getDrawable().invalidateSelf();
    }
    @ReactProp(name = "centerY",defaultFloat = 0.5f)
    public void setCenterY(RadialGradientView view , float v){
        view.getDrawable().setCenterY(v);
        view.getDrawable().invalidateSelf();
    }
    @ReactProp(name = "tileMode")
    public void setTileMode(RadialGradientView view , String v){
        Shader.TileMode mode = "mirror".equals(v) ? Shader.TileMode.MIRROR
                : ( "repeat".equals(v) ? Shader.TileMode.REPEAT : Shader.TileMode.CLAMP );
        view.getDrawable().setTileMode(mode);
        view.getDrawable().invalidateSelf();
    }
    @ReactProp(name = "radius")
    public void setRadius(RadialGradientView view ,ReadableMap v){
        float r = (float) ModUtil.getDouble(v,"value",0.5);
        String m =  ModUtil.getString(v,"multiplier","min");

        String t = "none";
        switch(m){
                case "max" :
                    t =  GradientLayer.TO_MAX;
                    break;
                case "width" :
                    t =  GradientLayer.TO_WIDTH;
                    break;
                case"height" :
                    t = GradientLayer.TO_HEIGHT;
                    break;
                case"min" :
                    t = GradientLayer.TO_MIN;
                    break;
                default:
                    r = toDip(r,view);
                    t = GradientLayer.TO_NONE;
                    break;
        }

        view.getDrawable().setRadius(r,t);
        view.getDrawable().invalidateSelf();
    }


    @ReactProp(name = "colors")
    public void setColors(RadialGradientView view, ReadableArray colors)  {
        int[] c = colors != null ? ModUtil.toIntArray(colors) : new int[] { Color.WHITE, Color.BLACK};
        view.getDrawable().setColors(c);
        view.getDrawable().invalidateSelf();
    }
    @ReactProp(name = "positions")
    public void setPositions(RadialGradientView view, ReadableArray positions)  {
        float[] p = positions != null ? ModUtil.toFloatArray(positions) : null;
        view.getDrawable().setPositions(p);
        view.getDrawable().invalidateSelf();
    }
    private  float toDip(float num, RadialGradientView view ){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,num,view.getResources().getDisplayMetrics());
    }
}
