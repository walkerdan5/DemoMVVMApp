package com.example.demomvvmapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demomvvmapp.R
import com.example.demomvvmapp.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.demomvvmapp.databinding.FragmentHomeBinding
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
    }

    private fun addObservers() {
        viewModel.progressVisibility.observe(viewLifecycleOwner) { showProgress ->
            binding.loadingProgress.visibility = View.VISIBLE.takeIf { showProgress } ?: View.GONE
        }

        viewModel.setIllumination.observe(viewLifecycleOwner) {
            when (it) {
                0 -> binding.moonPhaseIv.setImageResource(R.drawable.ic_moon_0)
                1 -> binding.moonPhaseIv.setImageResource(R.drawable.ic_moon_0_1)
                2 -> binding.moonPhaseIv.setImageResource(R.drawable.ic_moon_0_2)
                3 -> binding.moonPhaseIv.setImageResource(R.drawable.ic_moon_0_3)
                4 -> binding.moonPhaseIv.setImageResource(R.drawable.ic_moon_0_4)
                5 -> binding.moonPhaseIv.setImageResource(R.drawable.ic_moon_0_5)
                6 -> binding.moonPhaseIv.setImageResource(R.drawable.ic_moon_0_6)
                7 -> binding.moonPhaseIv.setImageResource(R.drawable.ic_moon_0_7)
                8 -> binding.moonPhaseIv.setImageResource(R.drawable.ic_moon_0_8)
                9 -> binding.moonPhaseIv.setImageResource(R.drawable.ic_moon_0_9)
                10 -> binding.moonPhaseIv.setImageResource(R.drawable.ic_moon_10)
            }
        }
    }
}