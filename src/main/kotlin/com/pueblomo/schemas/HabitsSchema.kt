package com.pueblomo.schemas

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

class Habit(id: EntityID<UUID>) : Entity<UUID>(id) {
    companion object : EntityClass<UUID, Habit>(Habits)

    var tagId by Habits.tagId
    var name by Habits.name
    var characterId by Habits.characterId
    var type by Habits.type
    var goal by Habits.goal
}


object Habits : UUIDTable() {
    val tagId = varchar("tag_id", 36)
    val name = varchar("name", 250)
    val characterId = varchar("character_id", 36)
    val type = varchar("type", 10)
    val goal = long("goal")
}

enum class HabitType {
    PERIOD,
    QUANTITY
}