import React from "react";
import { Constructor, NativeMethods, ViewProps } from "react-native";

type Color = number | number[] | string;

interface BaseGradientProps extends ViewProps {
    startPoint?:{ x:number,y:number }
    endPoint?:{ x:number,y:number }
    positions?:number[]
    colors?: Color[]
}

interface TileModeProp{
    tileMode?: 'mirror' | 'repeat' | 'clamp'
} 

interface RadialProps extends TileModeProp{
    centerX?: number
    centerY?:number
    radius?: {
        value:number,
        multiplier?: 'min' | 'max' | 'height' | 'width' | 'none'
    }
}

interface ConicProps{
    centerX?: number
    centerY?:number
    conicRotation?:number
}

declare class Linear extends React.Component<TileModeProp & BaseGradientProps> {}
declare class Radial extends React.Component<RadialProps & BaseGradientProps> {}
declare class Conic extends React.Component<ConicProps & BaseGradientProps> {}
export declare const LinearGradient: Constructor<NativeMethods> & typeof Linear;
export declare const RadialGradient: Constructor<NativeMethods> & typeof Radial;
export declare const ConicGradient: Constructor<NativeMethods> & typeof Conic;
export function Color(value: number | number[] | string):number | null;

 