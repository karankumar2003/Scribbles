package com.example.scribbles.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.scribbles.R
import com.example.scribbles.databinding.RvNoteItemBinding
import com.example.scribbles.models.Note

class NoteAdapter(private val noteList: List<Note>) : RecyclerView.Adapter<NoteAdapter.MyViewHolder>(){

    class MyViewHolder(val binding: RvNoteItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RvNoteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentNote = noteList[position]
        holder.binding.titleTV.text = currentNote.title
        holder.binding.subtitleTV.text = currentNote.subtitle
        holder.binding.textTV.text = currentNote.text
        holder.binding.dateTV.text = currentNote.date
        holder.binding.priorityIV.setImageResource(R.drawable.red_circle)
    }
}