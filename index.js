// main index.js

import { Platform ,requireNativeComponent} from 'react-native';
import ExtractColor from './src/Color'
import LinearGradientWeb from './src/LinearGradientWeb'
import RadialGradientWeb from './src/RadialGradientWeb'
import ConicGradientWeb from './src/ConicGradientWeb'

export const LinearGradient = (Platform.OS === 'android' || Platform.OS === 'ios') ? requireNativeComponent('LinearGradient',null) : LinearGradientWeb
export const RadialGradient =  (Platform.OS === 'android' || Platform.OS === 'ios') ? requireNativeComponent('RadialGradient',null) : RadialGradientWeb
export const ConicGradient =  (Platform.OS === 'android' || Platform.OS === 'ios')  ? requireNativeComponent('ConicGradient',null) : ConicGradientWeb
export const Color = ExtractColor;