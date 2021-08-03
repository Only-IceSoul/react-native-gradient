import React from "react";
import { Constructor, NativeMethods, ViewProps } from "react-native";



interface BaseGradientProps extends ViewProps {
    startPoint?:{ x:number,y:number }
    endPoint?:{ x:number,y:number }
    positions?:number[]
    colors?: (number | string)[]
}

interface TileModeProp{
    tileMode?: 'mirror' | 'repeat' | 'clamp'
} 

interface RadialProps extends TileModeProp{
    centerX?: number
    centerY?:number
    radius?: {
        value?:number,
        valueWeb?:number,
        multiplier?: 'min' | 'max' | 'height' | 'width' | 'none'
    }
}

interface ConicProps{
    centerX?: number
    centerY?:number
    conicRotation?:number
}

declare class LinearComponent extends React.Component<TileModeProp & BaseGradientProps>  {}
declare class RadialComponent extends React.Component<RadialProps & BaseGradientProps>  {}
declare class ConicComponent extends React.Component<ConicProps & BaseGradientProps> {}
 declare const LinearGradientBase: Constructor<NativeMethods> & typeof LinearComponent;
 declare const RadialGradientBase: Constructor<NativeMethods> & typeof RadialComponent;
 declare const ConicGradientBase: Constructor<NativeMethods> & typeof ConicComponent;
 export class LinearGradient  extends LinearGradientBase {}
 export class RadialGradient  extends RadialGradientBase {}
 export class ConicGradient  extends ConicGradientBase {}
export function Color(color:number | number[] | string): number; 
