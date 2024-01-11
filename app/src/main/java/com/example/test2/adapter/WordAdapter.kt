package com.example.test2.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.test2.CourseDirections
import com.example.test2.R
import com.example.test2.model.WordItem
import com.google.android.material.card.MaterialCardView

class WordAdapter(
    var wordSet: List<WordItem>
): RecyclerView.Adapter<WordAdapter.WordViewHolder>() {
    class WordViewHolder(var view:View): RecyclerView.ViewHolder(view){
        val word: TextView = view.findViewById(R.id.word_item_text)
        val card: MaterialCardView = view.findViewById(R.id.word_card)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.word_item, parent, false)
        return WordViewHolder(adapterLayout)
    }
    override fun getItemCount(): Int {
        return wordSet.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.word.text = wordSet[position].word
        holder.card.setOnClickListener{
            val action = CourseDirections.actionCourse2ToCheck(wordSet[position].word)
            holder.view.findNavController().navigate(action)
        }
    }


}