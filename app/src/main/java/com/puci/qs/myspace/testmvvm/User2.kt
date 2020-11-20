package com.puci.qs.myspace.testmvvm

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.puci.qs.qishuier.BR

open class User2 : BaseObservable() {
    private var fristName: String? = null
    private var lastName: String? = null
    private var imageUrl: String? = null

    @Bindable
    open fun getImageUrl(): String? {
        return imageUrl
    }

    open fun setImageUrl(imageUrl: String?) {
        this.imageUrl = imageUrl
        notifyPropertyChanged(BR.imageUrl)
    }

    @Bindable
    open fun getFristName(): String? {
        //如果不标注Bindable，BR内部不会生成firstName属性
        return fristName
    }

    open fun setFristName(fristName: String?) {
        this.fristName = fristName
        notifyPropertyChanged(BR.fristName)
    }

    @Bindable
    open fun getLastName(): String? {
        return lastName
    }

    open fun setLastName(lastName: String?) {
        this.lastName = lastName
//        notifyPropertyChanged(BR.lastName)
    }
}