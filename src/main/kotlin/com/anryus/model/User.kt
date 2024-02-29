package com.anryus.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    var password: String,
    val role: Int,
    override val id: Int,
    override val createTime: Long,
    override val updateTime: Long?,
    override val deleteTime: Long?,
) : Model()

object Users : Table() {
    val username = varchar("username", 256).uniqueIndex()
    val password = varchar("password", 256)
    val role = integer("role")

    override val primaryKey = PrimaryKey(id)
}