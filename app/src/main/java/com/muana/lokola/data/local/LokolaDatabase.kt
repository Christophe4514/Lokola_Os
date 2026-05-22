package com.muana.lokola.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muana.lokola.data.model.Lesson

@Database(entities = [Lesson::class], version = 1, exportSchema = false)
abstract class LokolaDatabase : RoomDatabase() {
    abstract fun lessonDao(): LessonDao
}
