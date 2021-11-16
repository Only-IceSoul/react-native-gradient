//
//  RadialGradientView.swift
//  react-native-gradient
//
//  Created by Juan J LF on 8/2/21.
//

import UIKit


@objc(RadialGradientView)
class RadialGradientView: UIView {
    

    let mDrawable = GradientLayer()
    
    private var mCx :CGFloat = 0.5
    private var mCy :CGFloat = 0.5
    private var mRx :CGFloat = 0.5
    private var mRy :CGFloat = 0.5
    
    
    init() {
        super.init(frame: .zero)
        layer.addSublayer(mDrawable)
        setupView()

    }
    override init(frame: CGRect) {
        super.init(frame: frame)
        layer.addSublayer(mDrawable)
        setupView()
     
    }
    func setupView(){
        mDrawable.setType(.radial)
        mDrawable.setStartPoint(CGPoint(x: 0.5, y: 0.5))
        mDrawable.setEndPoint(CGPoint(x: 1, y: 1))
        mDrawable.setColor([UIColor.white.cgColor,UIColor.black.cgColor])
    }
    required init?(coder: NSCoder) {fatalError("init(coder:) has not been implemented")}
    
    override var bounds: CGRect{
        didSet{
            mDrawable.onBoundsChange(bounds)
        }
    }
    
    @objc func setColors(_ v:[Int]?){
        let colors = v != nil ? toCgColorArr(v!) : [UIColor.white.cgColor,UIColor.black.cgColor]
        mDrawable.setColor(colors)
    }
    @objc func setPositions(_ v:[NSNumber]?){
        mDrawable.setPositions(v)
    }
    @objc func setCx(_ v:NSNumber?){
        let v = CGFloat.init(truncating: v ?? 0.5)
     
        if v != mCx{
            mCx = v
           
            mDrawable.setStartPoint(.init(x: mCx, y: mCy))
            mDrawable.setEndPoint(.init(x: mCx + mRx, y: mCy + mRy))
        }
       
    }
    @objc func setCy(_ v:NSNumber?){
        let v = CGFloat.init(truncating: v ?? 0.5)
        if v != mCy{
            mCy = v
            mDrawable.setStartPoint(.init(x: mCx, y: mCy))
            mDrawable.setEndPoint(.init(x: mCx + mRx, y: mCy + mRy))
        }
    }
    @objc func setRx(_ v:NSNumber?){
        let v = CGFloat.init(truncating: v ?? 0.5)
        if v != mRx{
            mRx = v
            mDrawable.setEndPoint(.init(x: mCx + mRx, y: mCy + mRy))
        }

    }
    @objc func setRy(_ v:NSNumber?){
        let v = CGFloat.init(truncating: v ?? 0.5)
        if v != mRy{
            mRy = v
            mDrawable.setEndPoint(.init(x:  mCx + mRx, y: mCy + mRy))
        }

    }
    @objc func setTileMode(_ v:String?){
        
    }

    func toCgColorArr(_ c:[Int]) -> [CGColor]{
        var colors = [CGColor]()
        for i in c {
            colors.append(UIColor.parseInt(argb: i).cgColor)
        }
        return colors
    }
   

}
