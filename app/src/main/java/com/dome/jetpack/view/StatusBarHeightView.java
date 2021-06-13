package com.dome.jetpack.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.dome.jetpack.R;


/**
 * =============================================================================
 * Copyright (c) 2019 Administrator All rights reserved.
 * Created time 2019/2/21 0021 下午 5:46.
 * Version   1.0;
 * Describe :  状态栏高度View,用于沉浸占位
 * History:
 * ==============================================================================
 * @author by lwr
 */
public class StatusBarHeightView extends RelativeLayout {

    private int statusBarHeight;
    private int type;

    public StatusBarHeightView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public StatusBarHeightView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.StatusBarHeightView);
            type = typedArray.getInt(R.styleable.StatusBarHeightView_use_type, 0);
            typedArray.recycle();
        }
        if (type == 1) {
            setPadding(getPaddingLeft(), statusBarHeight, getPaddingRight(), getPaddingBottom());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (type == 0) {
            setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                    statusBarHeight);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

}
