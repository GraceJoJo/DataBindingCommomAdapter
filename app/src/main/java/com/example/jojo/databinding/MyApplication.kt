package com.example.jojo.databinding

import android.app.Application
import android.content.Context

/**
 * Created by JoJo on 2018/9/18.
 * wechat:18510829974
 * description:
 */
class MyApplication : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}