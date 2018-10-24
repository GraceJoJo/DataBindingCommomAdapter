package com.example.jojo.databinding

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.act_list.*

class ACT_ListView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_list)
        var mAdapter = ADA_ListItem(this)
        listview.adapter = mAdapter


        var gson = Gson()
        val jsonParser = JsonParser()
        val jsonElements = jsonParser.parse(DataConstants.jsonData).getAsJsonArray()//获取JsonArray对象
        val dataList= ArrayList<DataBean>()
//        for (bean in jsonElements) {
//            val bean1 = gson.fromJson(bean, DataBean::class.java)//解析
//            dataList.add(bean1)
//        }
        jsonElements.mapTo(dataList) {
            gson.fromJson(it, DataBean::class.java)//解析
        }

        mAdapter.update(dataList,true)
    }
}