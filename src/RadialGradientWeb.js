import React from 'react'
import Helper from 'react-native-gradientview/src/Helper'



const RadialGradientWeb = (props)=>{

  
    const {startPoint,
        endPoint,
        centerX,
        centerY,
        tileMode,
        radius,
        colors,
        positions,
         ...rest} = props

    const c =  colors || ['white','black']
    const tm = tileMode || 'clamp'
    const cx = centerX === undefined ? 0.5 :  centerX
    const cy = centerY === undefined ? 0.5 :  centerX
    const r = radius === undefined ? 0.5 : (radius.valueWeb === undefined ? 0.5 : radius.valueWeb)

    return(
        <div {...rest} >
            <svg viewBox="0 0 1 1" >
                <defs>
                    <radialGradient id="radialjjlf" spreadMethod={Helper.getSpreadMethod(tm)} 
                    cx={`${cx * 100}%`}  cy={`${cy * 100}%`} r={`${r * 100}%`}>
                        {c.map((c,index)=>{
                            return(
                                <stop key={index} offset={Helper.getPosition(positions,index,colors.length)} stopColor={`${c}`} />
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