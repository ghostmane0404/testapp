package com.example.testproject.core.data.network.responses

data class TopGamesResponse(
    val _total: Int,
    val top: List<TopResponse>
)