package com.example.jojo.databinding

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.act_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
        btn_one.setOnClickListener({
            var intent = Intent(this,ACT_RecyclerView::class.java)
            startActivity(intent)
        })
        btn_two.setOnClickListener({
            var intent = Intent(this,ACT_ListView::class.java)
            startActivity(intent)
        })
    }

}