package com.example.projeto_3_acacio.data.domain

data class Pokemon(
    val description: String,
    val gen: Int,
    val name: String,
    val number: String,
    val sprite: String,
    val types: List<String>,
)