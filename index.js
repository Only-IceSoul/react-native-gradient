// main index.js

import {  requireNativeComponent } from 'react-native';
import ExtractColor from './src/Color'

export const LinearGradient = requireNativeComponent('LinearGradient',null);
export const RadialGradient = requireNativeComponent('RadialGradient',null);
export const ConicGradient = requireNativeComponent('ConicGradient',null);
export const Color = ExtractColor;