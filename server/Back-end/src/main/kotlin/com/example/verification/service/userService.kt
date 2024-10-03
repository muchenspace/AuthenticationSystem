package com.example.verification.service

import com.example.verification.pojo.User

interface userService
{
    fun add(name: String, passwd: String)
    fun findByName(name: String): User?
}