package com.pueblomo.schemas

import com.pueblomo.models.AccomplishedHabitModel
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*


class AccomplishedHabit(id: EntityID<UUID>) : Entity<UUID>(id) {
    companion object : EntityClass<UUID, AccomplishedHabit>(AccomplishedHabits)

    var tagId by AccomplishedHabits.tagId
    var time by AccomplishedHabits.time
    var date by AccomplishedHabits.date
}

fun AccomplishedHabit.toModel(): AccomplishedHabitModel {
    return AccomplishedHabitModel(
        tagId = this.tagId,
        time = this.time,
        date = this.date
    )
}

object AccomplishedHabits : UUIDTable() {
    val tagId = varchar("tag_id", 36)
    val time = long("time")
    val date = varchar("date", 10)
}