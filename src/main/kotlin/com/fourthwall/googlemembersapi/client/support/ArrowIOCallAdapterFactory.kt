package com.fourthwall.googlemembersapi.client.support

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import arrow.fx.IO
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ArrowIOCallAdapterFactory : CallAdapter.Factory() {
    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (getRawType(returnType) != IO::class.java) return null
        if (returnType !is ParameterizedType) throw IllegalStateException("IO return type must be parameterized as IO<A>")

        val innerType = getParameterUpperBound(0, returnType)
        if (getRawType(innerType) != Response::class.java) return BodyCallAdapter<Any>(innerType)
        if (innerType !is ParameterizedType) throw IllegalStateException("Response type must be parameterized as Response<A>")

        val responseType = getParameterUpperBound(0, innerType)
        return ResponseCallAdapter<Any>(responseType)
    }
}

class ResponseCallAdapter<A>(private val responseType: Type): CallAdapter<A, IO<Response<A>>> {
    override fun responseType(): Type = responseType

    override fun adapt(call: Call<A>): IO<Response<A>> = call.asIO { it.right() }
}

class BodyCallAdapter<A>(private val responseType: Type): CallAdapter<A, IO<A?>> {
    override fun responseType(): Type = responseType

    override fun adapt(call: Call<A>): IO<A?> = call.asIO {
        if (it.isSuccessful) it.body().right()
        else HttpException(it).left()
    }
}

internal fun <A, B> Call<A>.asIO(map: (Response<A>) -> Either<Throwable, B>): IO<B> {
    return IO.async { cb ->
        enqueue(object : Callback<A> {
            override fun onResponse(call: Call<A>, response: Response<A>) = cb(map(response))
            override fun onFailure(call: Call<A>, t: Throwable) = cb(t.left())
        })
    }
}
