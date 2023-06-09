package com.example.scribbles.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemServiceName
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.scribbles.R
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
        binding.createNoteEditText.requestFocus() // to focus on editText

        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.createNoteEditText,InputMethodManager.SHOW_IMPLICIT)
        // To show keyboard


        var priority = 1
        binding.greenPriority.setImageResource(R.drawable.baseline_done_24)

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


        binding.createNoteFab.setOnClickListener {
            val title = binding.createTitleEditText.text.toString()
            val text = binding.createNoteEditText.text.toString()

            val d = Date()
            val date = DateFormat.format("MMMM d, yyyy ", d.time).toString()

        val note = Note(title = title, text = text, priority = priority,date =date)
        viewModel.insert(note)
            findNavController().navigateUp()

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}