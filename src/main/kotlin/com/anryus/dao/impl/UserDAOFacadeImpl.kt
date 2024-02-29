package com.anryus.dao.impl

import com.anryus.dao.DatabaseSingleton.query
import com.anryus.dao.UserDAOFacade
import com.anryus.model.User
import com.anryus.model.Users
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UserDAOFacadeImpl : UserDAOFacade {
    private fun resultRowToUser(row: ResultRow) = User(
        id = row[Users.id],
        username = row[Users.username],
        password = row[Users.password],
        role = row[Users.role],
        createTime = row[Users.createTime],
        updateTime = row[Users.updateTime],
        deleteTime = row[Users.deleteTime]
    )

    override suspend fun userInfo(username: String): User? = query {
        Users.select(Users.username eq username).map(::resultRowToUser).singleOrNull()
    }

    override suspend fun addNewUser(username: String, password: String, role: Int): User? = query {
        val insertStatement = Users.insert {
            it[Users.username] = username
            it[Users.password] = password
            it[Users.role] = role
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }
}