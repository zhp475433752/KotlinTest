package com.zwwl.kotlintest.paging3.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by zhanghuipeng on 2022/11/16.
 */
class RepoResponse {
    @SerializedName("items") val items: List<Repo> = emptyList()
}