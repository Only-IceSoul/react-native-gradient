package com.jjlf.rngradient

import android.content.Context
import android.graphics.Color
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.uimanager.*
import com.jjlf.rngradient.GradientHelper
import com.jjlf.rngradient.LinearGradientView
import com.jjlf.rngradient.SweepGradientView


class SweepGradientViewManager : ViewGroupManager<SweepGradientView>() {

    override fun getName(): String {
        return "ConicGradient"
    }

    override fun createViewInstance(reactContext: ThemedReactContext): SweepGradientView {
        return SweepGradientView(reactContext as Context)
    }

    @Override
    @ReactProp(name = ViewProps.BACKGROUND_COLOR, defaultInt = Color.TRANSPARENT, customType = "Color")
    override fun setBackgroundColor(view: SweepGradientView, c:Int){}

    @ReactProp(name = "startPoint")
    fun setStartPoint(view: LinearGradientView, m:ReadableMap?){}
    @ReactProp(name = "endPoint",)
    fun setEndPoint(view: LinearGradientView, m:ReadableMap?){}

    @ReactProp(name = "centerX",defaultFloat = 0.5f)
     fun setCenterX(view: SweepGradientView, v:Float){
         val mDrawable = view.getDrawable()
        mDrawable.setCenterX(v)
        mDrawable.invalidateSelf()

    }
    @ReactProp(name = "centerY",defaultFloat = 0.5f)
    fun setCenterY(view: SweepGradientView, v:Float){
        val mDrawable = view.getDrawable()
        mDrawable.setCenterY(v)
        mDrawable.invalidateSelf()
    }
    @ReactProp(name = "conicRotation",defaultFloat = 0f)
    fun setConicRotation(view: SweepGradientView, v:Float){
        val mDrawable = view.getDrawable()
        mDrawable.setSweepRotation(v)
        mDrawable.invalidateSelf()
    }

    @ReactProp(name = "colors")
    fun setColors(view: SweepGradientView, colors: ReadableArray?)  {
        val mDrawable = view.getDrawable()
        val c = if(colors != null) GradientHelper.toIntArray(colors) else intArrayOf(
            Color.WHITE,
            Color.BLACK)
        mDrawable.setColors(c)
        mDrawable.invalidateSelf()
    }
    @ReactProp(name = "positions")
    fun setPositions(view: SweepGradientView, positions: ReadableArray?)  {
        val mDrawable = view.getDrawable()
        val p = if(positions != null) GradientHelper.toFloatArray(positions) else null
        mDrawable.setPositions(p)
        mDrawable.invalidateSelf()
    }

}