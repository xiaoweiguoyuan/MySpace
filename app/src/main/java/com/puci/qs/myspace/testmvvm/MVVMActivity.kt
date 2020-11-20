package com.puci.qs.myspace.testmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.puci.qs.qishuier.R
import com.puci.qs.qishuier.databinding.ActivityMVVMBinding

class MVVMActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_m_v_v_m)
        val binding = DataBindingUtil.setContentView<ActivityMVVMBinding>(this,
            R.layout.activity_m_v_v_m
        )

//        val mvvmViewModel = MVVMViewModel(application, this)
//        binding.setViewModel(mvvmViewModel)
//        binding.setVariable(R.id.tv_model, mvvmViewModel)
        var user = User()
        user.name = "测试测试"
        user.password = "哈哈哈"

        binding.tvName.setText(user.name)
        binding.userInfo = user
        var user2 = User2()
        user2.setImageUrl("https://img-blog.csdn.net/20180530222630444?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2h1YW5neGlhb2d1bzE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70")
        binding.user2 = user2
        var presenter = Presenter(user, binding, this, user2)
        binding.presenter = presenter

        user.getNums().add("人生如梦")
        user.getmaps().put("name", "David")
    }
}