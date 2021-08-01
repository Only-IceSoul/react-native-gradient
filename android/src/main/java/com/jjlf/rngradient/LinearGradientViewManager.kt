package com.jjlf.rngradient

import android.content.Context
import android.graphics.Color
import android.graphics.Shader
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.uimanager.*


class LinearGradientViewManager : ViewGroupManager<LinearGradientView>() {

    override fun getName(): String {
        return "LinearGradient"
    }

    override fun createViewInstance(reactContext: ThemedReactContext): LinearGradientView {
        return LinearGradientView(reactContext as Context)
    }
    @Override
    @ReactProp(
        name = ViewProps.BACKGROUND_COLOR,
        defaultInt = Color.TRANSPARENT,
        customType = "Color")
    override fun setBackgroundColor(view: LinearGradientView, c:Int){}

    @ReactProp(name = "startPoint")
     fun setStartPoint(view: LinearGradientView, m:ReadableMap?){
        val x = try{ m!!.getDouble("x") }catch (e:Error){ 0.5 }.toFloat()
        val y = try{ m!!.getDouble("y") }catch (e:Error){ 0.0 }.toFloat()
         val mDrawable = view.getDrawable()
        mDrawable.setStartX(x)
        mDrawable.setStartY(y)
        mDrawable.invalidateSelf()

    }

    @ReactProp(name = "endPoint",)
    fun setEndPoint(view: LinearGradientView, m:ReadableMap?){
        val x = try{ m!!.getDouble("x") }catch (e:Error){ 0.5 }.toFloat()
        val y = try{ m!!.getDouble("y") }catch (e:Error){ 1.0 }.toFloat()
        val mDrawable = view.getDrawable()
        mDrawable.setEndX(x)
        mDrawable.setEndY(y)
        mDrawable.invalidateSelf()
    }

    @ReactProp(name = "tileMode")
    fun setTileMode(view: LinearGradientView, v:String?){
        val mDrawable = view.getDrawable()
        val mode = when(v ?: "clamp"){
            "mirror" -> Shader.TileMode.MIRROR
            "repeat" -> Shader.TileMode.REPEAT
            else -> Shader.TileMode.CLAMP
        }
        mDrawable.setTileMode(mode)
        mDrawable.invalidateSelf()
    }
    @ReactProp(name = "colors")
    fun setColors(view: LinearGradientView, colors: ReadableArray?)  {
        val mDrawable = view.getDrawable()
        val c = if(colors != null) GradientHelper.toIntArray(colors) else intArrayOf(
            Color.WHITE,
            Color.BLACK)
        mDrawable.setColors(c)
        mDrawable.invalidateSelf()
    }
    @ReactProp(name = "positions")
    fun setPositions(view: LinearGradientView, positions: ReadableArray?)  {
        val mDrawable = view.getDrawable()
        val p = if(positions != null) GradientHelper.toFloatArray(positions) else null
        mDrawable.setPositions(p)
        mDrawable.invalidateSelf()
    }

}