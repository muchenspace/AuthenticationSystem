package com.example.verification.pojo

import java.time.LocalDateTime


class User {
    var id: Int = -1 //主键ID
    var username: String = "" //用户名
    var password: String = "" //密码
    var nickname: String = "" //昵称
    var email: String = "" //邮箱
    var userPic: String = "" //用户头像地址
    var createTime: LocalDateTime? = null //创建时间
    var updateTime: LocalDateTime? = null //更新时间
}
