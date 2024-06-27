package com.example.noteapp.ui.fragments.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardBinding
import com.example.noteapp.ui.adapters.OnBoardViewPagerAdapter
import com.example.noteapp.utils.SharedUtil

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        clickInitialize()
        binding.wormDotsIndicator.attachTo(binding.viewPager)
    }

    private fun initialize() {
        binding.viewPager.adapter = OnBoardViewPagerAdapter(this@OnBoardFragment)
    }

    private fun clickInitialize() = with(binding.viewPager) {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                with(binding) {
                    if (position == 2) {
                        skipBtn.visibility = View.INVISIBLE
                        startBtn.visibility = View.VISIBLE

                        startBtn.setOnClickListener {
                            val sharedUtil = SharedUtil()
                            sharedUtil.unit(requireContext())
                            sharedUtil.isBoardDone = true
                            findNavController().navigate(
                                R.id.action_onBoardFragment_to_noteFragment,
                                null,
                                NavOptions.Builder().setPopUpTo(R.id.onBoardFragment, true)
                                    .build() //Удаляем информацию о фрагменте
                            )
                        }
                    } else {
                        skipBtn.visibility = View.VISIBLE
                        startBtn.visibility = View.INVISIBLE

                        skipBtn.setOnClickListener {
                            currentItem += 2       //setCurrentItem(currentItem + 2)
                        }
                    }
                }
            }
        })
    }
}