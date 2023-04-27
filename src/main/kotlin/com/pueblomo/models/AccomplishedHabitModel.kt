package com.pueblomo.models

import kotlinx.serialization.Serializable

@Serializable
data class AccomplishedHabitModel(
    val tagId: String,
    val time: Long,
    val date: String = ""
)
