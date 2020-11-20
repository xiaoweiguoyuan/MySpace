package com.wuba.car.myspace.testmvvm

import android.app.Application
import com.wuba.car.qishuier.databinding.ActivityMVVMBinding

public class MVVMViewModel(var context: Application, var binding:ActivityMVVMBinding) {

    lateinit var mvvmModel: MVVMMoel
    var result:String = ""

    open fun getData() {
//        binding.tvModel
    }
}