package com.mmag.ud4_videoretrofitconcoroutines.ui.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmag.ud4_videoretrofitconcoroutines.data.network.APIManager
import com.mmag.ud4_videoretrofitconcoroutines.data.network.model.AllCharactersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


data class MainUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val response: AllCharactersResponse? = null
)

class MainViewModel : ViewModel() {

    private var _charactersResponse: MutableLiveData<MainUIState> = MutableLiveData(MainUIState())
    val charactersResponse: LiveData<MainUIState> get() = _charactersResponse

    fun getAllCharacterFromAPI() {
        //indicamos que carga la info
        _charactersResponse.postValue(
            MainUIState(isLoading = true)
        )

        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            val response = APIManager.service.getAllCharacters()
            if (response.isSuccessful) {
                _charactersResponse.postValue(
                    MainUIState(isLoading = false, response = response.body())
                )
            } else {
                _charactersResponse.postValue(
                    MainUIState(isLoading = false, isError = true)
                )
            }
        }
    }


}