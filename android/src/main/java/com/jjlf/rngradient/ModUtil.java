package com.jjlf.rngradient;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

class ModUtil {

    static float clamp(float v){
        return v > 1 ? 1 : (v < 0 ? 0 : v);
    }

    static String getString(ReadableMap m, String name, String optional){
        if(m != null){
            String v = m.getString(name);
            if(v != null){
                return v;
            }
        }
        return optional;
    }
    static double getDouble(ReadableMap m, String name, double optional){
        if(m != null){
            return m.getDouble(name);
        }
        return optional;
    }
    static int getInt(ReadableMap m, String name, int optional){
        if(m != null){
            return m.getInt(name);
        }
        return optional;
    }
    static boolean getBoolean(ReadableMap m, String name, boolean optional){
        if(m != null){
            return m.getBoolean(name);
        }
        return optional;
    }
    static ReadableArray getArray(ReadableMap m, String name, ReadableArray optional){
        if(m != null){
            ReadableArray v = m.getArray(name);
            if(v != null){
                return v;
            }
        }
        return optional;
    }

    static int[] toIntArray(ReadableArray colors){
        int[] list = new int [colors.size()];
        for(int i = 0 ;   i < colors.size() ; i++){
            list[i] = colors.getInt(i);
        }
        return list;
    }
    static float[] toFloatArray(ReadableArray colors){
        float[] list = new float [colors.size()];
        for(int i = 0 ;   i < colors.size() ; i++){
            list[i] = (float) colors.getDouble(i);
        }
        return list;
    }
}
