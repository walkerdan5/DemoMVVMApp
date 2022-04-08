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

    }
}