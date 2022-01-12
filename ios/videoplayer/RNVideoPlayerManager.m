//
//  RNVideoPlayerManager.m
//  nativeComponents
//
//  Created by Hur Ali on 1/12/22.
//

#import "RNVideoPlayerManager.h"
#import "RNVideoPlayer.h"

@implementation RNVideoPlayerManager

RCT_EXPORT_MODULE(RNVideoPlayer)

+ (BOOL)requiresMainQueueSetup
{
    return YES;
}

- (UIView *)view
{
  return [RNVideoPlayer new];
}

RCT_EXPORT_VIEW_PROPERTY(source, NSString);
RCT_EXPORT_VIEW_PROPERTY(paused, BOOL);
RCT_EXPORT_VIEW_PROPERTY(autoPlay, BOOL);
RCT_EXPORT_VIEW_PROPERTY(onLoaded, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onCompleted, RCTBubblingEventBlock)
@end
