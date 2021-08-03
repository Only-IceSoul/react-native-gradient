
function getPositionConic(arr,index,length){
    if(arr){
        return arr[index]
    }
 
    if(length > 0){
        let l =  length - 1
        let sum = 1 / l
        return index == l ? 1 : index*sum
    }
    return 0
}

const Helper = {
    getPosition:(arr,index,length)=>{
        if(arr){
            return `${arr[index] * 100}%`
        }
     
        if(length > 0){
            let l =  length - 1
            let sum = 1 / l
            return index == l ? "100%" : `${(index*sum) * 100}%`
        }
        return "0%"
    },
    getSpreadMethod:(tileMode)=>{
        switch(tileMode){
            case "mirror":
                return "reflect"
            case "repeat":
                return "repeat"
            default:
                return "pad" 
        }
    },
    getConicColors:(c,r,cx,cy,pos)=>{
        var str = "conic-gradient("
        str += `from ${r}deg at ${cx * 100}% ${cy * 100}% ,`
        c.map((v,index)=>{
            str += `${v} `
            str += `${getPositionConic(pos,index,c.length) * 360}deg,`
        })
        str = str.slice(0, -1)
        str += ")"
        return str

    }
}

export default Helper