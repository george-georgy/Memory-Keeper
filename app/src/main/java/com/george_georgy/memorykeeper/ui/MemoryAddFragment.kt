package com.george_georgy.memorykeeper.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.george_georgy.memorykeeper.ui.viewModel.MemoryViewModel
import com.george_georgy.memorykeeper.databinding.FragmentMemoryBinding
import com.george_georgy.memorykeeper.model.Memory


class MemoryAddFragment : Fragment() {
    private var _binding: FragmentMemoryBinding? = null
    private val args by navArgs<MemoryAddFragmentArgs>()
    private lateinit var viewmodel: MemoryViewModel
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel = (activity as MainActivity).memoryViewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.memory?.let {
            binding.apply {
                edTitle.setText(it.memoryTitle)
                edMemory.setText(it.memoryText)
            }
            binding.imgDeleteMemory.visibility = View.VISIBLE
        }

        binding.apply {
            btnSaveMemory.setOnClickListener {
                val id = args.memory?.memoryId ?: 0
                val title = edTitle.text.toString()
                val text = edMemory.text.toString()

               Memory(id, title, text).also { memory ->
                    if (title.isEmpty() && text.isEmpty()) {
                        Toast.makeText(context, "All fields must be filled", Toast.LENGTH_LONG)
                            .show()
                        return@setOnClickListener
                    }
                    viewmodel.insertMemory(memory)
                    findNavController().navigateUp()
                }
            }
        }


        binding.apply {
            imgDeleteMemory.setOnClickListener {
                val memoryId = args.memory?.memoryId ?: 0
                val memoryTitle = edTitle.text.toString()
                val memoryText = edMemory.text.toString()

                Memory(memoryId, memoryTitle, memoryText).also {
                    viewmodel.deleteMemory(it)
                    findNavController().navigateUp()
                }
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
