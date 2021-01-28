package com.example.testproject.core.data.network.responses

data class GameResponse(
    val _id: Int,
    val box: BoxResponse,
    val giantbomb_id: Int,
    val locale: String,
    val localized_name: String,
    val logo: LogoResponse,
    val name: String
)