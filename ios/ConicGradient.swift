//
//  ConicGradient.swift
//  react-native-gradient
//
//  Created by Juan J LF on 8/2/21.
//

import Foundation
import UIKit

@objc(ConicGradient)
class ConicGradient: RCTViewManager {

    override func view() -> UIView! {
       return ConicGradientView()
     }
    override class func requiresMainQueueSetup() -> Bool {
        return false
    }
}
