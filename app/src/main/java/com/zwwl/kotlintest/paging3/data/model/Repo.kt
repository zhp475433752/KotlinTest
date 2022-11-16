package com.zwwl.kotlintest.paging3.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by zhanghuipeng on 2022/11/16.
 */
data class Repo(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("stargazers_count") val starCount: Int
)
