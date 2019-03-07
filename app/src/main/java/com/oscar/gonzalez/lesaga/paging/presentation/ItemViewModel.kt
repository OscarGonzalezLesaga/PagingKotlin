package com.oscar.gonzalez.lesaga.paging.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.oscar.gonzalez.lesaga.paging.data.Item

class ItemViewModel : ViewModel() {

    var itemPagedList: LiveData<PagedList<Item>>? = null

    init {
        val itemDataSourceFactory = ItemDataSourceFactory()
        val config =
            PagedList.Config.Builder().setEnablePlaceholders(true).setPageSize(ItemDataSource.page_size).build()

        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }
}