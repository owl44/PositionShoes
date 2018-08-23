package com.shoes.position.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 自定义ScrollView，不让ScrollView自动切换子view的焦点
 * Created by Administrator on 2018/8/23.
 */

public class NonFocusingScrollView extends ScrollView {

    public NonFocusingScrollView(Context context) {
        super(context);
    }

    public NonFocusingScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NonFocusingScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * TODO 不让ScrollView自动切换子View的焦点
     * @param direction
     * @param previouslyFocusedRect
     * @return
     */
    @Override
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        return true;
    }

}
