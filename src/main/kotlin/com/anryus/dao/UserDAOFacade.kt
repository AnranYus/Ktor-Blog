package com.anryus.dao

import com.anryus.model.User

interface UserDAOFacade {
    suspend fun userInfo(username: String): User?
    suspend fun addNewUser(username: String, password: String, role: Int): User?
}