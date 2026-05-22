package com.muana.lokola.data.repository

import com.muana.lokola.data.local.LessonDao
import com.muana.lokola.data.model.Lesson
import com.muana.lokola.data.model.LessonCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonRepository @Inject constructor(
    private val lessonDao: LessonDao
) {
    fun getAllLessons(): Flow<List<Lesson>> {
        return lessonDao.getAllLessons()
    }

    fun getLessonsByCategory(category: LessonCategory): Flow<List<Lesson>> {
        return lessonDao.getLessonsByCategory(category)
    }

    suspend fun getLessonById(lessonId: Int): Lesson? {
        return lessonDao.getLessonById(lessonId)
    }

    fun getLessonByIdFlow(lessonId: Int): Flow<Lesson?> {
        return lessonDao.getLessonByIdFlow(lessonId)
    }

    suspend fun insertLesson(lesson: Lesson) {
        lessonDao.insertLesson(lesson)
    }

    suspend fun insertLessons(lessons: List<Lesson>) {
        lessonDao.insertLessons(lessons)
    }

    suspend fun updateLessonProgress(lessonId: Int, progress: Int, isCompleted: Boolean) {
        lessonDao.updateLessonProgress(lessonId, progress, isCompleted)
    }
}
