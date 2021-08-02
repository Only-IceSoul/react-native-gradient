// LinearGradient.m

#import <Foundation/Foundation.h>
#import "React/RCTViewManager.h"
#import <React/RCTBridgeModule.h>
#import <UIKit/UIKit.h>


@interface
RCT_EXTERN_MODULE(LinearGradient,RCTViewManager)

RCT_EXPORT_VIEW_PROPERTY(startPoint, NSDictionary)
RCT_EXPORT_VIEW_PROPERTY(endPoint, NSDictionary)
RCT_EXPORT_VIEW_PROPERTY(colors, NSArray)
RCT_EXPORT_VIEW_PROPERTY(positions, NSArray)
RCT_EXPORT_VIEW_PROPERTY(tileMode, NSString)

@end
