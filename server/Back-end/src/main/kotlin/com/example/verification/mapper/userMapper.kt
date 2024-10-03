package com.example.verification.mapper


import com.example.verification.pojo.User
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

@Mapper
interface userMapper
{
    @Insert("insert into user(username,password,create_time,update_time)"+ "values(#{name},#{passwd},now(),now())")
    fun add(name: String, passwd: String)
    @Select("select * from user where username = #{name}")
    fun findByName(name: String): User?
}