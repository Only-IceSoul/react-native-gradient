//
//  LinearGradient.swift
//  react-native-gradient
//
//  Created by Juan J LF on 8/2/21.
//

import UIKit

@objc(LinearGradient)
class LinearGradient: RCTViewManager {

    override func view() -> UIView! {
       return LinearGradientView()
     }
   
    override class func requiresMainQueueSetup() -> Bool {
        return false
    }
}
