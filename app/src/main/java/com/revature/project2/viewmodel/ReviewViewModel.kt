package com.revature.project2.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revature.project2.model.api.RetrofitHelper
import com.revature.project2.model.api.alltoys.ToyItem
import com.revature.project2.model.api.user.Review
import com.revature.project2.model.api.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReviewViewModel: ViewModel() {

    private val getReviewsRequestLiveData = MutableLiveData<Boolean>()
    var userReviews:List<Review> by  mutableStateOf(listOf())

    fun getReviews(reviews: User) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val reviewsService = RetrofitHelper.getReviewsService()
                val responseService = reviewsService.getReviews(reviews)

                if(responseService.isSuccessful) {
                    responseService.body()?.let { Review ->
                        Log.d("Review", "Response Review $Review")
                        userReviews = responseService.body()!!

                    }
                } else {
                    responseService.errorBody()?.let { error ->
                        Log.d("Review error", "Response Review $error")
                        error.close()
                    }
                }
                getReviewsRequestLiveData.postValue(responseService.isSuccessful)
            } catch (e: Exception) {
                Log.d("Review", "Exception in Networking $e")
            }
        }
    }
}