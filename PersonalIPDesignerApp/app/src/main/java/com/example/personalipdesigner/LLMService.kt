package com.example.personalipdesigner

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

/**
 * Simple helper to call an LLM API. Replace YOUR_API_KEY and endpoint.
 */
object LLMService {
    private val client = OkHttpClient()
    private const val ENDPOINT = "https://api.example.com/v1/chat"
    private const val API_KEY = "YOUR_API_KEY"

    fun ask(prompt: String): String {
        val json = JSONObject()
        json.put("prompt", prompt)
        val body = json.toString().toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url(ENDPOINT)
            .addHeader("Authorization", "Bearer $API_KEY")
            .post(body)
            .build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return "Error: ${'$'}{response.code}"
            return response.body?.string() ?: ""
        }
    }
}
