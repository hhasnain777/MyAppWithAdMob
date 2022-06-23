package com.example.myappwithadmob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class RecycleViewActivity : AppCompatActivity()  {

    private lateinit var myRecycleView: RecyclerView
    private lateinit var adapter: mAdapter
    private lateinit var mList: ArrayList<DataClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view)


        myRecycleView = findViewById(R.id.myRecycleView)

        mList = ArrayList<DataClass>()
        adapter = mAdapter(this, mList)
        myRecycleView.setHasFixedSize(true)
        adapter.onItemClick = {
                val intent = Intent(this, FullScreenActivity::class.java)
                intent.putExtra("data", it)
                startActivity(intent)
            }

        myRecycleView.layoutManager = LinearLayoutManager(this)
        myRecycleView.adapter = adapter
            mList.add(DataClass(getString(R.string.Title0), R.drawable.p0, getString(R.string.Biography)))
        mList.add(DataClass(getString(R.string.title_1), R.drawable.p1, getString(R.string.description_1)))
        mList.add(DataClass(getString(R.string.title_2), R.drawable.p2, getString(R.string.description_2)))
        mList.add(DataClass(getString(R.string.title_3), R.drawable.p3, getString(R.string.description_3)))
        mList.add(DataClass(getString(R.string.title_4), R.drawable.p4, getString(R.string.description_4)))
        mList.add(DataClass(getString(R.string.title_5), R.drawable.p5, getString(R.string.description_5)))
        mList.add(DataClass(getString(R.string.title_6), R.drawable.p6, getString(R.string.description_6)))
        mList.add(DataClass(getString(R.string.title_7), R.drawable.p7, getString(R.string.description_7)))
        mList.add(DataClass(getString(R.string.title_8), R.drawable.p7, getString(R.string.description_8)))
        mList.add(DataClass(getString(R.string.title_9), R.drawable.p9, getString(R.string.description_9)))
        mList.add(DataClass(getString(R.string.title_10), R.drawable.p10, getString(R.string.description_10)))
        mList.add(DataClass(getString(R.string.title_11), R.drawable.p11, getString(R.string.description_11)))

    }

}