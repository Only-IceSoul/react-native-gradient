//
//  GradientLayer.swift
//  react-native-gradient
//
//  Created by Juan J LF on 8/2/21.
//


import UIKit


class GradientLayer : CAGradientLayer{
    
     enum Axis {
        case x, y , z
    }

     enum GradientType : String{
        case axial,
             radial,
             conic
        internal func toTarget() -> CAGradientLayerType
        {
            return CAGradientLayerType(rawValue: rawValue)
        }
    }
  
    //MARK: layer propertys
    private var mRotationX :CGFloat = 0
    private var mRotationY :CGFloat = 0
    private var mRotationZ :CGFloat = 0
    private var mRotationOrder :[Axis] = [.z,.x,.y]
    private var mTranslationX : CGFloat = 0
    private var mTranslationY : CGFloat = 0
    private var mTranslationPlusX : CGFloat = 0
    private var mTranslationPlusY : CGFloat = 0
    private var mIsTranslationPercent : Bool = false
    private var mScaleX  : CGFloat = 1
    private var mScaleY  : CGFloat = 1
    private var mInsetX  : CGFloat = 0
    private var mInsetY  : CGFloat = 0
    private var mBaseRect = CGRect()
    private var mRect = CGRect()

    
    
    public override init() {
        super.init()
        type = .axial
    }
    
    public init(type: GradientType){
        super.init()
        super.type = type.toTarget()
    }
    @discardableResult
    public func setType(_ t: GradientType)-> GradientLayer {
        disableAnimation()
        super.type = t.toTarget()
        commit()
        return self
    }
    @discardableResult
    public func setStartPoint(_ point: CGPoint)-> GradientLayer {
        disableAnimation()
        super.startPoint = point
        commit()
        return self
    }
    @discardableResult
    public func setEndPoint(_ point: CGPoint)-> GradientLayer {
        disableAnimation()
        super.endPoint = point
        commit()
        return self
    }
    @discardableResult
    public func setColor(_ colors: [CGColor])-> GradientLayer {
        disableAnimation()
        super.colors = colors
        commit()
        return self
    }
    @discardableResult
    public func setPositions(_ positions:[NSNumber]?)-> GradientLayer {
        disableAnimation()
        super.locations = positions
        commit()
        return self
    }
    
    
    //MARK: layer set transform
     @discardableResult
    public func setScale(sx:CGFloat,sy:CGFloat)-> GradientLayer{
        mScaleX = sx
        mScaleY = sy
        return self
    }

     @discardableResult
    public func setRotationZ(degrees: CGFloat) -> GradientLayer{
        mRotationZ = degrees
        return self
    }
    @discardableResult
   public func setRotationX(degrees: CGFloat) -> GradientLayer{
       mRotationX = degrees
       return self
   }
    @discardableResult
   public func setRotationY(degrees: CGFloat) -> GradientLayer{
       mRotationY = degrees
 
       return self
   }
    @discardableResult
    public func setRotationOrder(f: Axis,s:Axis,t:Axis) -> GradientLayer{
        if (f == s || f == t) || ( s == t ) { return self}
       mRotationOrder = [f,s,t]
       return self
   }
     @discardableResult
    public func setTranslation(dx:CGFloat,dy:CGFloat)-> GradientLayer {
        mIsTranslationPercent = false
        mTranslationX = dx
        mTranslationY = dy
        return self
    }
    @discardableResult
    public func setTranslation(percentX:CGFloat,percentY:CGFloat, plusX:CGFloat = 0,plusY:CGFloat = 0)-> GradientLayer {
        mTranslationX = percentX
        mTranslationY = percentY
        mTranslationPlusX = plusX
        mTranslationPlusY = plusY
        mIsTranslationPercent = true
        return self
   }
    
    private var mFirstBounds = CGRect()
    private var mBoundsX:CGFloat = 0
    private var mBoundsY:CGFloat = 0
    private var mBoundsWidth:CGFloat = 0
    private var mBoundsHeight:CGFloat = 0
    private var mIsBoundsDynamically = false
    private var mIsBoundsPercentPos = false
    private var mIsBoundsPercentSize = false
    
    /**
     
      Put the position and size of the layer where it will draw  relative in percentage-absolute to the bounds it received, naturally it always receives the bounds of the parent.
      
      Percentage values  0 = 0% - 1 = 100%
      
      - Parameters:
          - x :  x origin
          - y :  y origin
          - width : a width value
          - height : a height value
          - percentPos : if origin is a percentage value, default false
          - percentSize: if size is a percentage value, default false
      
      */
    @discardableResult
    public func setBoundsDynamically(x:CGFloat,y:CGFloat,width:CGFloat,height:CGFloat,percentPos:Bool = false,percentSize:Bool = false)-> GradientLayer{
        mIsBoundsDynamically = true
        mIsBoundsPercentPos = percentPos
        mIsBoundsPercentSize = percentSize
        mBoundsX = x
        mBoundsY = y
        mBoundsWidth = width >= 0 ? width : 0
        mBoundsHeight = height >= 0 ? height : 0
        return self
    }
    
    public func onBoundsChange(_ frame: CGRect,invalidate:Bool = true){
        mFirstBounds.size = frame.size
        mFirstBounds.origin = frame.origin
        var left = mBoundsX
        var top = mBoundsY
        var width = mBoundsWidth
        var height = mBoundsHeight
        if(mIsBoundsPercentPos){
            left = mBoundsX * frame.width
            top = mBoundsY * frame.height
            
        }
        if(mIsBoundsPercentSize){
            width = mBoundsWidth * frame.width
            height = mBoundsHeight * frame.height
        }
        if(mIsBoundsDynamically){
            
            mBaseRect.origin.x = left
            mBaseRect.origin.y = top
            mBaseRect.size.width = width
            mBaseRect.size.height = height
         
        }else{
            mBaseRect.size = frame.size
            mBaseRect.origin = frame.origin
        }
        
        disableAnimation()
        super.frame = mBaseRect
        commit()
     
    }
    
    private func draw(){
        if(frame.width > 0 && frame.height > 0){
            makeTransform()
        }
    }
    
    public func invalidateSelf(){
        draw()
    }
    
    private func makeTransform(_ animated:Bool = false){
      
        if(animated && duration > 0){
            applyTransform()
        }else {
            disableAnimation()
            applyTransform()
            commit()
        }
    }
    private func applyTransform(){
        var transX : CGFloat = mTranslationX
        var transY : CGFloat = mTranslationY
        if(mIsTranslationPercent){
            transX = (mTranslationX * mFirstBounds.width) + mTranslationPlusX
            transY = (mTranslationY * mFirstBounds.height) + mTranslationPlusY
        }

        var result  = CATransform3DTranslate(CATransform3DIdentity, transX, transY, 0)
        
        var per = CATransform3DIdentity
        per.m34 = 1 / -400;
        for e in mRotationOrder {
            if e == .z{
                per =  CATransform3DRotate(per, mRotationZ.toRadians(), 0, 0, 1)
            }
            if e == .y{
                per = CATransform3DRotate(per, -mRotationY.toRadians(), 0, 1, 0)
            }
            if e == .x{
                per = CATransform3DRotate(per, -mRotationX.toRadians(), 1, 0, 0)
            }
        }

        result = CATransform3DConcat(per, result)
        result = CATransform3DScale(result, mScaleX, mScaleX, 1)
        
        self.transform = result
    }
    private func disableAnimation(){
        CATransaction.begin()
        CATransaction.setDisableActions(true)
    }
    
    private func commit(){
        CATransaction.commit()
    }
    
    
    
   
    public override init(layer: Any) {
        super.init(layer: layer)

    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
