package cn.qingyuyu.mirror.view

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ArrayAdapter
import cn.qingyuyu.mirror.R
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast


class MainActivity : Activity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        //全屏
        window.setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT//强制竖屏

        initView()
    }
     fun initView()
    {
        val titleList = ArrayList<String>()
        titleList.add("新闻消息超级不确定1")
        titleList.add("新闻消息超级不确定2")
        titleList.add("新闻消息超级不确定3")
        titleList.add("新闻消息超级不确定4")
        titleList.add("新闻消息超级不确定5")
        titleList.add("新闻消息超级不确定6")
        titleList.add("新闻消息超级不确定7")
        titleList.add("新闻消息超级不确定8")
        newsText.setTextList(titleList)
        newsText.setText(16.0f, 5, Color.WHITE)//设置属性
        newsText.setTextStillTime(3000)//设置停留时长间隔
        newsText.setAnimTime(500)//设置进入和退出的时间间隔
        newsText.setOnItemClickListener { position -> Toast.makeText(this@MainActivity, titleList[position],Toast.LENGTH_SHORT).show() }


        val adapter= ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titleList)
        shadowList.adapter=adapter

        val service =Intent(this@MainActivity,MirrorService::class.java)
        startService(service)

        button.setOnClickListener {
            stopService(service)
        }
    }

    override fun onResume() {
        super.onResume()
        newsText.startAutoScroll()
    }

    override fun onPause() {
        super.onPause()
        newsText.stopAutoScroll()
    }

}
