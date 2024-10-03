package com.example.verification.pojo

import java.time.LocalDateTime

class app(
    var id: Int = -1,
    var name: String? = null,
    var createUser: Int = -1,
    var createTime: LocalDateTime? = null, //创建时间
    var updateTime: LocalDateTime? = null //更新时间
)