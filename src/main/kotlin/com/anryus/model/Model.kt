package com.anryus.model

import kotlinx.datetime.Clock
import org.jetbrains.exposed.sql.Table

abstract class Model{
    abstract val id:Int
    abstract val createTime: Long
    abstract val updateTime: Long?
    abstract val deleteTime: Long?
}

open class Table:Table(){
    val id = integer("id").default(0).autoIncrement()
    val createTime = long("createTime").default(Clock.System.now().toEpochMilliseconds())
    val updateTime = long("updateTime").default(Clock.System.now().toEpochMilliseconds())
    val deleteTime = long("deleteTime").default(-1)
}

inline fun <reified T,reified E> T.convert(block:T.(E) ->Unit):E{
    val instance = E::class.java.getDeclaredConstructor().newInstance()
    block.invoke(this,instance)
    return instance
}
