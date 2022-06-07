package com.example.pokemonapp

import kotlin.random.Random

data class Usuario(var nombre: String, var pass: String) {
    val token = nombre + pass
    var pokemonFavoritoId : Int? = null

    var pokemonCapturados = mutableListOf<Int>()
}
