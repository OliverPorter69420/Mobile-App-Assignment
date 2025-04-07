package com.example.dissertation_app.data.api
import okhttp3.Interceptor
import okhttp3.Response

class RateLimitInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)
        var retryCount = 0
        val maxRetries = 3
        var delay = 1000L

        while (response.code == 429 && retryCount < maxRetries) {
            retryCount++
            val retryAfterHeader = response.headers["Retry-After"]
            delay = if (retryAfterHeader != null) {
                try {
                    retryAfterHeader.toLong() * 1000
                } catch (e: NumberFormatException) {
                    delay * 2
                }
            } else {
                delay * 2
            }

            println("Received 429. Retrying in $delay ms (attempt $retryCount/$maxRetries)")
            Thread.sleep(delay)
            response.close()

            response = chain.proceed(request)
        }

        return response
    }
}