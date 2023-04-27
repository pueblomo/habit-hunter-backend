package com.pueblomo.plugins

import com.pueblomo.models.AccomplishedHabitModel
import com.pueblomo.models.HabitModel
import com.pueblomo.schemas.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

const val datePatter = "dd.MM.yyyy"
val sdf = SimpleDateFormat(datePatter, Locale.getDefault())
fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/accomplishedHabits") {
            val now = LocalDate.now()
            val startOfWeek = now.with(DayOfWeek.MONDAY).format(DateTimeFormatter.ofPattern(datePatter))
            val endOfWeek = now.with(DayOfWeek.SUNDAY).format(DateTimeFormatter.ofPattern(datePatter))
            call.application.environment.log.info("$startOfWeek : $endOfWeek")
            var accomplishedHabitsJson = ""
            transaction {
                val accomplishedHabits = AccomplishedHabit.find {
                    AccomplishedHabits.date greaterEq startOfWeek and (
                            AccomplishedHabits.date lessEq endOfWeek
                            )
                }.map { accomplishedHabit -> accomplishedHabit.toModel() }
                accomplishedHabitsJson = Json.encodeToString(accomplishedHabits)
            }
            call.respond(accomplishedHabitsJson)
        }

        post("/accomplishedHabits") {
            val accomplishedHabitModel = call.receive<AccomplishedHabitModel>()
            transaction {
                AccomplishedHabit.new {
                    time = accomplishedHabitModel.time
                    tagId = accomplishedHabitModel.tagId
                    date = sdf.format(Date())
                }
            }
            call.respond(HttpStatusCode.OK)
        }

        post("/habits") {
            val habitModel = call.receive<HabitModel>()
            transaction {
                Habit.new {
                    tagId = habitModel.tagId
                    name = habitModel.name
                    characterId = habitModel.characterId
                    type = habitModel.type.name
                    goal = habitModel.goal
                }
                AccomplishedHabit.find { AccomplishedHabits.tagId eq habitModel.tagId }.first().delete()
            }
            call.respond(HttpStatusCode.OK)
        }

        get("/unknownHabits") {
            var unknownTagId = ""
            transaction {
                val unknwonHabits = AccomplishedHabit.all()
                    .filter { accomplishedHabit -> Habit.find { Habits.tagId eq accomplishedHabit.tagId }.empty() }
                unknownTagId = unknwonHabits.first().tagId
            }
            call.respond(unknownTagId)
        }
    }
}
