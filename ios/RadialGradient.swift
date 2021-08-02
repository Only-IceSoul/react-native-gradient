//
//  RadialGradient.swift
//  react-native-gradient
//
//  Created by Juan J LF on 8/2/21.
//

import UIKit

@objc(RadialGradient)
class RadialGradient: RCTViewManager {

    override func view() -> UIView! {
       return RadialGradientView()
     }
    override class func requiresMainQueueSetup() -> Bool {
        return false
    }
}
