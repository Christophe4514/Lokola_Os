package com.muana.lokola.domain.usecase

import com.muana.lokola.data.model.Lesson
import com.muana.lokola.data.repository.LessonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLessonByIdUseCase @Inject constructor(
    private val repository: LessonRepository
) {
    operator fun invoke(lessonId: Int): Flow<Lesson?> {
        return repository.getLessonByIdFlow(lessonId)
    }
}
