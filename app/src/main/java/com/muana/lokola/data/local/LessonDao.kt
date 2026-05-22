package com.muana.lokola.data.local

import androidx.room.*
import com.muana.lokola.data.model.Lesson
import com.muana.lokola.data.model.LessonCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {

    @Query("SELECT * FROM lessons ORDER BY id ASC")
    fun getAllLessons(): Flow<List<Lesson>>

    @Query("SELECT * FROM lessons WHERE category = :category ORDER BY id ASC")
    fun getLessonsByCategory(category: LessonCategory): Flow<List<Lesson>>

    @Query("SELECT * FROM lessons WHERE id = :lessonId")
    suspend fun getLessonById(lessonId: Int): Lesson?

    @Query("SELECT * FROM lessons WHERE id = :lessonId")
    fun getLessonByIdFlow(lessonId: Int): Flow<Lesson?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: Lesson)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLessons(lessons: List<Lesson>)

    @Update
    suspend fun updateLesson(lesson: Lesson)

    @Query("UPDATE lessons SET progress = :progress, isCompleted = :isCompleted WHERE id = :lessonId")
    suspend fun updateLessonProgress(lessonId: Int, progress: Int, isCompleted: Boolean)

    @Delete
    suspend fun deleteLesson(lesson: Lesson)
}
