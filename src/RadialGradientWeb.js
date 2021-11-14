import React, { useMemo } from 'react'
import Helper from 'react-native-gradientview/src/Helper'
import { StyleSheet } from 'react-native'


const RadialGradientWeb = (props)=>{

  
    const {
        
        svgKey,

        cx,
        cy,
        rx,
        ry,
        tileMode,
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

    const colorsArr = colors === undefined ? ['white','black'] : colors

    const tm = tileMode === undefined ? 'clamp' : tileMode

    const centerX = cx === undefined ? 0.5 : cx
    const centerY = cy === undefined ? 0.5 : cy
    const radiusX = rx === undefined ? 0.5 : rx
    const radiusY = ry === undefined ? 0.5 : ry

    const useKey = svgKey === undefined ? "" : svgKey

    const gradientKey = `JJSRadialGradient${useKey}`
    return(
        <div {...rest} style={styleObject} >
            <svg viewBox="0 0 1 1"  style={{width:'100%',height:'100%'}} >
                <defs>
                    <radialGradient id={gradientKey}
                     spreadMethod={Helper.getSpreadMethod(tm)} 
                    gradientUnits="objectBoundingBox"
                    gradientTransform={`translate(0.5 0.5) scale(1,${radiusY/radiusX}) translate(-0.5 -0.5)`}
                    cx={centerX}  cy={centerY} r={radiusX}  
                    >
                         {colorsArr.map((color,index)=>{
                                return(
                                    <stop key={index} offset={Helper.getPosition(positions,index,colorsArr.length)} stopColor={color}  />
                                )
                            })}
 
                    </radialGradient>
                </defs>

                <rect width="1" height="1" fill={`url('#${gradientKey}')`} />
            </svg>
        </div>
    
    )
}


export default RadialGradientWeb