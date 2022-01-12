/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * Generated with the TypeScript template
 * https://github.com/react-native-community/react-native-template-typescript
 *
 * @format
 */

import React, {useEffect, useState, useCallback} from 'react';
import {View} from 'react-native';
import VideoPlayer from './src/components/VideoPlayer';

const App = () => {
    const [paused, setPaused] = useState(false);

    useEffect(
        useCallback(() => {
            setTimeout(() => {
                setPaused(true);
            }, 30000);
        }, []),
        [],
    );

    const onVideoLoaded = useCallback(() => {
        console.log('Loaded');
    }, []);

    const onVideoCompleted = useCallback(() => {
        console.log('Completed');
    }, []);

    return (
        <View
            // eslint-disable-next-line react-native/no-inline-styles
            style={{
                flex: 1,
                justifyContent: 'center',
                alignItems: 'center',
            }}>
            <VideoPlayer
                source={
                    'http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4'
                }
                autoPlay={true}
                paused={paused}
                onLoaded={onVideoLoaded}
                onCompleted={onVideoCompleted}
            />
        </View>
    );
};

export default App;
