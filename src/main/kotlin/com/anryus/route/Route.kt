package com.anryus.route

import com.anryus.dao.impl.PostDAOFacadeImpl
import com.anryus.dao.impl.UserDAOFacadeImpl
import com.anryus.model.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.postRouting() {
    val postDao = PostDAOFacadeImpl()

    route("/posts") {
        get {
            if (postDao.allPost().isNotEmpty()) {
                call.respond(postDao.allPost())
            } else {
                call.respondText("Not found posts.", status = HttpStatusCode.NotFound)
            }
        }

        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText("Miss id", status = HttpStatusCode.BadRequest)

            val post =
                postDao.post(id.toInt()) ?: return@get call.respondText(
                    "Not found post.",
                    status = HttpStatusCode.NotFound
                )

            call.respond(post)
        }


    }

}

fun Route.userRouting() {
    val userDao = UserDAOFacadeImpl()
    route("/user") {

        get("{username?}") {
            val username = call.parameters["username"] ?: return@get call.respondText(
                "Miss username.",
                status = HttpStatusCode.BadRequest
            )

            val user = userDao.userInfo(username) ?: return@get call.respondText(
                "Not found user.",
                status = HttpStatusCode.NotFound
            )

            user.password = ""

            call.respond(user)

        }

        post {
            val user = call.receive<User>()
            val userInfo = userDao.userInfo(user.username)

            if (userInfo!=null){
                return@post call.respondText("Username already used.", status = HttpStatusCode.BadRequest)
            }
            userDao.addNewUser(user.username,user.password,user.role)
            call.respond(HttpStatusCode.OK)
        }
    }
}