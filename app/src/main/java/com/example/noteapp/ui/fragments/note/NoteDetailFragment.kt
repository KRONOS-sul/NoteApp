package com.example.noteapp.ui.fragments.note

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.noteapp.App
import com.example.noteapp.R
import com.example.noteapp.data.models.NoteEntity
import com.example.noteapp.databinding.FragmentNoteDetailBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding
    private var backgroundColor: Int = R.color.background // Default background color
    private var textColor: Int = R.color.white // Default background color


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvDate.text = setDateTime()
        setupTextWatchers()
        clickListener()
    }

    private fun setDateTime(): SpannableString {// Ультра жирный код для установки реального времени и разделения цветов в тексте
        val currentTime = LocalDateTime.now()
        val day = currentTime.dayOfMonth
        val month = currentTime.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
        val hour = DateTimeFormatter.ofPattern("HH:mm")
        val time = currentTime.format(hour)
        val datePart = "$day$month"
        val formattedDateTime = "$datePart $time"

        val spannable = SpannableString(formattedDateTime)
        val dateColor = Color.parseColor("#FFFFFFFF")
        val hourColor = Color.parseColor("#505050")
        spannable.setSpan(
            ForegroundColorSpan(dateColor),
            0,
            datePart.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            ForegroundColorSpan(hourColor),
            datePart.length,
            formattedDateTime.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannable
    }

    private fun setupTextWatchers() = with(binding) {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkFieldsForEmptyValues()
            }

            override fun afterTextChanged(s: Editable?) {}
        }
        etTitle.addTextChangedListener(textWatcher)
        etContent.addTextChangedListener(textWatcher)
    }

    private fun checkFieldsForEmptyValues() = with(binding) {
        val title = etTitle.text.toString()
        val content = etContent.text.toString()
        btnDone.visibility = if (title.isNotEmpty() && content.isNotEmpty()) View.VISIBLE else View.GONE
    }

    private fun clickListener() = with(binding) {
        val title = etTitle.text.toString()
        val content = etContent.text.toString()
        btnDone.setOnClickListener {
            App().getInstance()?.noteDao()?.insert(
                NoteEntity(
                    title, content, setDateTime().toString(),
                    backgroundColor, textColor
                )
            )
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }
        btnBack.setOnClickListener {
            findNavController().navigate(
                R.id.noteFragment,
                null,
                NavOptions.Builder().setPopUpTo(R.id.noteFragment, true).build()
            )
        }
    }

}