package com.anryus.dao

import com.anryus.blog.model.Posts
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object  DatabaseSingleton {
    fun init(){
        val driverClassName = "com.mysql.cj.jdbc.Driver"
        val url = "jdbc:mysql://localhost:3306/ktor_blog?username=ktor-blog&password=ktor-blog"
        val database = Database.connect(url, driverClassName)
        transaction(database) {
            SchemaUtils.create(Posts)
        }
    }

    suspend fun <T> query(block: suspend () -> T):T = newSuspendedTransaction(Dispatchers.IO) {
        block.invoke()
    }
}