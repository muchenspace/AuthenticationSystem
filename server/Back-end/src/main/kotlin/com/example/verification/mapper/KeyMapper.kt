package com.example.verification.mapper

import com.example.verification.pojo.key
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import java.time.LocalDateTime

@Mapper
interface KeyMapper
{
    @Insert("insert into keyTable(content,appId,create_time,maturity_time)" + "values(#{key},#{appId},now(),#{maturityTime})")
    fun add(key: String, appId: Int,maturityTime: LocalDateTime)
    @Select("select * from keytable where appId = #{appId}")
    fun findByAppId(appId: Int): List<key>?
    @Delete("delete from keytable where id = #{id}")
    fun delete(id: Int)
    @Select("select * from keytable where content = #{key}")
    fun findByKey(key: String): key?
}