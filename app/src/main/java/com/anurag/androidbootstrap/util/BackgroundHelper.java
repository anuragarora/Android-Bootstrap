package com.anurag.androidbootstrap.util;

import android.util.Log;
import com.anurag.androidbootstrap.R;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by anurag on 19/04/17.
 */
public class BackgroundHelper {
    private static final String TAG = BackgroundHelper.class.getSimpleName();

    public static int getBackground(){
        Calendar c = Calendar.getInstance(TimeZone.getDefault());
        int Hr24=c.get(Calendar.HOUR_OF_DAY);
        Log.i(TAG, "Hour of day: "+Hr24);
        if(Hr24>=19 || Hr24<5){
            return R.drawable.night;
        }else if(Hr24>=5 && Hr24<12) {
            return R.drawable.sunrise;
        }else if(Hr24>=12&&Hr24<16){
            return R.drawable.afternoon;
        }else{
            return R.drawable.sunset;
        }
    }
}
