package com.muana.lokola.domain.usecase

import com.muana.lokola.data.model.Lesson
import com.muana.lokola.data.repository.LessonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLessonsUseCase @Inject constructor(
    private val repository: LessonRepository
) {
    operator fun invoke(): Flow<List<Lesson>> {
        return repository.getAllLessons()
    }
}
