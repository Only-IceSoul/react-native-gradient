package com.jjlf.rngradient

import android.content.Context
import android.view.ViewGroup



class RadialGradientView(context: Context): ViewGroup(context) {


    private val mDrawable = GradientLayer()
    init{
        background = mDrawable
        clipChildren = false
        mDrawable.setType(GradientLayer.RADIAL)
    }

    fun getDrawable(): GradientLayer {
        return mDrawable
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }

}