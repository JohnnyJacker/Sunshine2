package com.example.android.sunshine;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.EditTextPreference;
import android.util.AttributeSet;

/**
 * Created by T510 Owner on 5/17/2016.
 */
public class LocationEditTextPreference extends EditTextPreference {

    static final private int DEFAULT_MINIMUM_LOCATION_LENGTH = 2;
    private int mMinLength;

    public LocationEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        //  This is asking for the location edit text preference style
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.LocationEditTextPreference,
                0, 0);
        try {
            //  This is getting the integer value of the attribute and passing in default value
            mMinLength = a.getInteger(R.styleable.LocationEditTextPreference_minLength,
                    DEFAULT_MINIMUM_LOCATION_LENGTH);
        } finally {
            a.recycle();
        }
    }
}
