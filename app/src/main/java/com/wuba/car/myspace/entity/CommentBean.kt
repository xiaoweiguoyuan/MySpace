package com.wuba.car.myspace.entity


open class CommentBean: BaseType() {
    var id: String? = null
    var user: User? = null
    var time: String? = null
    var content: String? = null
    var cursor: Double? = null
    var user_id: String? = null
}