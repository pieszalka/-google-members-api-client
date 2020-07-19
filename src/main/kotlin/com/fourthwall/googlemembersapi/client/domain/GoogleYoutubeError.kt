package com.fourthwall.googlemembersapi.client.domain

import org.openapitools.client.models.Error

data class GoogleYoutubeError(val code: Int, override val message: String, val reasons: String) : Throwable(message) {
    companion object {
        fun fromResponse(error: Error): GoogleYoutubeError {
            val reasons = error.errors?.map { err -> err.reason }?.joinToString(";") ?: "";
            return GoogleYoutubeError(error.code, error.message, reasons)
        }
    }
}