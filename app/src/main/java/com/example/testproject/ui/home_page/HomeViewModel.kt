package com.example.testproject.ui.home_page

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.testproject.core.data.network.responses.TopResponse
import com.example.testproject.core.shared.base.BaseViewModel
import java.util.concurrent.TimeUnit

class HomeViewModel(application:Application) : BaseViewModel(application) {
    fun getTop(): LiveData<PagingData<TopResponse>> {
        return authRepository.getTop()
    }


    var isTimeFinished = MutableLiveData<Boolean>()
    var isTimerWorking = false


    val timer = object : CountDownTimer(70000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            isTimeFinished.value  = false
        }
        override fun onFinish() {
            isTimeFinished.value = true
            isTimerWorking = false
        }
    }
}