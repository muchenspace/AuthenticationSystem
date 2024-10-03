package com.example.verification.mapper

import com.example.verification.pojo.app
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface appMapper
{
    @Insert("insert into app(name,create_user,create_time,update_time)" + "values(#{appName},#{userId},now(),now())")
    fun add(appName: String,userId: Int)

    @Select("select * from app where create_user = #{userId}")
    fun getAll(userId: Int): List<app>?

    @Select("select * from app where name = #{appName}")
    fun findByName(appName: String) : app?

    @Delete("delete from app where id=#{id}")
    fun delete(id: Int)
}