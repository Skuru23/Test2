package com.example.test2


import AppDatabase
import CourseSQL
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.room.Room
import com.example.test2.application.DataApp
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val course = CourseSQL(name = "English 101")
        GlobalScope.launch {
            try {
                val lmao: DataApp = DataApp()
                val courseDao = lmao.database.courseDao()
                courseDao.insertCourse(course)
                val allCourses = courseDao.getAllCourses()
                Log.d("myLog", "Number of course: ${allCourses.size}")
            }catch (e:Exception){
                Log.d("myLog", e.toString())
            }
        }
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        val bottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottom_nav)
        setupWithNavController(bottomNavigationView, navController)
    }
}