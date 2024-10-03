package com.example.verification.utils

/**
 * ThreadLocal 工具类
 */
object ThreadLocalUtil
{
    var THREAD_LOCAL = ThreadLocal<Any>()
    //根据键获取值
    fun <T> get(): T
    {
        return THREAD_LOCAL.get() as T
    }

    //存储键值对
    fun set(value: Any)
    {
        THREAD_LOCAL.set(value)
    }


    //清除ThreadLocal 防止内存泄漏
    fun remove() {
        THREAD_LOCAL.remove()
    }
}
