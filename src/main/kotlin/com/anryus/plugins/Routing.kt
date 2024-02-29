package com.anryus.plugins

import com.anryus.route.postRouting
import com.anryus.route.userRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        postRouting()
        userRouting()
    }
}
