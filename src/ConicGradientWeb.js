import React, { useMemo } from 'react'
import Helper from 'react-native-gradientview/src/Helper'



const ConicGradientWeb = (props)=>{

  
    const {startPoint,
        endPoint,
        colors,
        positions,
        conicRotation,
        centerX,
        centerY,
         ...rest} = props


    const rotationBase = 90
    const c = colors || ['white','black']
    const cx = centerX === undefined ? 0.5 :  centerX
    const cy = centerY === undefined ? 0.5 :  centerX
    const rot = conicRotation === undefined ? rotationBase :  conicRotation + rotationBase

    const conicGradient = useMemo(()=>{
        return Helper.getConicColors(c,rot,cx,cy,positions)
    },props.colors,props.conicRotation,props.centerX,props.centerY,props.positions)

    return(
        <div {...rest}  >
                <div style={{width:'100%',height:'100%',background: conicGradient }} />
        </div>
    
    )
}


export default ConicGradientWeb