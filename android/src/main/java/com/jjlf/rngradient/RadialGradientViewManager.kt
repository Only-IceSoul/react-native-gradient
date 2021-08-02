package com.jjlf.rngradient

import android.content.Context
import android.graphics.Color
import android.graphics.Shader
import android.util.Log
import android.util.TypedValue
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.uimanager.*


class RadialGradientViewManager : ViewGroupManager<RadialGradientView>() {

    override fun getName(): String {
        return "RadialGradient"
    }

    override fun createViewInstance(reactContext: ThemedReactContext): RadialGradientView {
        return RadialGradientView(reactContext as Context)
    }
    @Override
    @ReactProp(
        name = ViewProps.BACKGROUND_COLOR,
        defaultInt = Color.TRANSPARENT,
        customType = "Color")
    override fun setBackgroundColor(view: RadialGradientView, c:Int){}

    @ReactProp(name = "startPoint")
    fun setStartPoint(view: RadialGradientView, m:ReadableMap?){}
    @ReactProp(name = "endPoint",)
    fun setEndPoint(view: RadialGradientView, m:ReadableMap?){}

    @ReactProp(name = "centerX",defaultFloat = 0.5f)
    fun setCenterX(view: RadialGradientView, v:Float){
        val mDrawable = view.getDrawable()
        mDrawable.setCenterX(v)
        mDrawable.invalidateSelf()

    }
    @ReactProp(name = "centerY",defaultFloat = 0.5f)
    fun setCenterY(view: RadialGradientView, v:Float){
        val mDrawable = view.getDrawable()
        mDrawable.setCenterY(v)
        mDrawable.invalidateSelf()
    }
    @ReactProp(name = "tileMode")
    fun setTileMode(view: RadialGradientView, v:String?){
        val mDrawable = view.getDrawable()
        val mode = when(v ?: "clamp"){
            "mirror" -> Shader.TileMode.MIRROR
            "repeat" -> Shader.TileMode.REPEAT
            else -> Shader.TileMode.CLAMP
        }
        mDrawable.setTileMode(mode)
        mDrawable.invalidateSelf()
    }
    @ReactProp(name = "radius")
    fun setRadius(view: RadialGradientView, v:ReadableMap?){
        var r = try { v!!.getDouble("value") }catch (e:Error){ 1.0 }.toFloat()
        val m =  try { v!!.getString("multiplier") ?: "none" }catch (e:Error){ "none" }

        val mDrawable = view.getDrawable()

        val t = when(m){
            "max" -> GradientLayer.TO_MAX
            "width"-> GradientLayer.TO_WIDTH
            "height" -> GradientLayer.TO_HEIGHT
            "min" -> GradientLayer.TO_MIN
            else -> {
                r = toDip(r,view)
                GradientLayer.TO_NONE
            }
        }

        mDrawable.setRadius(r,t)
        mDrawable.invalidateSelf()
    }


    @ReactProp(name = "colors")
    fun setColors(view: RadialGradientView, colors: ReadableArray?)  {
        val mDrawable = view.getDrawable()
        val c = if(colors != null) GradientHelper.toIntArray(colors) else intArrayOf(
            Color.WHITE,
            Color.BLACK)
        mDrawable.setColors(c)
        mDrawable.invalidateSelf()
    }
    @ReactProp(name = "positions")
    fun setPositions(view: RadialGradientView, positions: ReadableArray?)  {
        val mDrawable = view.getDrawable()
        val p = if(positions != null) GradientHelper.toFloatArray(positions) else null
        mDrawable.setPositions(p)
        mDrawable.invalidateSelf()
    }
    private fun toDip(num:Float, view: RadialGradientView): Float{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,num,view.resources.displayMetrics)
    }
}