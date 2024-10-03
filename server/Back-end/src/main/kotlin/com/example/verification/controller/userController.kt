package com.example.verification.controller

import org.springframework.beans.factory.annotation.Autowired
import com.example.verification.pojo.Result
import com.example.verification.pojo.User
import com.example.verification.service.userService
import com.example.verification.utils.JwtUtil
import com.example.verification.utils.ThreadLocalUtil
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.regex.PatternSyntaxException

@RestController
@RequestMapping("/user/")
@Validated
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class UserController
{
    @Autowired
    private lateinit var service:userService

    @Autowired
    lateinit var stringRedisTemplate: StringRedisTemplate

    @PostMapping("register")
    public fun register(@Pattern(regexp = "\\S{5,16}")name:String, @Pattern(regexp = "\\S{5,16}")passwd:String): Result<Any?>
    {
        if (service.findByName(name) != null)
        {
            return Result(1,"用户名重复")
        }
        service.add(name,passwd)
        return Result(0, "注册成功")
    }
    @PostMapping("login")
    public fun login(@Pattern(regexp = "\\S{5,16}")name:String, @Pattern(regexp = "\\S{5,16}")passwd:String):Result<Any?>
    {
        val user = service.findByName(name) ?: return Result(1,"用户不存在")
        if (user.password != passwd)
        {
            return Result(1,"密码错误")
        }
        val claims:MutableMap<String, Any> = mutableMapOf()
        claims["name"] = user.username
        claims["id"] = user.id
        val token = JwtUtil.genToken(claims)
        val op = stringRedisTemplate.opsForValue()
        op.set(user.username,token)
        return Result<Any?>(0, "登录成功", token)
    }
    @GetMapping
    public fun getUserInfo():Result<User>
    {
        return Result<User>(0,"操作成功",service.findByName(ThreadLocalUtil.get<Map<String,Any>>().get("name") as String))
    }


}