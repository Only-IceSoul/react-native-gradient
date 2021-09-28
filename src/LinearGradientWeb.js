import React, { useMemo } from 'react'
import Helper from 'react-native-gradientview/src/Helper'
import {StyleSheet } from 'react-native'


const LinearGradientWeb  = (props)=>{

  
    const {startPoint,endPoint,tileMode,colors,positions,style, ...rest} = props

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
    const s = startPoint === undefined ? { x:0.5, y:0 } :  startPoint
    const e = endPoint === undefined ?  { x:0.5, y:1 } :  endPoint
    
    return(
        <div {...rest} style={styleObject} >
            <svg viewBox="0 0 1 1" style={{width:'100%',height:'100%'}}>
                <defs>
                    <linearGradient id="linearjjlf" spreadMethod={Helper.getSpreadMethod(tm)} 
                    x1={`${s.x * 100}%`} y1={`${s.y * 100}%`} x2={`${e.x * 100}%`} y2={`${e.y * 100}%`}
                    >
                        {c.map((color,index)=>{
                            return(
                                <stop key={index} offset={Helper.getPosition(positions,index,c.length)} stopColor={`${color}`} />
                            )
                        })}
 
                    </linearGradient>
                </defs>

                <rect width="1" height="1" fill="url('#linearjjlf')" />
            </svg>
        </div>
    
    )
}


export default LinearGradientWeb