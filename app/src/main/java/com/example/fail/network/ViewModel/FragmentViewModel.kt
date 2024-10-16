package com.example.fail.network.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fail.network.model.Character
import com.example.fail.network.repository.FirstRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FragmentViewModel @Inject constructor (
    private val reprository: FirstRepository
) : ViewModel() {

    val characters: LiveData<List<Character>> = reprository.fetchCharacters()
}