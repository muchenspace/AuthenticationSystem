package com.example.verification

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.StringRedisTemplate

@SpringBootTest
class VerificationApplicationTests
{
	@Autowired
	lateinit var stringRedisTemplate:StringRedisTemplate

	@Test
	fun testSet()
	{
		var op = stringRedisTemplate.opsForValue()
		op.set("name","111")
	}
	@Test
	fun testGet()
	{
		var op = stringRedisTemplate.opsForValue()
		System.out.println(op.get("name"))
	}
}
