package com.example.sovialservice_mobile.model

import android.util.Log
import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object Db {
    const val jdbcUrl = "jdbc:mysql://192.168.0.102:3306/socservices"
    const val username = "root"
    const val password = "12345678"

    init {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver")
        } catch (e: ClassNotFoundException) {
            throw ClassNotFoundException("MySQL JDBC Driver not found", e)
        }
    }

    fun fff() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver")
            val conn = DriverManager.getConnection(jdbcUrl, username, password)
        } catch (e: Exception) {
            e.printStackTrace()
            password
        }
    }
}