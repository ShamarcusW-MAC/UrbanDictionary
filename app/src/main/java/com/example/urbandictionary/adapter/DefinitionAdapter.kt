package com.example.urbandictionary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.urbandictionary.R
import com.example.urbandictionary.model.Definition

class DefinitionAdapter(val definitionList: List<Definition>) :
    RecyclerView.Adapter<DefinitionAdapter.DefinitionViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.dictionary_item_layout,
                parent,
                false)
        return DefinitionViewHolder(view)
    }

    //Returns the size of the list of definitions
    override fun getItemCount(): Int {
        return definitionList.size
    }

    //Binds the data with the view
    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        holder.word.text = definitionList[position].word
        holder.definition.text = definitionList[position].definition
        holder.thumbsUp.text = definitionList[position].thumbsUp.toString()
        holder.thumbsDown.text = definitionList[position].thumbsDown.toString()
    }


    //Data being set to each view
    inner class DefinitionViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val word: TextView = itemView.findViewById(R.id.word_title_textview)
        val definition: TextView = itemView.findViewById(R.id.dictionary_item_textview)
        val thumbsUp: TextView = itemView.findViewById(R.id.thumbs_up_number)
        val thumbsDown: TextView = itemView.findViewById(R.id.thumbs_down_number)

    }

}