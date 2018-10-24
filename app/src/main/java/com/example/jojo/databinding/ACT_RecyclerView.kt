package com.example.jojo.databinding

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonParser



class ACT_RecyclerView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_recyclerview)
        var recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        var mAdapter = ADA_RecyclerItem(this)
        recyclerview.adapter = mAdapter


        var gson = Gson()
        val jsonParser = JsonParser()
        val jsonElements = jsonParser.parse(DataConstants.jsonData).getAsJsonArray()//获取JsonArray对象
        val dataList= ArrayList<DataBean>()

        jsonElements.mapTo(dataList) {
            gson.fromJson(it, DataBean::class.java)//解析
        }
        mAdapter.update(dataList,true)
    }
}
