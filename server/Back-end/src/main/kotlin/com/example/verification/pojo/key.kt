package com.example.verification.pojo

import java.time.LocalDateTime

class key
(
    var id: Int = -1,
    val content: String? = null,
    val appId:Int = -1,
    val createTime: LocalDateTime? = null, //创建时间
    val maturityTime : LocalDateTime? = null //到期时间
)