package com.cmargb.momentsofinertia.util;

import android.util.Log;

/**
 * Created by connor on 9/15/15.
 */
public class ScalingUtils {
    private static int screenWidth;
    private static int screenheight;
    private static float scaleRatio;

    public static void setScreenWidth(int screenWidth, int screenHeight){
        ScalingUtils.screenWidth = screenWidth;
        ScalingUtils.screenheight = screenHeight;
        scaleRatio = (float)screenWidth/1794f;
    }

    public static int cPX(int position){
        return (int)((float)position*scaleRatio);
    }

    public static int iCPX(int position){ return (int)((float)position/scaleRatio);}

    public static float fICPX(float position){ return position/scaleRatio;}

    public static int getScreenheight(){return screenheight;}

}
