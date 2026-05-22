package com.muana.lokola.ui.mayebi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muana.lokola.data.model.Lesson
import com.muana.lokola.domain.usecase.GetLessonByIdUseCase
import com.muana.lokola.domain.usecase.UpdateLessonProgressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonDetailViewModel @Inject constructor(
    private val getLessonByIdUseCase: GetLessonByIdUseCase,
    private val updateLessonProgressUseCase: UpdateLessonProgressUseCase
) : ViewModel() {

    private val _lesson = MutableStateFlow<Lesson?>(null)
    val lesson: StateFlow<Lesson?> = _lesson.asStateFlow()

    fun loadLesson(lessonId: Int) {
        viewModelScope.launch {
            getLessonByIdUseCase(lessonId).collect { lesson ->
                _lesson.value = lesson
            }
        }
    }

    fun getLesson(lessonId: Int) = getLessonByIdUseCase(lessonId)

    fun updateProgress(lessonId: Int, progress: Int, isCompleted: Boolean) {
        viewModelScope.launch {
            updateLessonProgressUseCase(lessonId, progress, isCompleted)
        }
    }
}
