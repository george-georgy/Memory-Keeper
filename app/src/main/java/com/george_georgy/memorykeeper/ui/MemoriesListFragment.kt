package com.george_georgy.memorykeeper.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.george_georgy.memorykeeper.R
import com.george_georgy.memorykeeper.databinding.FragmentMemoriesListBinding
import com.george_georgy.memorykeeper.model.Memory
import com.george_georgy.memorykeeper.ui.viewModel.MemoryViewModel


class MemoriesListFragment : Fragment(R.layout.fragment_memories_list)  {

    private var _binding: FragmentMemoriesListBinding? = null
    private var memoryAdapter: MemoryListAdapter? = null
    private lateinit var memoryViewModel: MemoryViewModel
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemoriesListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // getting viewModel from activity
        memoryViewModel = (activity as MainActivity).memoryViewModel

        setupRecyclerView()
        observeMemoryList()


        binding.edSearch.addTextChangedListener {
            search(it.toString().trim())
        }

        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment)
        }

        memoryAdapter?.onClick = { memory->
            val bundle = Bundle().apply {
                putParcelable("memory",memory)
            }
            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment,bundle)
        }

    }



    private fun setupRecyclerView() {
        memoryAdapter = MemoryListAdapter()
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = memoryAdapter
            //addItemDecoration(VerticaItemDecoration(40))
        }
    }

    private fun observeMemoryList() {

        memoryViewModel.memories.observe(
            viewLifecycleOwner
        ) { list: List<Memory> ->
            memoryAdapter?.differ?.submitList(list)
            updateUI(list)
        }

    }

    private fun search(query: String) {

        memoryViewModel.searchMemories(query).observe(
            viewLifecycleOwner
        ) { list ->
            memoryAdapter?.differ?.submitList(list)
        }
    }

    private fun updateUI(memory: List<Memory>) {

        if (memory.isNotEmpty()) {
            binding.cardView.visibility = View.GONE
            binding.rvNotes.visibility = View.VISIBLE
        } else {
            binding.cardView.visibility = View.VISIBLE
            binding.rvNotes.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
         memoryAdapter = null

    }


}