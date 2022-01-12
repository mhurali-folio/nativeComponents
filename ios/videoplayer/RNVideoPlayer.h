//
//  RNVideoPlayer.h
//  nativeComponents
//
//  Created by Hur Ali on 1/12/22.
//

#import <AVFoundation/AVFoundation.h>
#import <UIKit/UIKit.h>
#import <React/RCTComponent.h>

@interface RNVideoPlayer : UIView
@property (nonatomic, assign) NSString *source;
@property (nonatomic, assign) BOOL *paused;
@property (nonatomic, assign) BOOL *autoPlay;
@property (nonatomic, copy) RCTBubblingEventBlock onLoaded;
@property (nonatomic, copy) RCTBubblingEventBlock onCompleted;
- (void)setupVideoPlayer:(NSString *)source;
@end
