package com.example.noteapp.ui.fragments.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardViewPagerBinding

class OnBoardViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding) {
        when (requireArguments().getInt(ARG_ONBOARD_POSITION)) {        //Получаем агрументы (позиции экранов) с помощью константы (ключа)
            0 -> {
                boardContentTv.text = "Очень удобный функционал"
                lottieAnim.setAnimation(R.raw.note_anim)
            }

            1 -> {
                boardContentTv.text = "Быстрый, качественный продукт"
                lottieAnim.setAnimation(R.raw.second_anim)
            }

            2 -> {
                boardContentTv.text = "Куча функций и интересных фишек"
                lottieAnim.setAnimation(R.raw.third_anim)
            }
        }
    }

    companion object {
        const val ARG_ONBOARD_POSITION = "onboard"
    }

}