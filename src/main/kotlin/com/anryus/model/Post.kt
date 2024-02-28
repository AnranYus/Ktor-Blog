package com.anryus.blog.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Post(
    val id:Int,
    val title:String,
    val content:String,
    val authorId:Int,
    val createTime: Long,
    val updateTime: Long,
    val deleteTime: Long
)

object Posts: Table(){
    val id = integer("id").autoIncrement()
    val title = varchar("title",256)
    val content = text("content")
    val authorId = integer("authorId")
    val createTime = long("createTime")
    val updateTime = long("updateTime")
    val deleteTime = long("deleteTime")

    override val primaryKey = PrimaryKey(id)
}