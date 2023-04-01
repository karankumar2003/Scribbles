package com.example.scribbles.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.scribbles.R
import com.example.scribbles.databinding.FragmentCreateNoteBinding
import com.example.scribbles.databinding.FragmentHomeBinding
import com.example.scribbles.ui.adapters.NoteAdapter
import com.example.scribbles.viewModel.NoteViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    val viewModel : NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        binding.newNoteFab.setOnClickListener{
            navController.navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, VERTICAL)


        viewModel.getAllNotes().observe(viewLifecycleOwner){
            binding.recyclerView.adapter = NoteAdapter(it)
        }


    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}