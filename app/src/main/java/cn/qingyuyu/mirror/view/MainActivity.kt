package cn.qingyuyu.mirror.view


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import cn.qingyuyu.mirror.R
import cn.qingyuyu.mirror.model.WeatherModel

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import cn.qingyuyu.mirror.presenter.MainPresenter
import cn.qingyuyu.mirror.view.inter.MainActivityInterface

import com.google.android.things.device.TimeManager

import com.google.android.things.pio.PeripheralManager
import com.google.android.things.pio.UartDevice

import java.io.IOException


class MainActivity : AppCompatActivity(),MainActivityInterface  {
    private lateinit var mp:MainPresenter
    private lateinit var adapter: ArrayAdapter<String>
    private var uart: UartDevice? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mp=MainPresenter(this,this)

        adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,mp.schedule)

        scheduleList.adapter=adapter


    }

    override fun onResume() {

            try {
                val manager = PeripheralManager.getInstance()//获取实例
                uart = manager.openUartDevice("UART0")//打开UART
                uart!!.setBaudrate(9600)//设置波特率为9600
                uart!!.setDataSize(8)
                uart!!.setParity(UartDevice.PARITY_NONE)
                uart!!.setStopBits(1)

            } catch (e: Exception) {
                setGreeting("通信失败")
                Log.e("uart", e.toString())
            }
            mp.start()
        super.onResume()
    }

    override fun sendCMD(s: String) {
        runOnUiThread {
            if (s != "null" && uart != null)
                try {
                    uart!!.write(s.toByteArray(), s.length)
                    Log.e("write",s)
                } catch (e: Exception) {
                    Log.e("write", e.toString())
                }
        }

    }

    override fun updateSchedule() {
                runOnUiThread {
                    adapter.notifyDataSetChanged()
                }
    }

    override fun onDestroy() {

        if (uart != null)
            try {
                uart!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        mp.stop()

        super.onDestroy()
    }
    override fun setNews(news: java.util.ArrayList<String>?) {
        runOnUiThread {
            if(news!=null) {
                val a= ArrayAdapter(this,android.R.layout.simple_list_item_1,news)
                newsList.adapter=a
            }
        }
    }

    override fun setTime(time: Date?) {
        runOnUiThread {
            if(time!=null)
                try {

                    val timeManager = TimeManager.getInstance()
                    // Use 24-hour time
                    timeManager.setTimeFormat(TimeManager.FORMAT_24)
                    timeManager.setTime(time.time)
                }
                catch (e:Exception)
                {
                    Log.e("time m",e.toString()+"$time")
                }
        }

    }


    override fun setGreeting(text: String?) {
        runOnUiThread {
            greeting.text=text
        }
    }

    override fun setWeather(wm: WeatherModel?) {
        if(wm!=null) {
            val id = getImgId(wm)
            runOnUiThread {
                weatherImg.setImageResource(id)
                nowTemperature.text=wm.data.tempnow+"℃"
                temperature.text=wm.data.templow+"℃~"+wm.data.temphigh+"℃"
            }
        }
    }
    override fun screenOff() {
                runOnUiThread {
                  screenSwitch.alpha=1f
                }
    }

    override fun screenOn() {
                runOnUiThread {
                    screenSwitch.alpha=0f
                }
    }

    override fun screenOnOrOff(): Boolean {
        return   screenSwitch.alpha==1f
    }
   private fun getImgId(w:WeatherModel):Int{
        var id=R.drawable.ic_mai
       when {
           w.data.weather=="阴" -> id=R.drawable.ic_overcast
           w.data.weather=="多云" -> id=R.drawable.ic_cloudy
           w.data.weather=="暴雪" -> id=R.drawable.ic_baoxue
           w.data.weather=="暴雨" -> id=R.drawable.ic_baoyu
           w.data.weather=="大雪" -> id=R.drawable.ic_daxue
           w.data.weather=="大雨" -> id=R.drawable.ic_dayu
           w.data.weather=="雷阵雨" -> id=R.drawable.ic_leizhenyu
           w.data.weather=="雾" -> id=R.drawable.ic_fog
           w.data.weather=="霾" -> id=R.drawable.ic_mai
           w.data.weather=="小雪" -> id=R.drawable.ic_xiaoxue
           w.data.weather=="小雨" -> id=R.drawable.ic_xiaoyu
           w.data.weather=="雨夹雪" -> id=R.drawable.ic_yujiaxue
           w.data.weather=="阵雪" -> id=R.drawable.ic_zhenxue
           w.data.weather=="中雪" -> id=R.drawable.ic_zhongxue
           w.data.weather=="中雨" -> id=R.drawable.ic_zhongyu
           w.data.weather=="晴" -> id=R.drawable.ic_ziwaixian_white
       }

        return id
    }
}
