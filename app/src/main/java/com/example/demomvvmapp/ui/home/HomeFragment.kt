package com.example.demomvvmapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demomvvmapp.R
import com.example.demomvvmapp.base.BaseFragment
import com.example.demomvvmapp.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension


@KoinApiExtension
class HomeFragment : BaseFragment<HomeEvent, HomeViewModel>() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var _binding: FragmentHomeBinding

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        init()

        return binding.root
    }

    private fun init() {
        addObservers()
        viewModel.getTheMoonPhase()
    }

    private fun addObservers() {
        viewModel.progressVisibility.observe(viewLifecycleOwner) { showProgress ->
            binding.loadingProgress.visibility = View.VISIBLE.takeIf { showProgress } ?: View.GONE
        }

        viewModel.setIllumination.observe(viewLifecycleOwner) {
            binding.moonImgUrl = getMoonDrawable(it)
        }
    }


    private fun getMoonDrawable(illuminationValue: Int): Int {
        return when (illuminationValue) {
            0 -> (R.drawable.ic_moon_0)
            1 -> (R.drawable.ic_moon_0_1)
            2 -> (R.drawable.ic_moon_0_2)
            3 -> (R.drawable.ic_moon_0_3)
            4 -> (R.drawable.ic_moon_0_4)
            5 -> (R.drawable.ic_moon_0_5)
            6 -> (R.drawable.ic_moon_0_6)
            7 -> (R.drawable.ic_moon_0_7)
            8 -> (R.drawable.ic_moon_0_8)
            9 -> (R.drawable.ic_moon_0_9)
            10 -> (R.drawable.ic_moon_10)
            else -> (R.drawable.ic_moon_0)
        }
    }
}