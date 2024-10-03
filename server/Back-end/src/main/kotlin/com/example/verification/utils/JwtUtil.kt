package com.example.verification.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

object JwtUtil
{
    private const val KEY = "muchen"

    //接收业务数据,生成token并返回
    fun genToken(claims: Map<String, Any>): String
    {
        return JWT.create()
            .withClaim("claims", claims)
            .withExpiresAt(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
            .sign(Algorithm.HMAC256(KEY))
    }

    //接收token,验证token,并返回业务数据
    fun parseToken(token: String): Map<String, Any>
    {
        return JWT.require(Algorithm.HMAC256(KEY))
            .build()
            .verify(token)
            .getClaim("claims")
            .asMap()
    }
}
