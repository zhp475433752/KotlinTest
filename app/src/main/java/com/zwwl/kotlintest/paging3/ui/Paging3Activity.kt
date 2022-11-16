package com.zwwl.kotlintest.paging3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.zwwl.kotlintest.R
import com.zwwl.kotlintest.paging3.vm.PagingViewModel
import kotlinx.android.synthetic.main.activity_paging3.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Paging3Activity : AppCompatActivity() {

    // 延迟获取 viewModel
    private val viewModel by lazy { ViewModelProvider(this)[PagingViewModel::class.java] }
    private val repoAdapter = RepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging3)

        paging_recycler_view.layoutManager = LinearLayoutManager(this)
        paging_recycler_view.adapter = repoAdapter

        lifecycleScope.launch {
            viewModel.getPagingData().collect {
                repoAdapter.submitData(it)
            }
        }

        repoAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    paging_recycler_view.visibility = View.VISIBLE
                    paging_progress_bar.visibility = View.INVISIBLE
                }
                is LoadState.Loading -> {
                    paging_recycler_view.visibility = View.INVISIBLE
                    paging_progress_bar.visibility = View.VISIBLE
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    paging_progress_bar.visibility = View.INVISIBLE
                    Toast.makeText(this, "Load Error: ${state.error.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}