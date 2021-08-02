//
//  extension.swift
//  react-native-gradient
//
//  Created by Juan J LF on 8/2/21.
//

import Foundation

extension CGFloat {
    
    func toRadians()-> CGFloat{
        return (self * .pi) / 180
    }
    func toDegrees()-> CGFloat{
        return self * 180 / CGFloat.pi
    }
    
    func clamp(min:CGFloat = 0,max:CGFloat = 1) -> CGFloat{
        return self > max ? max : (self < min ? min : self)
    }
    func clampNotNegative()->CGFloat{
        return self < 0 ? 0 : self
    }
}


extension UIColor{
    
    static func parseHex(_ hex: String) -> UIColor? {
          var chars = Array(hex.hasPrefix("#") ? hex.dropFirst() : hex[...])
          let red, green, blue, alpha: CGFloat
          switch chars.count {
          case 3:
              chars = chars.flatMap { [$0, $0] }
              fallthrough
          case 6:
              chars = ["F","F"] + chars
              fallthrough
          case 8:
              alpha = CGFloat(strtoul(String(chars[0...1]), nil, 16)) / 255
              red   = CGFloat(strtoul(String(chars[2...3]), nil, 16)) / 255
              green = CGFloat(strtoul(String(chars[4...5]), nil, 16)) / 255
              blue  = CGFloat(strtoul(String(chars[6...7]), nil, 16)) / 255
          default:
              return nil
          }
         return self.init(red: red, green: green, blue:  blue, alpha: alpha)
      }
    
    static func parseInt(argb: Int)-> UIColor{
        let a = CGFloat((argb >> 24) & 0xFF)
        let r = CGFloat((argb >> 16) & 0xFF)
        let g = CGFloat((argb >> 8) & 0xFF)
        let b = CGFloat(argb & 0xFF)
        return UIColor(red: r / 255, green: g / 255, blue: b / 255, alpha: a / 255)
    }
}

