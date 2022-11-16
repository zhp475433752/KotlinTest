package com.zwwl.kotlintest.paging3.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zwwl.kotlintest.paging3.api.GitHubService
import com.zwwl.kotlintest.paging3.data.model.Repo

/**
 * Created by zhanghuipeng on 2022/11/16.
 */
class RepoPagingSource(private val gitHubService: GitHubService) : PagingSource<Int, Repo>()  {
    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val repoResponse = gitHubService.searchRepos(page, pageSize)
            val list = repoResponse.items
            val prevKey = if (page > 1) page -1 else null
            val nextKey = if (list.isNotEmpty()) page+1 else null
            LoadResult.Page(data = list, prevKey = prevKey, nextKey = nextKey)
        } catch(e: Exception){
            LoadResult.Error(e)
        }

    }

}