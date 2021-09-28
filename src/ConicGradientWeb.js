import React, { useMemo } from 'react'
import Helper from 'react-native-gradientview/src/Helper'
import { StyleSheet } from 'react-native'


const ConicGradientWeb = (props)=>{

  
    const {startPoint,
        endPoint,
        colors,
        positions,
        conicRotation,
        centerX,
        centerY,
        style,
         ...rest} = props


    const styleObject = useMemo(()=>{
    if (typeof style === 'number') return StyleSheet.flatten(style) 
    if(Array.isArray(style)){
        var styleJs = {}
        style.forEach((v)=>{
            if(typeof v === 'number'){
            let ss = StyleSheet.flatten(style) 
            Object.assign(styleJs,ss)
            }else{
            Object.assign(styleJs,v)
            }
        })

        return styleJs
    }
    return style
    },[style])

    const rotationBase = 90
    const c = colors || ['white','black']
    const cx = centerX === undefined ? 0.5 :  centerX
    const cy = centerY === undefined ? 0.5 :  centerX
    const rot = conicRotation === undefined ? rotationBase :  conicRotation + rotationBase

    const conicGradient = useMemo(()=>{
        return Helper.getConicColors(c,rot,cx,cy,positions)
    },props.colors,props.conicRotation,props.centerX,props.centerY,props.positions)

    return(
        <div {...rest} style={styleObject} >
                <div style={{width:'100%',height:'100%',background: conicGradient }} />
        </div>
    
    )
}


export default ConicGradientWeb