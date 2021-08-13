package com.jjlf.rngradient;

import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GradientLayer extends Drawable{

        
    static int LINEAR = 0;
    static int RADIAL = 1;
    static int SWEEP = 2;
    static int AXIS_X = 1;
    static int AXIS_Y = 2;
    static int AXIS_Z = 3;
    static String TO_WIDTH = "width";
    static String TO_HEIGHT = "height";
    static String TO_MIN = "min";
    static String TO_MAX = "max";
    static String TO_NONE = "none";
       

private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
private float mRotationX  = 0f;
private float mRotationY  = 0f;
private float mRotationZ  = 0f;
private int[] mRotationOrder = {AXIS_Z, AXIS_X, AXIS_Y};
private float mTranslationX  = 0f;
private float mTranslationY  = 0f;
private float mTranslationPlusX  = 0f;
private float mTranslationPlusY  = 0f;
private boolean mIsTranslationPercent  = false;
private float mScaleX   = 1f;
private float mScaleY   = 1f;
private float mInsetX = 0f;
private float mInsetY = 0f;
private final RectF mBaseRect = new RectF();
private final RectF mRect = new RectF();
private final Matrix mCanvasMatrix = new Matrix();


private int mType = LINEAR;;
private float[] mPositions = null;
private int[] mColors = {Color.WHITE,Color.BLACK};
private float mStartX = 0.5f;
private float mStartY = 0f;
private float mEndX = 0.5f;
private float mEndY = 1f;
private float mRadius = 0.5f;
private float mCenterX = 0.5f;
private float mCenterY = 0.5f;
private float mSweepRotation = 0f;
private String mRadiusTarget = TO_MIN;
private final Matrix mSweepMatrix = new Matrix();
private Shader.TileMode mTileMode = Shader.TileMode.CLAMP;

    GradientLayer() {
      mPaint.setStyle(Paint.Style.FILL);
    }
    GradientLayer setTileMode(Shader.TileMode mode) {
        mTileMode = mode;
        return this;
        }

        //MARK: Radial ,Sweep

    GradientLayer setRadius(float v,String target) {
        mRadius = Math.max(v, 0f);
        mRadiusTarget = target;
        return this;
    }
        GradientLayer setCenterX(float v) {
        mCenterX = v;
        return this;
        }
        GradientLayer setCenterY(float v) {
        mCenterY = v;
        return this;
        }
        GradientLayer setSweepRotation(float v) {
        mSweepRotation = v;
        return this;
        }

        //MARK: Linear
        GradientLayer setStartX(float v) {
        mStartX = v;
        return this;
        }
        GradientLayer setStartY(float v) {
        mStartY = v;
        return this;
        }
        GradientLayer setEndX(float v) {
            mEndX = v;
            return this;
        }
        GradientLayer setEndY(float v) {
            mEndY = v;
            return this;
        }

        GradientLayer setType(int t) {
            mType = t;
            return this;
        }
        GradientLayer setColors(int[] c) {
            mColors = c;
            return this;
        }
        GradientLayer setPositions(float[] p) {
            mPositions = p;
            return this;
        }

        GradientLayer setInset(float dx,float dy) {
            mInsetX = dx;
            mInsetY = dy;
            return this;
        }

        //MARK: Transform

        GradientLayer setScale(float sx,float sy) {
            mScaleX = sx;
            mScaleY = sy;
            return this;
        }

        GradientLayer setRotationZ(float degrees)  {
            mRotationZ = degrees;
            return this;
        }
        GradientLayer setRotationX(float degrees)  {
            mRotationX = degrees;
            return this;
        }
        GradientLayer setRotationY(float degrees)  {
            mRotationY = degrees;
            return this;
        }
        GradientLayer setRotationOrder(int f,int s,int t)  {
            if(f < 1 || f > 3 ){ return this; }
            if(t < 1 || t > 3 ){ return this; }
            if(s < 1 || s > 3 ){ return this; }
            if((f == s || f == t) || (s == t)){ return this; }
            mRotationOrder = new int[]{f,s,t};
            return this;
        }
        //pixels
        GradientLayer setTranslation(float dx,float dy) {
            mIsTranslationPercent = false;
            mTranslationX = dx;
            mTranslationY = dy;
            return this;
        }

        GradientLayer setTranslation(float percentX,float percentY,float plusX,float plusY) {
            mTranslationX = percentX;
            mTranslationY = percentY;
            mTranslationPlusX = plusX;
            mTranslationPlusY = plusY;
            mIsTranslationPercent = true;
            return this;
        }


    private float mBoundsX = 0f;
    private float mBoundsY = 0f;
    private float mBoundsWidth = 0f;
    private float mBoundsHeight = 0f;
    private boolean mIsBoundsDynamically = false;
    private boolean mIsBoundsPercentPos = false;
    private boolean mIsBoundsPercentSize = false;
    GradientLayer setBoundsDynamically(float x,float y,float width,float height,boolean percentPos,boolean percentSize)  {
        mIsBoundsDynamically = true;
        mIsBoundsPercentPos = percentPos;
        mIsBoundsPercentSize = percentSize;
        mBoundsX = x;
        mBoundsY = y;
        mBoundsWidth = width;
        mBoundsHeight = height;
        return this;
    }


    @Override
    protected void onBoundsChange(Rect bounds) {
        if(bounds != null){
            if(mIsBoundsPercentPos){
                mBoundsX *= bounds.width();
                mBoundsY *= bounds.height();
            }
            if(mIsBoundsPercentSize){
                mBoundsWidth *= bounds.width();
                mBoundsHeight *= bounds.height();
            }
            if(mIsBoundsDynamically){
                mBaseRect.set(mBoundsX,mBoundsY,mBoundsX+mBoundsWidth,mBoundsY+mBoundsHeight);
            }else{
                mBaseRect.set(bounds);
            }
        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if(getBounds().width() > 0f && getBounds().height() >0f) {

            setupRect();
            setupShader();
            setupCanvasMatrix();
            int checkpoint = canvas.save();
            canvas.concat(mCanvasMatrix);
            try{
                canvas.drawRect(mRect,mPaint);
            }finally {
                canvas.restoreToCount(checkpoint);
            }
        }
    }

    private void setupShader(){
        if(RADIAL == mType) {
            float cx = mCenterX * mRect.width();
            float cy = mCenterY * mRect.height();
            float r = (mRadiusTarget.equals(TO_MIN) ? mRadius * Math.min(mRect.width(), mRect.height())
                    : mRadiusTarget.equals(TO_MAX) ? mRadius * Math.max(mRect.width(), mRect.height())
                    : mRadiusTarget.equals(TO_WIDTH) ? mRadius * mRect.width()
                    : mRadiusTarget.equals(TO_HEIGHT) ? mRadius * mRect.height()
                    : mRadius);

            mPaint.setShader(new RadialGradient(cx, cy, r, mColors, mPositions, mTileMode));
        }else if(SWEEP == mType) {
            float cx = mCenterX * mRect.width();
            float cy = mCenterY * mRect.height();
            mSweepMatrix.reset();
            mSweepMatrix.postRotate(mSweepRotation, cx, cy);
            mPaint.setShader(new SweepGradient(cx, cy, mColors, mPositions));
            mPaint.getShader().setLocalMatrix(mSweepMatrix);
        }else{
            float x1 = mStartX * mRect.width();
            float y1 = mStartY * mRect.height();
            float x2 = mEndX * mRect.width();
            float y2 = mEndY * mRect.height();
            mPaint.setShader(new LinearGradient(x1,y1,x2,y2,mColors,mPositions,mTileMode));
        }
    }

    private void setupRect(){
        mRect.set(mBaseRect);
        mRect.inset( mInsetX, mInsetY);
    }
    private final Camera mCamera = new Camera();
    private void setupCanvasMatrix(){
        float transX = mTranslationX;
        float transY = mTranslationY;
        if(mIsTranslationPercent){
            transX = (mTranslationX * getBounds().width()) + mTranslationPlusX;
            transY = (mTranslationY * getBounds().height()) + mTranslationPlusY;
        }

        mCanvasMatrix.reset();
        mCamera.save();

        for(int e : mRotationOrder){
            if(e == AXIS_Z){
                 mCamera.rotate(0f,0f, -mRotationZ);
            }
            if(e == AXIS_X){
                 mCamera.rotate(-mRotationX,0f, 0f);
            }
            if(e == AXIS_Y){
                 mCamera.rotate(0f,-mRotationY, 0f);
            }
        }

        mCamera.getMatrix(mCanvasMatrix);
        mCamera.restore();

        mCanvasMatrix.postScale(mScaleX,mScaleY);
        mCanvasMatrix.preTranslate(-mBaseRect.centerX(),-mBaseRect.centerY());
        mCanvasMatrix.postTranslate(mBaseRect.centerX(),mBaseRect.centerY());
        mCanvasMatrix.postTranslate(transX,transY);

    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }
}