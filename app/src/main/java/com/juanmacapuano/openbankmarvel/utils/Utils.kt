package com.juanmacapuano.openbankmarvel.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Utils {

    companion object {
        const val BASEURL = "https://gateway.marvel.com/"
        val ts = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = ""
        const val PRIVATE_KEY = ""
        fun hash(): String {
            val input = "$ts$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }
        const val limit = "20"
    }
}