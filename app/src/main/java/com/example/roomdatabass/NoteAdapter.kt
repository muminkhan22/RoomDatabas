package com.example.roomdatabass

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.roomdatabass.databinding.ItemDegineBinding


class NoteAdapter : ListAdapter<Note,NoteViewHolder>(comparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        return NoteViewHolder(ItemDegineBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
          )
        )

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        getItem(position).let {

            holder.binding.apply {

               titleTV.text=it.title
                timeTV.text=it.time
                dateTV.text=it.date


            }
        }
    }

    companion object{
        var comparator = object : DiffUtil.ItemCallback<Note>(){
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
               return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }


        }

    }
}