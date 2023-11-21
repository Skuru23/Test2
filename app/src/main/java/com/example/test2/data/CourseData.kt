package com.example.test2.data

import com.example.test2.model.Course

object CourseData {
    private val courseList:List<Course> = listOf(
        Course("course1"),
        Course("course2"),
        Course("course3"),
        Course("course4"),
        Course("course5"),
        Course("course6"),
        Course("course7"),
    )

    fun getDataSet():List<Course>{
        return courseList
    }
}