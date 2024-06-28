package com.example.noteapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.data.models.NoteEntity
import com.example.noteapp.databinding.ItemNoteBinding

class NoteAdapter : ListAdapter<NoteEntity, NoteAdapter.NoteViewHolder>(DiffCallback()) {

    inner class NoteViewHolder(private val binding: ItemNoteBinding) ://Внутренний класс для создания ViewHolder
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NoteEntity) {    //Метод для привязки данных к ViewHolder
            with(binding) {
                itemTvTitle.text = item.title
                itemTvContent.text = item.content
                itemTvDate.text = item.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            NoteAdapter.NoteViewHolder {//Метод для создания ViewHolder
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: NoteAdapter.NoteViewHolder,
        position: Int
    )    // Метод для привязки данных к ViewHolder
    {
        holder.bind(getItem(position))
    }

    class DiffCallback :
        DiffUtil.ItemCallback<NoteEntity>() {//DiffCallback - сравнение двух элементов

        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: NoteEntity,
            newItem: NoteEntity
        ): Boolean {//Проверка на идентичность элементов
            return oldItem.id == newItem.id
        }
    }
}