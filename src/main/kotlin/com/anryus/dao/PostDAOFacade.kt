package com.anryus.dao

import com.anryus.blog.model.Post

interface PostDAOFacade {
    suspend fun allPost():List<Post>
    suspend fun post(id:Int):Post?
    suspend fun addNewPost(title:String,content:String,authorId:Int):Post?

}