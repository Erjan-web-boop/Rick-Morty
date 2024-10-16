package com.example.fail.network.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fail.network.model.Character
import com.example.fail.network.repository.SecondRepository
import javax.inject.Inject

class SecFragmentViewModel @Inject constructor(private val repository2: SecondRepository) : ViewModel() {

    fun getCharacterById(id: Int): MutableLiveData<Character?> {
        return repository2.getCharacterById(id)
    }
}