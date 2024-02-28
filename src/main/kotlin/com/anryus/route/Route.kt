package com.anryus.route

import com.anryus.dao.impl.PostDAOFacadeImpl
import io.ktor.http.*
import io.ktor.server.application.*
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