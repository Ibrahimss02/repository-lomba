package com.drunken.repositorylombaraion.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drunken.repositorylombaraion.models.Lomba
import com.drunken.repositorylombaraion.network.RepositoryLombaApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LombaDetailPageViewModel(private val idLomba: String): ViewModel() {

    private val _lomba = MutableLiveData<Lomba>()
    val lomba: LiveData<Lomba>
        get() = _lomba

    init {
        fetchLombaDetail()
    }

    private fun fetchLombaDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RepositoryLombaApi.retrofitService.getSpecificLomba(idLomba)

            withContext(Dispatchers.Default) {
                response?.let {
                    _lomba.postValue(response)
                }
            }
        }
    }
}