package com.example.test2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.test2.ViewModel.CourseProgressViewModel
import com.example.test2.adapter.SentenceAdapter
import com.example.test2.adapter.WordAdapter
import com.example.test2.data.SentenceData
import com.example.test2.data.WordData
import com.example.test2.databinding.FragmentCourseBinding
import com.example.test2.databinding.FragmentHomePageBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [Course.newInstance] factory method to
 * create an instance of this fragment.
 */
class Course : Fragment() {

    private var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding!!

    private lateinit var course_n: String
    private lateinit var wordList: RecyclerView
    private lateinit var senList: RecyclerView

    private lateinit var sharedViewModel: CourseProgressViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            course_n = it.getString("course_name").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.coureNameHeader.text = course_n


        binding.courseProgress.max = WordData.getWordForCourse(course_n).size
            + SentenceData.getSentenceByCourse(course_n).size

        sharedViewModel = ViewModelProvider(requireActivity()).get(CourseProgressViewModel::class.java)

        sharedViewModel.progressValue.observe(viewLifecycleOwner, Observer { progress ->
            binding.courseProgress.progress = progress
        })

        wordList = binding.coursePageWordList
        wordList.adapter = WordAdapter(WordData.getWordForCourse(course_n))
        senList = binding.coursePageSentenceList
        senList.adapter = SentenceAdapter(SentenceData.getSentenceByCourse(course_n))
    }
}