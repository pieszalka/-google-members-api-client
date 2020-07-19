package com.fourthwall.googlemembersapi.client.domain

import arrow.core.Either
import arrow.fx.IO
import arrow.fx.extensions.io.applicative.just
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.fourthwall.googlemembersapi.client.support.AccessTokenInterceptor
import com.fourthwall.googlemembersapi.client.support.ArrowIOCallAdapterFactory
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.openapitools.client.apis.DefaultApi
import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.models.ServiceUnavailable
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit

open class GoogleYoutubeClient {

    protected fun <T>mapException(ex: Throwable): IO<Either<Throwable, T>> = when (ex) {
        //is Error -> ex.left().just()
        is HttpException -> {
            ex.response()
                    ?.errorBody()
                    ?.string()
                    ?.let { Either.left(GoogleYoutubeError.fromResponse(
                            objectMapper.readValue<ServiceUnavailable>(it, ServiceUnavailable::class.java).error
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
                    objectMapper.readValue<ServiceUnavailable>(it, ServiceUnavailable::class.java).error
            )) }

    companion object {

        val objectMapper: ObjectMapper = ObjectMapper().registerKotlinModule()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)

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
