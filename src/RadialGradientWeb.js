import React, { useMemo } from 'react'
import Helper from 'react-native-gradientview/src/Helper'
import { StyleSheet } from 'react-native'


const RadialGradientWeb = (props)=>{

  
    const {startPoint,
        endPoint,
        centerX,
        centerY,
        tileMode,
        radius,
        colors,
        positions,
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

    const c = colors === undefined ? ['white','black'] : colors
    const tm = tileMode === undefined ? 'clamp' : tileMode
    const cx = centerX === undefined ? 0.5 :  centerX
    const cy = centerY === undefined ? 0.5 :  centerX
    const r = radius === undefined ? 0.5 : (radius.valueWeb === undefined ? 0.5 : radius.valueWeb)

    return(
        <div {...rest} style={styleObject} >
            <svg viewBox="0 0 1 1"  style={{width:'100%',height:'100%'}} >
                <defs>
                    <radialGradient id="radialjjlf" spreadMethod={Helper.getSpreadMethod(tm)} 
                    cx={`${cx * 100}%`}  cy={`${cy * 100}%`} r={`${r * 100}%`}>
                        {c.map((color,index)=>{
                            return(
                                <stop key={index} offset={Helper.getPosition(positions,index,c.length)} stopColor={`${color}`} />
                            )
                        })}
 
                    </radialGradient>
                </defs>

                <rect width="1" height="1" fill="url('#radialjjlf')" />
            </svg>
        </div>
    
    )
}


export default RadialGradientWeb