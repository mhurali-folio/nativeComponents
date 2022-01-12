package com.nativecomponents.videoplayer;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

public class CompletededEvent extends Event<CompletededEvent> {
    @Override
    public String getEventName() {
        return "onCompleted";
    }

    public CompletededEvent(int surfaceId, int viewId) {
        super(surfaceId, viewId);
    }

    @Override
    public boolean canCoalesce() {
        // We don't want to miss any event, as event data is incremental.
        return false;
    }

    @Nullable
    @Override
    protected WritableMap getEventData() {
        WritableMap eventData = Arguments.createMap();
        eventData.putBoolean("completed", true);
        return eventData;
    }
}
