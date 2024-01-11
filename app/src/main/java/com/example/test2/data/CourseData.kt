package com.example.test2.data

import com.example.test2.model.Course

object CourseData {
    private val courseList:List<Course> = listOf(
        Course("Hello"),
        Course("Computer"),
        Course("Pet"),
    )

    fun getDataSet():List<Course>{
        return courseList
    }
}