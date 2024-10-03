package com.example.verification.Interceptor

import com.example.verification.utils.JwtUtil
import com.example.verification.utils.ThreadLocalUtil
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.data.redis.core.StringRedisTemplate

@Component
class LoginInterceptor:HandlerInterceptor
{
    @Autowired
    lateinit var stringRedisTemplate:StringRedisTemplate
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean
    {
        try
        {
            ThreadLocalUtil.set(JwtUtil.parseToken(request.getHeader("Authorization")))
            var op = stringRedisTemplate.opsForValue()
            val token:String? = op[ThreadLocalUtil.get<Map<String,Any>>().get("name") as String]
            if (request.getHeader("Authorization") != token)
            {//token失效
                response.status = 401
                return false
            }
            return true
        } catch (e: Exception)
        {
            response.status = 401
            return false
        }
    }

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: java.lang.Exception?)
    {
        ThreadLocalUtil.remove()
    }
}