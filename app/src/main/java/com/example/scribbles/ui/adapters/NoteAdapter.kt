package com.example.scribbles.ui.adapters

import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.scribbles.R
import com.example.scribbles.databinding.RvNoteItemBinding
import com.example.scribbles.models.Note
import com.example.scribbles.ui.fragments.HomeFragmentDirections
import javax.security.auth.login.LoginException

class NoteAdapter(var noteList: List<Note>) : RecyclerView.Adapter<NoteAdapter.MyViewHolder>(){

    class MyViewHolder(val binding: RvNoteItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RvNoteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentNote = noteList[position]

        if(currentNote.title.isNotEmpty()){
            holder.binding.titleTV.text = currentNote.title
        }else{
            holder.binding.titleTV.visibility = View.GONE
        }
        holder.binding.textTV.text = currentNote.text
        holder.binding.dateTV.text = currentNote.date

        when(currentNote.priority){
            1->{holder.binding.priorityIV.setImageResource(R.drawable.green_circle)}
            2->{holder.binding.priorityIV.setImageResource(R.drawable.yellow_circle)}
            3->{holder.binding.priorityIV.setImageResource(R.drawable.red_circle)}
        }

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
            Navigation.findNavController(it).navigate(action)
        }
    }


}