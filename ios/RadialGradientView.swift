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
    
    @objc func setStartPoint(_ v:[String:Any]?){
        let x = v?["x"] as? CGFloat ?? 0.5
        let y = v?["y"] as? CGFloat ?? 0.5
        mDrawable.setStartPoint(CGPoint(x: x, y: y))
        
    }
    @objc func setEndPoint(_ v:[String:Any]?){
        let x = v?["x"] as? CGFloat ?? 1
        let y = v?["y"] as? CGFloat ?? 1
        mDrawable.setEndPoint(CGPoint(x: x, y: y))
    }
    @objc func setColors(_ v:[Int]?){
        let colors = v != nil ? toCgColorArr(v!) : [UIColor.white.cgColor,UIColor.black.cgColor]
        mDrawable.setColor(colors)
    }
    @objc func setPositions(_ v:[NSNumber]?){
        mDrawable.setPositions(v)
    }
    @objc func setCenterX(_ v:NSNumber?){}
    @objc func setCenterY(_ v:NSNumber?){}
    @objc func setTileMode(_ v:String?){}
    @objc func setRadius(_ v:[String:Any]?){}
    func toCgColorArr(_ c:[Int]) -> [CGColor]{
        var colors = [CGColor]()
        for i in c {
            colors.append(UIColor.parseInt(argb: i).cgColor)
        }
        return colors
    }
   

}
