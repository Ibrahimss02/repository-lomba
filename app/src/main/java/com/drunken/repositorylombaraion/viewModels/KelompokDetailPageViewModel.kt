package com.drunken.repositorylombaraion.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drunken.repositorylombaraion.models.Kelompok
import com.drunken.repositorylombaraion.network.RepositoryLombaApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KelompokDetailPageViewModel(private val idKelompok: String): ViewModel() {

    private val _kelompok = MutableLiveData<Kelompok>()
    val kelompok: LiveData<Kelompok>
        get() = _kelompok

    init {
        fetchKelompokDetail()
    }

    private fun fetchKelompokDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RepositoryLombaApi.retrofitService.getKelompok(idKelompok)

            withContext(Dispatchers.Default) {
                response?.let {
                    _kelompok.postValue(response)
                }
            }

        }
    }
}