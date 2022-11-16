package com.zwwl.kotlintest.paging3.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zwwl.kotlintest.paging3.data.model.Repo
import com.zwwl.kotlintest.paging3.data.repository.Repository
import kotlinx.coroutines.flow.Flow

/**
 * Created by zhanghuipeng on 2022/11/16.
 */
class PagingViewModel: ViewModel() {

    fun getPagingData(): Flow<PagingData<Repo>> {
        return Repository.getPagingData().cachedIn(viewModelScope)
    }

}