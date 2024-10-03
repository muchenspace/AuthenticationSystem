package com.example.verification.service

import com.example.verification.pojo.key
import java.time.LocalDateTime

interface KeyService
{
    fun add(key: String, appId: Int,maturityTime: LocalDateTime)
    fun findByAppId(appId: Int): List<key>?
    fun delete(id: Int)
    fun findByKey(key: String): key?
}