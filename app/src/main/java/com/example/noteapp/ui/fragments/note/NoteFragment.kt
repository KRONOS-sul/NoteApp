package com.example.noteapp.ui.fragments.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.App
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNoteBinding
import com.example.noteapp.ui.adapters.NoteAdapter

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter = NoteAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
        initialize()
        getData()
    }

    private fun clickListeners() = with(binding) {
        fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }
    }

    private fun initialize() {
        binding.notesRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter //or "private val noteAdapter = NoteAdapter()"
        }
    }

    private fun getData() {
        App().getInstance()?.noteDao()?.getAll()?.observe(viewLifecycleOwner) {
            noteAdapter.submitList(it)
        }
    }
}