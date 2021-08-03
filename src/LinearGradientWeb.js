import React from 'react'
import Helper from 'react-native-gradientview/src/Helper'


const LinearGradientWeb  = (props)=>{

  
    const {startPoint,endPoint,tileMode,colors,positions, ...rest} = props

    const c = colors || ['white','black']
    const tm = tileMode || 'clamp'
    const s = startPoint === undefined ? { x:0.5, y:0 } :  startPoint
    const e = endPoint === undefined ?  { x:0.5, y:1 } :  endPoint

    return(
        <div {...rest} >
            <svg viewBox="0 0 1 1" >
                <defs>
                    <linearGradient id="linearjjlf" spreadMethod={Helper.getSpreadMethod(tm)} 
                    x1={`${s.x * 100}%`} y1={`${s.y * 100}%`} x2={`${e.x * 100}%`} y2={`${e.y * 100}%`}
                    >
                        {c.map((c,index)=>{
                            return(
                                <stop key={index} offset={Helper.getPosition(positions,index,colors.length)} stopColor={`${c}`} />
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