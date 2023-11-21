package com.example.test2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test2.R
import com.example.test2.data.CourseData
import com.example.test2.model.Course

class CourseAdapter(): RecyclerView.Adapter<CourseAdapter.CourseCardViewHolder>() {

    val courseSet:List<Course> = CourseData.courseList
    class CourseCardViewHolder(view :View) : RecyclerView.ViewHolder(view){
        val courseName:TextView = view.findViewById(R.id.course_card_text)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseCardViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_item, parent, false)
        return CourseCardViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return courseSet.size
    }

    override fun onBindViewHolder(holder: CourseCardViewHolder, position: Int) {
        holder.courseName.text = courseSet[position].name
    }
}