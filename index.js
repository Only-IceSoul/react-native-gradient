// main index.js

import {  requireNativeComponent } from 'react-native';
import Color from './src/Color'

const LinearGradient = requireNativeComponent('LinearGradient',null);
const RadialGradient = requireNativeComponent('RadialGradient',null);
const ConicGradient = requireNativeComponent('ConicGradient',null);

export default {
    LinearGradient,
    RadialGradient,
    ConicGradient,
    Color
};
