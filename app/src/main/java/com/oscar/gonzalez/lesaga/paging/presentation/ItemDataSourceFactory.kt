package com.oscar.gonzalez.lesaga.paging.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource
import com.oscar.gonzalez.lesaga.paging.data.Item

class ItemDataSourceFactory : DataSource.Factory<Int, Item>() {

    var itemLiveDataSource: MutableLiveData<PageKeyedDataSource<Int, Item>>? = null

    override fun create(): DataSource<Int, Item> {
        var itemDataSource = ItemDataSource()
        itemLiveDataSource?.postValue(itemDataSource)
        return itemDataSource
    }
}