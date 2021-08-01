package com.jjlf.rngradient

import com.facebook.react.bridge.ReadableArray

object GradientHelper {

     fun toIntArray(colors: ReadableArray): IntArray{
        val list = IntArray(colors.size())
        for(i in 0 until colors.size()){
            list[i] = colors.getInt(i)
        }
        return list
    }
    fun toFloatArray(colors: ReadableArray): FloatArray{
        val list = FloatArray(colors.size())
        for(i in 0 until colors.size()){
            list[i] = colors.getDouble(i).toFloat()
        }
        return list
    }
}