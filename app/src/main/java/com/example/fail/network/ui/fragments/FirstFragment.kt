package com.example.fail.network.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fail.databinding.FragmentFirstBinding
import com.example.fail.network.resource.Resource
import com.example.fail.network.viewmodel.CharacterViewModel
import com.example.fail.network.ui.adapter.AppAdapter
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel


class FirstFragment : Fragment() {

    private lateinit var appAdapter: AppAdapter
    private val viewModel by viewModel<CharacterViewModel>()

    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.rvApp

        appAdapter = AppAdapter { character ->
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(character)
            findNavController().navigate(action)
        }

        recyclerView.adapter = appAdapter

        viewModel.characters.observe(viewLifecycleOwner) { resource ->
            binding.progressBar.isVisible = resource is Resource.Loading
            when (resource) {
                is Resource.Success -> appAdapter.submitList(resource.data)
                is Resource.Error -> { /* Обработка ошибки */ }
                else -> Unit
            }
        }

    }
}
