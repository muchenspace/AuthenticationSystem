package com.example.verification.service.impl

import com.example.verification.mapper.userMapper
import com.example.verification.pojo.User
import com.example.verification.service.userService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class userServiceImpl: userService
{
    @Autowired
    private lateinit var mapper: userMapper

    override fun add(name: String, passwd: String)
    {
        mapper.add(name,passwd);
    }

    override fun findByName(name: String): User?
    {
        return mapper.findByName(name)
    }

}