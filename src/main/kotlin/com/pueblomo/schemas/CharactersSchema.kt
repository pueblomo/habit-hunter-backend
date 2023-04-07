package com.pueblomo.schemas

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

class Character(id: EntityID<UUID>) : Entity<UUID>(id) {
    companion object : EntityClass<UUID, Character>(Characters)

    var name by Characters.name
}

object Characters : UUIDTable() {
    val name = varchar("name", 50)

}