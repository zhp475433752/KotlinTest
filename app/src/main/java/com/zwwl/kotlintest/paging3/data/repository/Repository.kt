package com.zwwl.kotlintest.paging3.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zwwl.kotlintest.paging3.api.GitHubService
import com.zwwl.kotlintest.paging3.data.datasource.RepoPagingSource
import com.zwwl.kotlintest.paging3.data.model.Repo
import kotlinx.coroutines.flow.Flow

/**
 * Created by zhanghuipeng on 2022/11/16.
 */
object Repository {
    private const val PAGE_SIZE = 50
    private val gitHubService = GitHubService.create()

    fun getPagingData(): Flow<PagingData<Repo>> {
        return Pager(config = PagingConfig(PAGE_SIZE), pagingSourceFactory = {
            RepoPagingSource(gitHubService)
        }).flow
    }

}