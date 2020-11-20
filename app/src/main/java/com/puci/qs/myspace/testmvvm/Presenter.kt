package com.puci.qs.myspace.testmvvm

import android.content.Context
import android.view.View
import android.widget.Toast
import com.puci.qs.qishuier.databinding.ActivityMVVMBinding


class Presenter(var user: User, var binding: ActivityMVVMBinding, var context: Context, var user2: User2) {


    //普通方法绑定
    fun onTextChanged(s: CharSequence, start: Int, before: Int, color: Int) {
        user.setFristName(s.toString())
        binding.userInfo = user
        //user2 集成了baseObserver，因此不需要手动更新了
        user2.setFristName(s.toString())
    }

    //普通方法绑定
    fun onClick(view: View?) {
        Toast.makeText(context, "点击了", Toast.LENGTH_LONG).show()
    }

    //监听器绑定，可以返回数据
    fun onClickListenerBinding(user: User) {
        Toast.makeText(
            context,
            user.getLastName(),
            Toast.LENGTH_LONG
        ).show()
        user.setIsShow(!user.getIsShow().get()!!)
        user.getNums().add(0,"人生")
        user.getmaps().put("name", "如梦")
        var map = HashMap<String, String>()
        user.getLastName()?.let { map.put("leavesC", it) }
        binding.map = map
//        binding.notifyPropertyChanged(BR.map)

//        var player =  QSPlayer()
//        var url = "https://www.kugou.com/song/#hash=D5EC54A91E6ADECD109BB562DC747624&album_id=39395624"
//        url = "zaitaxiang.mp3"
//        player.setMediaPath(url)
//        player.start()
//        var player = AudioPlayer(context)
////        player.setMediaPath("assets:///zaitaxiang.mp3")
//        player.setMediaPath("https://audio.xmcdn.com/group85/M06/B5/B1/wKg5JV9ORw6RIo-yA9GPSIf6ZZc550.m4a")
//        player.start()
    }

//    fun onAudioTest() {
//        var player =  QishuiAudioPlayer(context)
//        var url = "https://www.kugou.com/song/#hash=D5EC54A91E6ADECD109BB562DC747624&album_id=39395624"
//        player.setVideoPath(url)
//    }
}
