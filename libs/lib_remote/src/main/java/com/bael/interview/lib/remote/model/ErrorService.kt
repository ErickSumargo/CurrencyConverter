package com.bael.interview.lib.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by ErickSumargo on 04/04/21.
 */

data class ErrorService(
    @SerializedName("code")
    val code: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("info")
    val info: String
)
