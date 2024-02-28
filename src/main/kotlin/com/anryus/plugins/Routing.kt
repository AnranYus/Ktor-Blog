package com.anryus.plugins

import com.anryus.route.postRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        postRouting()
    }
}
