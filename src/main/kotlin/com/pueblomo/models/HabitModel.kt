package com.pueblomo.models

import com.pueblomo.schemas.HabitType
import kotlinx.serialization.Serializable

@Serializable
data class HabitModel(
    val tagId: String,
    val name: String,
    val characterId: String,
    val type: HabitType,
    val goal: Long
)
