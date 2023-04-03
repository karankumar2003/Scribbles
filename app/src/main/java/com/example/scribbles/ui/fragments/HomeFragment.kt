package com.example.scribbles.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.scribbles.R
import com.example.scribbles.databinding.DeleteBottomSheetBinding
import com.example.scribbles.databinding.FragmentHomeBinding
import com.example.scribbles.models.Note
import com.example.scribbles.ui.adapters.NoteAdapter
import com.example.scribbles.viewModel.NoteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.w3c.dom.Text


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    val viewModel : NoteViewModel by viewModels()
    val noteAdapter = NoteAdapter(emptyList())
    var currentList = ArrayList<Note>()

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

        requireActivity().addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_fragment_menu,menu)
                val searchItem = menu.findItem(R.id.search_menu_item)
                val searchView = searchItem.actionView as SearchView
                searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                       return true
                    }

                    override fun onQueryTextChange(query: String?): Boolean {
                        val filteredList = ArrayList<Note>()
                        for(i in currentList){
                            if(i.title.contains(query.toString()) || i.text.contains(query.toString()) ){
                                filteredList.add(i)
                            }
                        }
                        noteAdapter.noteList = filteredList
                        noteAdapter.notifyDataSetChanged()

                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
              return true
            }
        },viewLifecycleOwner,Lifecycle.State.RESUMED)

        binding.newNoteFab.setOnClickListener{
            navController.navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2,VERTICAL)
        binding.recyclerView.adapter= noteAdapter

        viewModel.getAllNotes().observe(viewLifecycleOwner){
            currentList = it as ArrayList<Note>
            noteAdapter.noteList = it
            noteAdapter.notifyDataSetChanged()
        }

        binding.filterLow.setOnClickListener{
            viewModel.getPriorityNotes(1).observe(viewLifecycleOwner){
                currentList = it as ArrayList<Note>
                noteAdapter.noteList = it
                noteAdapter.notifyDataSetChanged()
            }
        }
        binding.filterMedium.setOnClickListener{
            viewModel.getPriorityNotes(2).observe(viewLifecycleOwner){
                currentList = it as ArrayList<Note>
                noteAdapter.noteList = it
                noteAdapter.notifyDataSetChanged()
            }
        }
        binding.filterHigh.setOnClickListener{
            viewModel.getPriorityNotes(3).observe(viewLifecycleOwner){
                currentList = it as ArrayList<Note>
                noteAdapter.noteList = it
                noteAdapter.notifyDataSetChanged()
            }
        }
        binding.filterIV.setOnClickListener{
            viewModel.getAllNotes().observe(viewLifecycleOwner){
                currentList = it as ArrayList<Note>
                noteAdapter.noteList = it
                noteAdapter.notifyDataSetChanged()
            }
        }




    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}