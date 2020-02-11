package com.mannguyen.androidtest.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mannguyen.androidtest.R
import com.mannguyen.androidtest.adapters.VolumesAdapter
import com.mannguyen.androidtest.utils.hide
import com.mannguyen.androidtest.utils.show
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    lateinit var adapter: VolumesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        adapter = VolumesAdapter(this).apply {
            onLoadMoreError = {
                toast(it.message.toString())
            }
        }

        rvVolumes.layoutManager = LinearLayoutManager(this)
        rvVolumes.adapter = adapter

        edtQuery.onActionSearch { query ->
            adapter.search(
                query = query,
                onStart = this@MainActivity::showLoading,
                onCompleted = this@MainActivity::hideLoading,
                onError = {
                    hideLoading()
                }
            )
        }

        rvVolumes.onScrolledToBottom {
            adapter.loadMore()
        }
    }

    private fun showLoading() {
        pbLoading.show()
        vCover.show()
    }

    private fun hideLoading() {
        pbLoading.hide()
        vCover.hide()
    }

}
