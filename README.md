# Gradientview

android: api 21+   
ios : 10.0+   
web: svg 
  
<img src="./src/demo.jpg" width="600">  
  


## Getting started

`$ npm install react-native-gradientview --save`  
`$ react-native link react-native-gradientview`  
  
or

`$ yarn add react-native-gradientview `
    

## IOS

**Add Swift**

(expo sdk >=42 you don't need to do this)

/ios/name_project

add a .swift file 



# Usage
```javascript
import { LinearGradient, RadialGradient,ConicGradient ,Color } from 'react-native-gradientview';

// TODO: What to do with the module?
   <LinearGradient {...props}
     colors={[Color('red'),Color('#ccc'),...]}
    />

    <RadialGradient {...props} />
    <ConicGradient {...props} />


```


## LinearGradient 

| Name | type | default |
| --- | --- | --- |
| startPoint |  Object x y | 0.5 - 0 |
| endPoint |  Object x y | 0.5 - 1 | 
| colors |  Array [Color]  | white,black |
| positions |  Array [number] | undefined  |
| tileMode | (android,web)  String  | 'clamp' |  
## RadialGradient 

| Name | type | default |
| --- | --- | --- |
| colors |  Array [Color]  | white,black |
| positions |  Array [number] |  undefined |
| cx |  number |  0.5 |
| cy |  number |  0.5 |
| rx |  number | 0.5 |
| rx |  number | 0.5 |
| tileMode | (android,web) String  | 'clamp' |

## ConicGradient 

| Name | type | OS | default |
| --- | --- | --- | --- |
| startPoint |  Object | ios | 0.5 - 0 |
| endPoint |  Object | ios | 0.5 - 1 |
| colors |  Array [Color]  | android,web,ios | white,black |
| positions |  Array [number] | android,web,ios | undefined |
| centerX |  number | android,web | 0.5 |
| centerY |  number | android,web | 0.5 |
| conicRotation |  number | android,web | 0 |

