package com.example.verification.service.impl

import com.example.verification.mapper.appMapper
import com.example.verification.pojo.app
import com.example.verification.service.appService
import com.example.verification.utils.ThreadLocalUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class appServiceImpl : appService
{
    @Autowired
    private lateinit var mapper: appMapper
    override fun add(appName: String)
    {
        mapper.add(appName,ThreadLocalUtil.get<Map<String,Any>>().get("id") as Int)
    }

    override fun getAll(): List<app>?
    {
        return mapper.getAll(ThreadLocalUtil.get<Map<String,Any>>().get("id") as Int)
    }

    override fun findByName(appName: String) : app?
    {
        return mapper.findByName(appName)
    }

    override fun delete(id: Int)
    {
        mapper.delete(id)
    }
}