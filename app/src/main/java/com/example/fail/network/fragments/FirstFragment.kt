package com.example.fail.network.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fail.network.model.Character
import com.example.fail.databinding.FragmentFirstBinding
import com.example.fail.network.ViewModel.FragmentViewModel
import com.example.fail.network.adapter.AppAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FirstFragment : Fragment() {

    private lateinit var appAdapter: AppAdapter
    private val viewModel by lazy {
        ViewModelProvider(this)[FragmentViewModel::class.java]
    }

    private lateinit var binding: FragmentFirstBinding

    private var characterList: List<Character> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

        val recyclerView = binding.rvApp


        appAdapter = AppAdapter(emptyList()) { character ->
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(character)
            findNavController().navigate(action)
        }

        recyclerView.adapter = appAdapter

        viewModel.characters.observe(viewLifecycleOwner, { characters ->
            appAdapter.updateData(characters)
        })
    }

}