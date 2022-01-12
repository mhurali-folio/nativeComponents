package com.nativecomponents.videoplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.MediaController;
import android.widget.VideoView;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;

public class RNVideoPlayer extends VideoView {
    VideoView videoView;
    private Boolean autoPlay = true;
    private Boolean paused = true;
    private EventDispatcher mEventDispatcher;
    private ReactContext reactContext;

    public RNVideoPlayer(Context context) {
        super(context);
        reactContext = (ReactContext)getContext();
        mEventDispatcher = UIManagerHelper.getEventDispatcherForReactTag(reactContext, getId());
        videoView = this;
        MediaController mediaController= new MediaController(context);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        setupListeners();
    }

    public void setAutoPlay(Boolean autoPlay) {
        this.autoPlay = autoPlay;
    }

    public void setPaused(Boolean paused) {
        this.paused = paused;

        if(this.videoView.canPause() && paused) {
            this.videoView.pause();
        } else if(!paused) {
            this.videoView.resume();
        }
    }

    private void setupListeners() {
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                onVideoCompleted();
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                onVideoLoaded();

                if(autoPlay) {
                    videoView.start();
                }
            }
        });
    }

    private void onVideoCompleted() {
        /**
         *    They old implementation is now deprecated, so we had to look into the implementation of TextInput
         *    in react-native github repo, and from there we identified how they are using the
         *    new Event APIs.
         *
         *      WritableMap event = Arguments.createMap();
         *       event.putString("message", "MyMessage");
         *       ReactContext reactContext = (ReactContext)getContext();
         *       reactContext
         *           .getJSModule(RCTEventEmitter.class)
         *           .receiveEvent(getId(), "topChange", event);
         */

        mEventDispatcher.dispatchEvent(new CompletededEvent(UIManagerHelper.getSurfaceId(reactContext), getId()));
    }

    private void onVideoLoaded() {
        /**
         *    They old implementation is now deprecated, so we had to look into the implementation of TextInput
         *    in react-native github repo, and from there we identified how they are using the
         *    new Event APIs.
         *
         *      WritableMap event = Arguments.createMap();
         *       event.putString("message", "MyMessage");
         *       ReactContext reactContext = (ReactContext)getContext();
         *       reactContext
         *           .getJSModule(RCTEventEmitter.class)
         *           .receiveEvent(getId(), "topChange", event);
         */

        mEventDispatcher.dispatchEvent(new LoadedEvent(UIManagerHelper.getSurfaceId(reactContext), getId()));
    }
}
