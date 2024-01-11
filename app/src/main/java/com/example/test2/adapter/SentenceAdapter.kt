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
import com.example.test2.model.Sentence
import com.google.android.material.card.MaterialCardView

class SentenceAdapter(
    var senSet: List<Sentence>
): RecyclerView.Adapter<SentenceAdapter.SentenceViewHolder>() {

    class SentenceViewHolder(var view:View): RecyclerView.ViewHolder(view){
        val sentence: TextView = view.findViewById(R.id.sentence_item_text)
        val card: MaterialCardView = view.findViewById(R.id.sen_card)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SentenceViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.sentence_item, parent, false)
        return SentenceViewHolder(adapterLayout)
    }
    override fun getItemCount(): Int {
        return senSet.size
    }

    override fun onBindViewHolder(holder: SentenceViewHolder, position: Int) {
        holder.sentence.text = senSet[position].sentence
        holder.card.setOnClickListener{
            val action = CourseDirections.actionCourse2ToCheck(senSet[position].sentence)
            holder.view.findNavController().navigate(action)
        }
    }


}