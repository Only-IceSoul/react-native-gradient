//
//  ConicGradient.m
//  react-native-gradient
//
//  Created by Juan J LF on 8/2/21.
//

#import <Foundation/Foundation.h>
#import <Foundation/Foundation.h>
#import "React/RCTViewManager.h"
#import <React/RCTBridgeModule.h>
#import <UIKit/UIKit.h>


@interface
RCT_EXTERN_MODULE(ConicGradient,RCTViewManager)

RCT_EXPORT_VIEW_PROPERTY(startPoint, NSDictionary)
RCT_EXPORT_VIEW_PROPERTY(endPoint, NSDictionary)
RCT_EXPORT_VIEW_PROPERTY(colors, NSArray)
RCT_EXPORT_VIEW_PROPERTY(positions, NSArray)
RCT_EXPORT_VIEW_PROPERTY(conicRotation, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(centerX, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(centerY, NSNumber)


@end
