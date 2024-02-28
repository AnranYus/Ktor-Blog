package com.anryus.dao.impl

import com.anryus.dao.PostDAOFacade
import com.anryus.dao.DatabaseSingleton.query
import com.anryus.blog.model.Post
import com.anryus.blog.model.Posts
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class PostDAOFacadeImpl : PostDAOFacade {

    private fun resultRowToPost(row:ResultRow) = Post(
        id = row[Posts.id],
        title = row[Posts.title],
        content = row[Posts.content],
        authorId = row[Posts.authorId],
        createTime = row[Posts.createTime],
        updateTime = row[Posts.updateTime],
        deleteTime = row[Posts.deleteTime]
    )

    override suspend fun allPost(): List<Post>  = query {
        Posts.selectAll().map(::resultRowToPost)
    }

    override suspend fun post(id:Int): Post? = query{
        Posts.select(Posts.id eq id).map(::resultRowToPost).singleOrNull()
    }

    override suspend fun addNewPost(title: String, content: String,authorId:Int): Post?=  query{
        val insertStatement = Posts.insert {
            it[Posts.title] = title
            it[Posts.content] = content
            it[Posts.authorId] =authorId
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToPost)
    }
}