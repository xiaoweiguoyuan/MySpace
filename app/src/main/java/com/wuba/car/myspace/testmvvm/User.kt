package com.wuba.car.myspace.testmvvm

import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableField


open class User {
    fun setFristName(toString: String) {
        firstname = toString
    }

    open fun getFristName(): String? {
        return firstname
    }

    fun getLastName(): String? {
        return firstname
    }

    private var firstname: String = ""
    var name:String = "zhangsan"

    var password: String = "nicai"
//    ObservableField是为了解决一个数据类中不需要所有的数据都进行监听时代替BaseObservable，让类当前类不需要继承BaseObservable也不需要重新notifyPropertyChanged方法
    //说白了，就是不需要使用notifyPropertyChanged方法了
    private var isShow: ObservableField<Boolean?> = ObservableField(true)
    private var nums = ObservableArrayList<String>()
    private var maps = ObservableArrayMap<String, String>()
    open fun getNums(): ObservableArrayList<String> {
        return nums
    }

    open fun setNums(nms: ObservableArrayList<String>) {
        nums = nms
    }

    open fun setmaps(map: ObservableArrayMap<String, String>) {
        maps = map
    }

    open fun getmaps(): ObservableArrayMap<String, String> {
        return maps
    }

    open fun setIsShow(b: Boolean) {
        this.isShow?.set(b!!)
    }

    open fun getIsShow(): ObservableField<Boolean?> {
        return this.isShow
    }

}