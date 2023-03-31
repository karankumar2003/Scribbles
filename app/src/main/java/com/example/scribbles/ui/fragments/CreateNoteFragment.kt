package com.example.scribbles.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.scribbles.databinding.FragmentCreateNoteBinding
import com.example.scribbles.models.Note
import com.example.scribbles.viewModel.NoteViewModel
import java.util.*


class CreateNoteFragment : Fragment() {

    private var _binding: FragmentCreateNoteBinding? = null
    private val binding get() = _binding!!
    private val viewModel : NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createNoteFab.setOnClickListener {
            val title = binding.createTitleEditText.text.toString()
            val subTitle = binding.createSubTitleEditText.text.toString()
            val text = binding.createNoteEditText.text.toString()

            val d = Date()
            val date = DateFormat.format("MMMM d, yyyy ", d.time).toString()

        val note = Note(title = title, subtitle = subTitle, text = text, priority = 1,date =date)
        viewModel.insert(note)

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}