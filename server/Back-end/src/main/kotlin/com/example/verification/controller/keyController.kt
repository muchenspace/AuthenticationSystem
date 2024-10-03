package com.example.verification.controller

import com.example.verification.pojo.Result
import com.example.verification.pojo.key
import com.example.verification.service.KeyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

@RestController
@RequestMapping("/key/")
@Validated
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class keyController
{
    @Autowired
    private lateinit var service: KeyService

    @GetMapping
    public fun findByAppId(appId:Int):Result<List<key>?>
    {
        return Result(0,"操作成功",service.findByAppId(appId))
    }
    @GetMapping("findByKey")
    fun findByKey(key:String):Result<key?>
    {
        return Result(0,"操作成功",service.findByKey(key))
    }
    @PostMapping("login")
    fun login(appId:Int,key:String):Result<Any?>
    {
        System.out.println("appid:${appId},key:${key}")
        val keys = service.findByAppId(appId) ?: return Result(1,"应用不存在")
        for (item in keys)
        {
            if (item.content == key)
            {
                return if (item.maturityTime!!.isAfter(LocalDateTime.now()) )
                {
                    Result(0,"登录成功",service.findByKey(item.content))
                } else
                {
                    Result(1,"卡密到期")
                }
            }
        }
        return Result(1,"卡密不存在")
    }
    @PostMapping("createKey")
    public fun createKey(count:Int, appId:Int, type:Int):Result<Any?>
    {
        if (count <= 0)
        {
            return Result(1, "密钥数量必须为正数")
        }
        if (type !in 1..3)
        {
            return Result(1, "卡密类型错误")
        }

        val now = LocalDateTime.now()
        val maturityTime = when(type)
        {
            1 -> now.plus(1, ChronoUnit.DAYS) // 天卡
            2 -> now.plus(7, ChronoUnit.DAYS) // 周卡
            3 -> now.plus(30, ChronoUnit.DAYS) // 月卡
            else -> now
        }
        try
        {
            for (i in 1..count)
            {
                val key = UUID.randomUUID().toString()
                service.add(key, appId, maturityTime)
            }
        }
        catch (e: Exception)
        {
            return Result(1, "创建密钥时发生错误: ${e.message}")
        }
        return Result(0, "创建密钥成功")
    }
    @DeleteMapping
    fun delete(id:Int):Result<Any?>
    {
        service.delete(id)
        return Result(0,"删除成功")
    }
}