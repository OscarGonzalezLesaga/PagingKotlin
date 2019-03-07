package com.oscar.gonzalez.lesaga.paging

import android.arch.paging.PageKeyedDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemDataSource : PageKeyedDataSource<Int, Item>() {

    companion object {
        const val page_size = 50
        const val first_page = 1
        const val site_name = "stackoverflow"
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Item>) {
        RetrofitClient.getInstance().getApi().getAnswers(first_page, page_size, site_name).enqueue(object :
            Callback<StackApiResponse> {
            override fun onFailure(call: Call<StackApiResponse>, t: Throwable) {
                //TODO
            }

            override fun onResponse(call: Call<StackApiResponse>, response: Response<StackApiResponse>) {
                response.body()?.run {
                    callback.onResult(items, null, first_page + 1)
                }
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        RetrofitClient.getInstance().getApi().getAnswers(params.key, page_size, site_name)
            .enqueue(object : Callback<StackApiResponse> {
                override fun onFailure(call: Call<StackApiResponse>, t: Throwable) {
                    //Implement failure
                }

                override fun onResponse(call: Call<StackApiResponse>, response: Response<StackApiResponse>) {
                    response.body()?.let {
                        var key = if (it.has_more) params.key + 1 else null
                        response.body()?.run {
                            callback.onResult(items, key)
                        }
                    }
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        RetrofitClient.getInstance().getApi().getAnswers(params.key, page_size, site_name)
            .enqueue(object : Callback<StackApiResponse> {
                override fun onFailure(call: Call<StackApiResponse>, t: Throwable) {
                }

                override fun onResponse(call: Call<StackApiResponse>, response: Response<StackApiResponse>) {
                    var key = if (params.key > 1) params.key - 1 else null
                    response.body()?.run {
                        callback.onResult(items, key)
                    }
                }
            })
    }
}