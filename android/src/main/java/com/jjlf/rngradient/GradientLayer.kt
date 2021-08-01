package com.jjlf.rngradient

import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.graphics.withMatrix
import kotlin.math.max
import kotlin.math.min

class GradientLayer : Drawable(){
    
    companion object{
        const val LINEAR = 0
        const val RADIAL = 1
        const val SWEEP = 2
        const val AXIS_X = 1
        const val AXIS_Y = 2
        const val AXIS_Z = 3
        const val TO_WIDTH = "width"
        const val TO_HEIGHT = "height"
        const val TO_MIN = "min"
        const val TO_MAX = "max"
        const val TO_NONE = "none"
    }

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mRotationX  = 0f
    private var mRotationY  = 0f
    private var mRotationZ  = 0f
    private var mRotationOrder = intArrayOf(AXIS_Z, AXIS_X, AXIS_Y)
    private var mTranslationX  = 0f
    private var mTranslationY  = 0f
    private var mTranslationPlusX  = 0f
    private var mTranslationPlusY  = 0f
    private var mIsTranslationPercent  = false
    private var mScaleX   = 1f
    private var mScaleY   = 1f
    private var mInsetX = 0f
    private var mInsetY = 0f
    private var mBaseRect = RectF()
    private var mRect = RectF()
    private val mCanvasMatrix = Matrix()


    private var mType = LINEAR
    private var mPositions : FloatArray? = null
    private var mColors = intArrayOf(Color.WHITE,Color.BLACK)
    private var mStartX = 0.5f
    private var mStartY = 0f
    private var mEndX = 0.5f
    private var mEndY = 1f
    private var mRadius = 0.5f
    private var mCenterX = 0.5f
    private var mCenterY = 0.5f
    private var mSweepRotation = 0f
    private var mRadiusTarget = TO_MIN
    private val mSweepMatrix = Matrix()
    private var mTileMode = Shader.TileMode.CLAMP

    init {
        mPaint.style = Paint.Style.FILL
    }
    fun setTileMode(mode:Shader.TileMode): GradientLayer {
        mTileMode = mode
        return this
    }

    //MARK: Radial ,Sweep

    fun setRadius(v:Float,target:String = TO_MIN): GradientLayer {
        mRadius = if(v < 0f) 0f else v
        mRadiusTarget = target
        return this
    }
    fun setCenterX(v:Float): GradientLayer {
        mCenterX = v
        return this
    }
    fun setCenterY(v:Float): GradientLayer {
        mCenterY = v
        return this
    }
    fun setSweepRotation(v:Float): GradientLayer {
        mSweepRotation = v
        return this
    }

    //MARK: Linear
    fun setStartX(v:Float): GradientLayer {
        mStartX = v
        return this
    }
    fun setStartY(v:Float): GradientLayer {
        mStartY = v
        return this
    }
    fun setEndX(v:Float): GradientLayer {
        mEndX = v
        return this
    }
    fun setEndY(v:Float): GradientLayer {
        mEndY = v
        return this
    }

    fun setType(t:Int): GradientLayer {
        mType = t
        return this
    }
    fun setColors(c:IntArray): GradientLayer {
        mColors = c
        return this
    }
    fun setPositions(p:FloatArray?): GradientLayer {
        mPositions = p
        return this
    }

    fun setInset(dx:Float,dy:Float): GradientLayer {
        mInsetX = dx
        mInsetY = dy
        return this
    }

    //MARK: Transform

    fun setScale(sx:Float,sy:Float): GradientLayer {
        mScaleX = sx
        mScaleY = sy
        return this
    }

    fun setRotationZ(degrees: Float) : GradientLayer {
        mRotationZ = degrees

        return this
    }
    fun setRotationX(degrees: Float) : GradientLayer {
        mRotationX = degrees
        return this
    }
    fun setRotationY(degrees: Float) : GradientLayer {
        mRotationY = degrees
        return this
    }
    fun setRotationOrder(f: Int,s:Int,t:Int) : GradientLayer {
        if(f !in 1..3){ return this }
        if(t !in 1..3){ return this }
        if(s !in 1..3){ return this }
        if((f == s || f == t) || (s == t)){ return this }
        mRotationOrder = intArrayOf(f,s,t)
        return this
    }
    //pixels
    fun setTranslation(dx:Float,dy:Float): GradientLayer {
        mIsTranslationPercent = false
        mTranslationX = dx
        mTranslationY = dy
        return this
    }

    fun setTranslation(percentX:Float,percentY:Float, plusX:Float = 0f,plusY:Float = 0f): GradientLayer {
        mTranslationX = percentX
        mTranslationY = percentY
        mTranslationPlusX = plusX
        mTranslationPlusY = plusY
        mIsTranslationPercent = true
        return this
    }


    private var mBoundsX = 0f
    private var mBoundsY = 0f
    private var mBoundsWidth = 0f
    private var mBoundsHeight = 0f
    private var mIsBoundsDynamically = false
    private var mIsBoundsPercentPos = false
    private var mIsBoundsPercentSize = false
    fun setBoundsDynamically(x:Float,y:Float,width:Float,height:Float,percentPos:Boolean = false,percentSize:Boolean = false) : GradientLayer {
        mIsBoundsDynamically = true
        mIsBoundsPercentPos = percentPos
        mIsBoundsPercentSize = percentSize
        mBoundsX = x
        mBoundsY = y
        mBoundsWidth = width
        mBoundsHeight = height
        return this
    }


    override fun onBoundsChange(bounds: Rect?) {
        bounds?.let{

            if(mIsBoundsPercentPos){
                mBoundsX *= bounds.width()
                mBoundsY *= bounds.height()
            }
            if(mIsBoundsPercentSize){
                mBoundsWidth *= bounds.width()
                mBoundsHeight *= bounds.height()
            }
            if(mIsBoundsDynamically){
                mBaseRect.set(mBoundsX,mBoundsY,mBoundsX+mBoundsWidth,mBoundsY+mBoundsHeight)
            }else{
                mBaseRect.set(it)
            }


        }
    }


    override fun draw(canvas: Canvas) {
        if(bounds.width() > 0f && bounds.height() >0f) {

            setupRect()
            setupShader()
            setupCanvasMatrix()

            canvas.withMatrix(mCanvasMatrix){

                drawRect(mRect,mPaint)
            }
        }
    }
    private fun setupShader(){
        when(mType){
            RADIAL ->{
                val cx = mCenterX * mRect.width()
                val cy = mCenterY * mRect.height()
                val r = when(mRadiusTarget){
                    TO_MIN -> mRadius * min(mRect.width(),mRect.height())
                    TO_MAX -> mRadius * max(mRect.width(),mRect.height())
                    TO_WIDTH -> mRadius * mRect.width()
                    TO_HEIGHT -> mRadius * mRect.height()
                    else -> mRadius
                }
                mPaint.shader = RadialGradient(cx,cy,r,mColors,mPositions,mTileMode)
            }
            SWEEP ->{
                val cx = mCenterX * mRect.width()
                val cy = mCenterY * mRect.height()
                mSweepMatrix.reset()
                mSweepMatrix.postRotate(mSweepRotation,cx,cy)
                mPaint.shader = SweepGradient(cx,cy,mColors,mPositions)
                mPaint.shader.setLocalMatrix(mSweepMatrix)
            }
            else ->{
                val x1 = mStartX * mRect.width()
                val y1 = mStartY * mRect.height()
                val x2 = mEndX * mRect.width()
                val y2 = mEndY * mRect.height()
                mPaint.shader = LinearGradient(x1,y1,x2,y2,mColors,mPositions,mTileMode)
            }
        }
    }
    private fun setupRect(){
        mRect.set(mBaseRect)
        mRect.inset( mInsetX, mInsetY)

    }
    private val mCamera = Camera()
    private fun setupCanvasMatrix(){
        var transX = mTranslationX
        var transY = mTranslationY
        if(mIsTranslationPercent){
            transX = (mTranslationX * bounds.width()) + mTranslationPlusX
            transY = (mTranslationY * bounds.height()) + mTranslationPlusY
        }

        mCanvasMatrix.reset()
        mCamera.save()

        for(e in mRotationOrder){
            if(e == AXIS_Z){
                mCamera.rotate(0f,0f, -mRotationZ)
            }
            if(e == AXIS_X){
                mCamera.rotate(-mRotationX,0f, 0f)
            }
            if(e == AXIS_Y){
                mCamera.rotate(0f,-mRotationY, 0f)
            }
        }

        mCamera.getMatrix(mCanvasMatrix)
        mCamera.restore()

        mCanvasMatrix.postScale(mScaleX,mScaleY)
        mCanvasMatrix.preTranslate(-mBaseRect.centerX(),-mBaseRect.centerY())
        mCanvasMatrix.postTranslate(mBaseRect.centerX(),mBaseRect.centerY())
        mCanvasMatrix.postTranslate(transX,transY)

    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
        invalidateSelf()
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
        invalidateSelf()
    }




}