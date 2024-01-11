package com.example.test2.ViewModel

import CourseDAO
import CourseSQL
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.test2.data.CourseData
import com.example.test2.model.Course
import kotlinx.coroutines.launch

class CourseItemViewModel():ViewModel() {
    private var _courseSet: List<Course> = CourseData.getDataSet()

//    private fun insertItem(coure: CourseSQL) {
//        viewModelScope.launch {
//            coureDAO.insertCourse(coure)
//        }
//    }

    val courseSet:List<Course>
        get() = _courseSet
}

//class CourseItemViewModelFactory(private var coureDAO: CourseDAO):ViewModelProvider.Factory{
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(CourseItemViewModel::class.java)){
//            @Suppress("UNCHECKED_CAST")
//            return CourseItemViewModel(coureDAO) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}