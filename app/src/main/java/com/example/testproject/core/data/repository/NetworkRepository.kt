package com.example.testproject.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.testproject.core.data.network.ApiInterface
import com.example.testproject.core.data.network.paging_source.TopGamesStatPagingSource

class NetworkRepository(private val api: ApiInterface) {

    fun getTop() =
        Pager(config = PagingConfig(pageSize = 20, maxSize = 200, enablePlaceholders = true),
            pagingSourceFactory = {
                TopGamesStatPagingSource(
                    api = api
                )
            }).liveData
}