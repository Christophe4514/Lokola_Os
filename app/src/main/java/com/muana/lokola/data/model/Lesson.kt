package com.muana.lokola.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lessons")
data class Lesson(
    @PrimaryKey val id: Int,
    val title: String,
    val category: LessonCategory,
    val content: String,
    val imageUrl: String?,
    val progress: Int = 0, // 0-100
    val isCompleted: Boolean = false,
    val isDownloaded: Boolean = true // Available offline by default for MVP
)

enum class LessonCategory {
    MATH,
    FRENCH,
    SCIENCE
}
