package com.example.scribbles.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.fragment.navArgs
import com.example.scribbles.R
import com.example.scribbles.databinding.FragmentEditNoteBinding
import com.example.scribbles.models.Note
import com.example.scribbles.viewModel.NoteViewModel
import java.util.*


class EditNoteFragment : Fragment() {

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    private val args : EditNoteFragmentArgs by navArgs()
    private val viewModel : NoteViewModel by viewModels()
    var priority : Int = -1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.EditTitleEditText.setText(args.note.title)
        binding.EditNoteEditText.setText(args.note.text)
        priority = args.note.priority

        when(priority){
            1->{binding.greenPriority.setImageResource(R.drawable.baseline_done_24)}
            2->{binding.yellowPriority.setImageResource(R.drawable.baseline_done_24)}
            3->{binding.redPriority.setImageResource(R.drawable.baseline_done_24)}
        }

        binding.greenPriority.setOnClickListener{
            priority = 1
            binding.greenPriority.setImageResource(R.drawable.baseline_done_24)
            binding.yellowPriority.setImageResource(0)
            binding.redPriority.setImageResource(0)
        }
        binding.yellowPriority.setOnClickListener{
            priority = 2
            binding.yellowPriority.setImageResource(R.drawable.baseline_done_24)
            binding.greenPriority.setImageResource(0)
            binding.redPriority.setImageResource(0)
        }
        binding.redPriority.setOnClickListener{
            priority = 3
            binding.redPriority.setImageResource(R.drawable.baseline_done_24)
            binding.yellowPriority.setImageResource(0)
            binding.greenPriority.setImageResource(0)
        }

        binding.saveChangesNoteFab.setOnClickListener {
            updateNote()
        }

    }

    private fun updateNote() {
        val newTitle = binding.EditTitleEditText.text.toString()
        val newText = binding.EditNoteEditText.text.toString()
        val date = Date()
        val newDate = DateFormat.format("MMMM d, yyyy ", date.time).toString()

        val note = Note(args.note.id,newTitle,newText,priority,newDate)

        viewModel.update(note)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}