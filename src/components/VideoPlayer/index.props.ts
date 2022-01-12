import {ViewStyle} from 'react-native';

export interface IVideoPlayer {
    styles?: ViewStyle;
    source: String;
    autoPlay?: Boolean;
    paused?: Boolean;
    onLoaded?: () => void;
    onCompleted?: () => void;
}
