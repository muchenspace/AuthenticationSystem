package com.example.verification.config

import com.example.verification.Interceptor.LoginInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class webConfig: WebMvcConfigurer
{
    @Autowired
    private lateinit var Interceptor : LoginInterceptor

    override fun addInterceptors(registry: InterceptorRegistry)
    {
        registry.addInterceptor(Interceptor).excludePathPatterns("/user/login","/user/register","/key/login")
    }
}