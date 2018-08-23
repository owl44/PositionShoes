package com.shoes.position.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shoes.position.ui.location.TrajectoryActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */

public class SelectorUtils {
    public static ArrayList<String> options1Items = new ArrayList<>();

    public static void showTime(Context context, OnTimeSelectListener listener) {
        /**
         *   //正确设置方式 原因：注意事项有说明
         startDate.set(2013,0,1);
         endDate.set(2020,11,31);
         */
        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(context, listener)
                .setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setSubmitColor(Color.WHITE)//确定按钮文字颜色
                .setCancelColor(Color.WHITE)
                .setTitleBgColor(0xFF1E3E61)//标题背景颜色 Night mode
                .setOutSideCancelable(true)
                .setBgColor(0xFF153857)
                .build();
        pvTime.show();
    }
}
