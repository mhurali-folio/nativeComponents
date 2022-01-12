/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * Generated with the TypeScript template
 * https://github.com/react-native-community/react-native-template-typescript
 *
 * @format
 */

import React from 'react';
import {StyleSheet, View, requireNativeComponent} from 'react-native';
import {IVideoPlayer} from './index.props';

const VPlayer = requireNativeComponent('RNVideoPlayer');

const VideoPlayer = (props: IVideoPlayer) => {
    return (
        <View style={[styles.container, props.styles]}>
            <VPlayer
                style={styles.player}
                source={props.source}
                autoPlay={props.autoPlay}
                paused={props.paused}
                onLoaded={props.onLoaded}
                onCompleted={props.onCompleted}
            />
        </View>
    );
};

const styles = StyleSheet.create({
    player: {
        flex: 1,
    },
    container: {
        width: 300,
        height: 300,
    },
});

export default VideoPlayer;
