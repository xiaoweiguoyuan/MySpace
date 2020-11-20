package com.wuba.car.myspace.testmvvm;

import androidx.databinding.BindingConversion;

/**
 * 如果BindingConversion与BindingAdapter同时生效，前者优先级高，先执行前者
 */
public class BindConvers {

    @BindingConversion
    public static String conversionStr(String text) {
        //与BindingAdapter类似，以下方法会将布局文件中所有以@{String}方式引用到的String类型变量加上后缀-conversionString
        return text + "-conversionStr";
    }
}
