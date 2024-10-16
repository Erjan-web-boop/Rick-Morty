package com.example.fail.network.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.fail.R
import com.example.fail.network.model.Character
import com.example.fail.databinding.FragmentSecondBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    private var character: Character? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        character = arguments?.getParcelable("character")
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        character?.let { it ->
            binding.characterName.text = it.name
            binding.characterStatus.text = it.status
            binding.characterGender.text = "Gender: ${it.gender}"
            binding.characterLocation.text = "Last known location: ${it.location.name}"
            binding.characterFirstSeen.text = "First seen in: ${it.episode.joinToString()}"

            Glide.with(this)
                .load(it.image)
                .placeholder(R.drawable.place_holder)
                .into(binding.characterImage)
        }
    }

}