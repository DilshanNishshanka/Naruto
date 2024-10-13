package com.creative.app.sl.naruto

data class Naruto (
    val id: Int,
    val name: String,
    val images : List<String>
)

data class NarutoResponse(val characters: List<Naruto>)