package com.pueblomo.plugins

import com.pueblomo.models.AccomplishedHabitModel
import com.pueblomo.schemas.AccomplishedHabit
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        post("/accomplishedHabits") {
            val accomplishedHabitModel = call.receive<AccomplishedHabitModel>()
            transaction {
                AccomplishedHabit.new {
                    time = accomplishedHabitModel.time
                    tagId = accomplishedHabitModel.tagId
                }
            }
        }
    }
}
