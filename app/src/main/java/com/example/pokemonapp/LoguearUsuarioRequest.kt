package com.example.pokemonapp

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.*
import kotlin.random.Random

class LoguearUsuarioRequest {

    companion object {

        private var gson = Gson()

        suspend fun get(): String = withContext(Dispatchers.IO) {
            var token = ""
            val nombre = Random.nextInt(0, Int.MAX_VALUE).toString()
            val pass = Random.nextInt(0, Int.MAX_VALUE).toString()
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("http://10.0.2.2:8084/crearUsuario/$nombre/$pass")
                .build()
            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                response.body?.string().let { responseBody ->
                    val user = gson.fromJson(responseBody, Usuario::class.java)
                    token = user.token
                }

            } else
                println("Algo ha ido mal")

            return@withContext token
        }
    }

}