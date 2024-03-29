package com.anryus.model

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val title:String,
    val content:String,
    val authorId:Int,
    override val id: Int,
    override val createTime: Long,
    override val updateTime: Long?,
    override val deleteTime: Long?,
):Model()

object Posts: Table(){
    val title = varchar("title",256)
    val content = text("content")
    val authorId = integer("authorId")

    override val primaryKey = PrimaryKey(id)
}