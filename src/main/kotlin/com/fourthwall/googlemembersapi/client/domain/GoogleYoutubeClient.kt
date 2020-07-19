package com.fourthwall.googlemembersapi.client.domain

import arrow.core.Either
import arrow.fx.IO
import arrow.fx.extensions.io.applicative.just
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.fourthwall.googlemembersapi.client.support.ArrowIOCallAdapterFactory
import okhttp3.OkHttpClient
import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.models.Error
import retrofit2.HttpException
import retrofit2.Retrofit

open class GoogleYoutubeClient {

    protected fun <T>mapException(ex: Throwable): IO<Either<Throwable, T>> = when (ex) {
        //is Error -> ex.left().just()
        is HttpException -> {
            ex.response()
                    ?.errorBody()
                    ?.string()
                    ?.let { Either.left(GoogleYoutubeError.fromResponse(
                            objectMapper.readValue<Error>(it, Error::class.java)
                    )) }
                    ?.just()
                    ?: IO.raiseError(ex)
        }
        else ->
            IO.raiseError(ex)
    }

    companion object {

        val objectMapper: ObjectMapper = ObjectMapper().registerKotlinModule()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)

        fun createGoogleYoutubeRetrofitClient(
                googleYoutubeApiUrl: String,
                //token: String,
                customize: (OkHttpClient.Builder) -> OkHttpClient.Builder = { it }
        ): Retrofit {
            //val credentials: String = Credentials.basic(username, password)
            val okHttp = customize(OkHttpClient
                    .Builder()
                    //.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    /*.addInterceptor { chain ->
                        val request: Request = chain.request()
                        val authenticatedRequest: Request = request.newBuilder().header("Authorization", credentials).build()
                        chain.proceed(authenticatedRequest)
                    }*/)
                    .build()
            return ApiClient(baseUrl = googleYoutubeApiUrl, okHttpClient = okHttp)
                    .retrofitBuilder
                    .addCallAdapterFactory(ArrowIOCallAdapterFactory())
                    .build()
        }
    }
}
