package com.oscar.gonzalez.lesaga.paging.presentation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.oscar.gonzalez.lesaga.paging.R.layout
import com.oscar.gonzalez.lesaga.paging.data.Item
import com.oscar.gonzalez.lesaga.paging.data.Owner
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_main.recyclerView
import java.util.Arrays

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        getExtras()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val itemViewModel: ItemViewModel = ViewModelProviders.of(this).get(
            ItemViewModel::class.java
        )
        val adapter = ItemAdapter(this)
        adapter.itemListener = ::itemClickListener
        itemViewModel.itemPagedList?.observe(this,
            Observer<PagedList<Item>> {
                adapter.submitList(it)
            })

        recyclerView.adapter = adapter
    }

    private fun getExtras() {
        intent?.extras?.run {
            val origin = getString("origin")
            origin.let {
                Toast.makeText(applicationContext, "Venimos de $origin", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun itemClickListener(user: Owner) {
        Toast.makeText(this, "Cotilleando a ${user.display_name}...", Toast.LENGTH_SHORT).show()
        if (VERSION.SDK_INT >= VERSION_CODES.N_MR1) {
            Picasso.get().load(user.profile_image).into(object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: LoadedFrom?) {
                    var icon = Icon.createWithBitmap(bitmap)
                    createShortCutUser(user.display_name, icon)
                }
            })
        }
    }

    @RequiresApi(VERSION_CODES.N_MR1)
    private fun createShortCutUser(user: String, icon: Icon) {

        val shortcutManager: ShortcutManager =
            getSystemService<ShortcutManager>(ShortcutManager::class.java)
        var intent = Intent(Intent.ACTION_VIEW, null, this, MainActivity::class.java)
        intent.putExtra("origen", user)
        val shortcut = ShortcutInfo.Builder(this, "detailUser")
            .setShortLabel(user)
            .setIntent(intent)
            .setIcon(icon)

        shortcutManager.dynamicShortcuts = Arrays.asList(shortcut.build())
    }
}

