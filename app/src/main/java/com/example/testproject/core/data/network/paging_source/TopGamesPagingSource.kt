package com.example.testproject.core.data.network.paging_source

import androidx.paging.PagingSource
import com.example.testproject.core.data.network.ApiInterface
import com.example.testproject.core.data.network.responses.TopResponse
import java.lang.Exception

private const val UNSPLASH_STARTING_INDEX = 0
class TopGamesStatPagingSource(
    private val api: ApiInterface
): PagingSource<Int, TopResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopResponse> {
        val position = params.key ?: UNSPLASH_STARTING_INDEX
        var offset = 0
        if(position!=0)offset = position*params.pageSize
        return  try {
            val response = api.getTop(limit= params.pageSize,offset = offset)
            val topResponse = response.body()?.top
            LoadResult.Page(
                data = topResponse!! as ArrayList<TopResponse>,
                prevKey = if (position == UNSPLASH_STARTING_INDEX) null else position - 1,
                nextKey = if (topResponse!!.isEmpty()) null else position + 1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }

    }
}