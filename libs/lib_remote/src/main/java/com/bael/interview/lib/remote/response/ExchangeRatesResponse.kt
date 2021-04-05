package com.bael.interview.lib.remote.response

import com.bael.interview.lib.remote.model.ErrorService
import com.google.gson.annotations.SerializedName

/**
 * Created by ErickSumargo on 01/04/21.
 */

data class ExchangeRatesResponse(
    @SerializedName("privacy")
    val privacy: String,
    @SerializedName("quotes")
    val quotes: Map<String, Double>,
    @SerializedName("source")
    val source: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("terms")
    val terms: String,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("error")
    val error: ErrorService?
)
