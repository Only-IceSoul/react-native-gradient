//
//  RadialGradient.m
//  react-native-gradient
//
//  Created by Juan J LF on 8/2/21.
//

#import <Foundation/Foundation.h>
#import "React/RCTViewManager.h"
#import <React/RCTBridgeModule.h>
#import <UIKit/UIKit.h>


@interface
RCT_EXTERN_MODULE(RadialGradient,RCTViewManager)

RCT_EXPORT_VIEW_PROPERTY(cx, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(cy, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(rx, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(ry, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(colors, NSArray)
RCT_EXPORT_VIEW_PROPERTY(positions, NSArray)
RCT_EXPORT_VIEW_PROPERTY(tileMode, NSString)


@end
