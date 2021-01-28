package com.example.testproject.core.data.network.responses

data class TopResponse(
    val channels: Int,
    val game: GameResponse,
    val viewers: Int
)