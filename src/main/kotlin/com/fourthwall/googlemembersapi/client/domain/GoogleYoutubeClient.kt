package com.fourthwall.googlemembersapi.client.domain

import arrow.core.Either
import arrow.fx.IO
import arrow.fx.extensions.io.applicative.just
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.fourthwall.googlemembersapi.client.support.AccessTokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.openapitools.client.apis.DefaultApi
import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.models.ApiError
import retrofit2.HttpException
import retrofit2.Response

open class GoogleYoutubeClient {

    protected fun <T>mapException(ex: Throwable): IO<Either<Throwable, T>> = when (ex) {
        is HttpException -> {
            ex.response()
                    ?.errorBody()
                    ?.string()
                    ?.let { Either.left(GoogleYoutubeError.fromResponse(
                            objectMapper.readValue<ApiError>(it, ApiError::class.java).error
                    )) }
                    ?.just()
                    ?: IO.raiseError(ex)
        }
        else ->
            IO.raiseError(ex)
    }

    protected fun <T>mapError(response: Response<T>): Either<Throwable, T> =
        response
            .errorBody()!!
            .string()
            .let { Either.left(GoogleYoutubeError.fromResponse(
                    objectMapper.readValue<ApiError>(it, ApiError::class.java).error
            )) }

    companion object {

        val objectMapper: ObjectMapper = ObjectMapper()
                .registerKotlinModule()
                .registerModule(JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)

        fun createGoogleYoutubeClient(
                googleYoutubeApiUrl: String,
                token: String,
                customize: (OkHttpClient.Builder) -> OkHttpClient.Builder = { it }
        ): DefaultApi {
            val okHttp = customize(OkHttpClient
                    .Builder()
                    .addInterceptor(AccessTokenInterceptor(token))
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)))
                    .build()
            return ApiClient(baseUrl = googleYoutubeApiUrl, okHttpClient = okHttp)
                    .createService(DefaultApi::class.java)
        }
    }
}
