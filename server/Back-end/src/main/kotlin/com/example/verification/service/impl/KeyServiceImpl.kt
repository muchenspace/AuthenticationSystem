package com.example.verification.service.impl

import com.example.verification.mapper.KeyMapper
import com.example.verification.pojo.key
import com.example.verification.service.KeyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class KeyServiceImpl: KeyService
{
    @Autowired
    private lateinit var mapper: KeyMapper

    override fun add(key: String, appId: Int,maturityTime: LocalDateTime)
    {
        mapper.add(key, appId,maturityTime)
    }

    override fun findByAppId(appId: Int) :List<key>?
    {
        return mapper.findByAppId(appId)
    }

    override fun delete(id: Int)
    {
        mapper.delete(id)
    }

    override fun findByKey(key: String): key?
    {
        return mapper.findByKey(key)
    }
}