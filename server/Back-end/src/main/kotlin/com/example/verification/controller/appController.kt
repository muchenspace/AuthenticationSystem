package com.example.verification.controller

import com.example.verification.pojo.Result
import com.example.verification.pojo.app
import com.example.verification.service.appService
import com.example.verification.utils.ThreadLocalUtil
import jakarta.validation.constraints.Pattern
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/app/")
@Validated
class appController
{
    @Autowired
    private lateinit var service: appService

    @PostMapping("createApp")
    public fun createApp(@Pattern(regexp = "\\S{2,16}")name: String): Result<Any?>
    {
        val appName = name+ ThreadLocalUtil.get<Map<String,Any>>().get("name") as String
        if(service.findByName(appName)!=null)
        {
            return Result(1, "应用名重复")
        }
        service.add(appName)
        return Result(0, "操作成功")
    }
    @DeleteMapping
    public fun deleteApp(id: Int): Result<Any?>
    {
        service.delete(id)
        return Result(0, "操作成功")
    }
    @GetMapping
    public fun getAll(): Result<List<app>?>
    {
        val allApp:List<app>? = service.getAll()
        return Result(0,"操作成功",allApp)
    }
}