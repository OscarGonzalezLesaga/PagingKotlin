package com.oscar.gonzalez.lesaga.paging

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val itemViewModel: ItemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        val adapter = ItemAdapter(this)

        itemViewModel.itemPagedList?.observe(this,
            Observer<PagedList<Item>> {
                adapter.submitList(it)
            })

        recyclerView.adapter = adapter
    }
}
