package com.dome.jetpack.ui.base;

import android.view.View;

public interface OnItemClickListener<T> {
    /**
     * 点击
     *
     * @param v        点击的view
     * @param t        返回Item
     * @param position 位置
     */
    void onItemClick(
            View v,
            T t,
            int position
    );
}