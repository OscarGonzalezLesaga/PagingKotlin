package com.oscar.gonzalez.lesaga.paging

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PageKeyedDataSource
import android.arch.paging.PagedList

class ItemViewModel : ViewModel() {

    var itemPagedList: LiveData<PagedList<Item>>? = null
    var liveDataSource: LiveData<PageKeyedDataSource<Int, Item>>? = null

    init {
        val itemDataSourceFactory = ItemDataSourceFactory()
        liveDataSource = itemDataSourceFactory.itemLiveDataSource
        var config =
            PagedList.Config.Builder().setEnablePlaceholders(true).setPageSize(ItemDataSource.page_size).build()

        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }
}