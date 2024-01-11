package com.example.test2.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CourseProgressViewModel:ViewModel() {
    private val _progressValue = MutableLiveData<Int>()

    val progressValue: LiveData<Int>
        get() = _progressValue

    init {
        _progressValue.value = 0
    }

    fun increaseProgress() {
        val currentValue = _progressValue.value ?: 0
        if (currentValue < 100) {
            _progressValue.value = currentValue + 1
        }
    }
}