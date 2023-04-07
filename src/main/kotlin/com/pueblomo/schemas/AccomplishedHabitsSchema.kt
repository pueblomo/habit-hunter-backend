package com.pueblomo.schemas

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*


class AccomplishedHabit(id: EntityID<UUID>) : Entity<UUID>(id) {
    companion object : EntityClass<UUID, AccomplishedHabit>(AccomplishedHabits)

    var tagId by AccomplishedHabits.tagId
    var time by AccomplishedHabits.time
}

object AccomplishedHabits : UUIDTable() {
    val tagId = varchar("habit_id", 36)
    val time = varchar("time", 8)
}