package com.example.verification.service

import com.example.verification.pojo.app

interface appService
{
    fun add(appName: String)
    fun getAll(): List<app>?
    fun findByName(appName: String): app?
    fun delete(id: Int)
}