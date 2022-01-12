//
//  RNVideoPlayer.m
//  nativeComponents
//
//  Created by Hur Ali on 1/12/22.
//

#import "RNVideoPlayer.h"

@implementation RNVideoPlayer
{
  BOOL hasCalledSetup;
  AVPlayer *avPlayer;
  BOOL hasAutoPlay;
}

- (void)layoutSubviews
{
  if(hasCalledSetup) {
    [self addVideoPlayerSubview];
  }
}

-(void)addVideoPlayerSubview{
  AVPlayerLayer *videoLayer = [AVPlayerLayer playerLayerWithPlayer:avPlayer];
  videoLayer.frame = self.bounds;
  videoLayer.videoGravity = AVLayerVideoGravityResizeAspectFill;
  
  [self.layer addSublayer:videoLayer];
  
  [avPlayer.currentItem addObserver:self forKeyPath:@"status" options:0 context:nil];
  [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(itemDidFinishPlaying:) name:AVPlayerItemDidPlayToEndTimeNotification object:[avPlayer currentItem]];

  if(hasAutoPlay) {
    [avPlayer play];
  }
}

- (void)setupVideoPlayer:(NSString *)source {
  NSURL *url = [NSURL URLWithString:source];
  avPlayer = [AVPlayer playerWithURL:url];
  avPlayer.actionAtItemEnd = AVPlayerActionAtItemEndNone;
  hasCalledSetup = YES;
}

- (void)setSource:(NSString *)source
{
  [self setupVideoPlayer:source];
}

- (void)setPaused:(BOOL *)paused
{
  if(paused) {
    [avPlayer pause];
  } else {
    [avPlayer play];
  }
}

- (void)setAutoPlay:(BOOL *)autoPlay
{
  hasAutoPlay = autoPlay;
}


- (void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object
                    change:(NSDictionary *)change context:(void *)context
{
    if (object == avPlayer.currentItem && [keyPath isEqualToString:@"status"])
    {
        if (avPlayer.currentItem.status == AVPlayerStatusReadyToPlay)
        {
          _onLoaded(@{
            @"loaded": @(YES)
          });
        }
    }
}

- (void)itemDidFinishPlaying:(NSNotification *)notification {
  _onCompleted(@{
    @"completed": @(YES)
  });
}

@end
