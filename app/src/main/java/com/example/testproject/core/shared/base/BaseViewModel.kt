package com.example.testproject.core.shared.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.testproject.core.data.network.ApiFactory
import com.example.testproject.core.data.repository.NetworkRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel(application: Application): AndroidViewModel(application){
    private val job = Job()
    protected val uiScope = CoroutineScope(Dispatchers.Main + job)

    protected val repository = NetworkRepository(ApiFactory(application).apiInterface)
    protected val authRepository = NetworkRepository(ApiFactory(application).loginApiInterface)
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}