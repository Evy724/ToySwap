package com.revature.project2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revature.project2.model.api.RetrofitHelper
import com.revature.project2.model.api.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel: ViewModel() {

    private val getHistoryRequestLiveData = MutableLiveData<Boolean>()

    fun getHistory(history: User) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val historyService = RetrofitHelper.getHistoryService()
                val responseService = historyService.getHistory(history)

                if(responseService.isSuccessful) {
                    responseService.body()?.let { ToyItem ->
                        Log.d("History", "History ToyItem $ToyItem")
                    }
                } else {
                    responseService.errorBody()?.let { error ->
                        Log.d("History error", "Response ToyItem $error")
                        error.close()
                    }
                }
                getHistoryRequestLiveData.postValue(responseService.isSuccessful)
            } catch (e: Exception) {
                Log.d("History", "Exception in Networking $e")
            }
        }
    }
}