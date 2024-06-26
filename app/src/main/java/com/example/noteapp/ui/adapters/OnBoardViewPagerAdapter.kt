package com.example.noteapp.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.noteapp.ui.fragments.onboard.OnBoardViewPagerFragment
import com.example.noteapp.ui.fragments.onboard.OnBoardViewPagerFragment.Companion.ARG_ONBOARD_POSITION

class OnBoardViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {          // Количество экранов
        return 3
    }

    override fun createFragment(position: Int) = OnBoardViewPagerFragment().apply {
        arguments = Bundle().apply {
            putInt(ARG_ONBOARD_POSITION, position)              // C помощью Bundle отправояем позиции экранов
        }
    }
}