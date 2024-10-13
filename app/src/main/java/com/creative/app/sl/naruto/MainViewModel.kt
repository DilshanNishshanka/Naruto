package com.creative.app.sl.naruto

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel(){

    private val _charactersState = mutableStateOf(NarutoState())
    val charactersState: State<NarutoState> = _charactersState

    init {
        fetchCharacters()
    }

    private fun fetchCharacters(){
        viewModelScope.launch {
            try {
                val response = narutoService.getCharacters()
                _charactersState.value = _charactersState.value.copy(
                    list = response.characters,
                    loading = false,
                    error = null
                )
            }catch (e : Exception){
                _charactersState.value = _charactersState.value.copy(
                    loading = false,
                    error = "Error fetching Characters ${e.message}"
                )
            }
        }
    }

    data class NarutoState(
        val loading: Boolean = true,
        val list: List<Naruto> = emptyList(),
        val error: String? = null
    )
}

