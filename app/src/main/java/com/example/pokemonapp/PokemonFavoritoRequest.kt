package com.example.pokemonapp

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class PokemonFavoritoRequest {

    companion object {

        private var gson = Gson()

        suspend fun get(pokemonId: Long, token: String): Boolean = withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("http://10.0.2.2:8084/pokemonFavorito/${token}/${pokemonId}")
                .build()
            val response = client.newCall(request).execute()

            return@withContext response.isSuccessful && response.body?.string() != "Token no encontrado"
        }
    }

}