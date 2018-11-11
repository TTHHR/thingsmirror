package cn.qingyuyu.mirror.view

import android.util.Log
import cn.qingyuyu.mirror.model.WeatherModel
import cn.qingyuyu.mirror.util.HttpUtils
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEntry
import com.google.code.rome.android.repackaged.com.sun.syndication.io.SyndFeedInput
import com.google.code.rome.android.repackaged.com.sun.syndication.io.XmlReader
import com.google.gson.Gson
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList
import com.google.android.things.pio.Gpio
import com.google.android.things.pio.PeripheralManager
import com.google.android.things.pio.GpioCallback


class MirrorService  {
    private lateinit var switchGpio: Gpio//gpio口
    private val gpioName="BCM4"
    private var city="上海"
    private var running=true
    private var hasPeople=true
//    private val mGpioCallback = object : GpioCallback {
//        override fun onGpioEdge(gpio: Gpio): Boolean {
//            // Read the active low pin state
//            hasPeople = !gpio.value
//            Log.e("ininin",""+gpio.value)
//            return true
//        }
//
//        override fun onGpioError(gpio: Gpio?, error: Int) {
//            Log.w("switch", gpio.toString() + ": Error event " + error)
//        }
//    }
//    constructor()
//    {
//            try {
//                val service = PeripheralManager.getInstance()
//                switchGpio = service.openGpio(gpioName)//打开
//                switchGpio.setDirection(Gpio.DIRECTION_IN)//
//                switchGpio.setEdgeTriggerType(Gpio.EDGE_BOTH)
//                switchGpio.registerGpioCallback(mGpioCallback)//注册中断回调函数
//            } catch (e: Exception) {
//                Log.e("gpio",e.toString())
//            }
//    }
    fun getNetWeather():WeatherModel? {
        var temp=WeatherModel()
        val url = "http://chkj02.market.alicloudapi.com/qgtq?city=$city"
        val headers = HashMap<String,String>()

        headers["Host"] = "chkj02.market.alicloudapi.com"
        headers["Content-Type"] = "application/json; charset=utf-8"
        headers["Authorization"] = "APPCODE "+"这里是APPCODE"

        try {
            val resposnse= HttpUtils.doGet(url, headers)

            Log.e("get weather",""+resposnse)

            val gson = Gson()
            temp = gson.fromJson(resposnse, WeatherModel::class.java)

        } catch (e: Exception) {
            Log.e("weather err",e.toString())
            return null
        }
        return temp
    }

     fun getNetTime():Date {
        var temp=Date(0)
        try {
           val url = URL("http://www.baidu.com")
            val uc = url.openConnection()//生成连接对
            uc.connect() //发出连接
            val ld = uc.date //取得网站日期时间

             temp= Date(ld)// 转换为标准时间对象
            val ca = Calendar.getInstance()
            ca.time = temp
            temp=Date(ca.timeInMillis)

        } catch (e: Exception) {
            Log.e("get net time",""+e)
        }
        return temp
    }

      fun getNetNews():ArrayList<String>{
        val rssUrl="http://news.qq.com/newsgn/rss_newsgn.xml"
        val news=ArrayList<String>()
        try{
            val feedUrl =URL(rssUrl).openConnection()
            feedUrl.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)")
            val input = SyndFeedInput()
            val feed = input.build(XmlReader(feedUrl))
            val feedType = feed.feedType//获取原feed属于哪种格式

            Log.e("type ",feedType)
            val entries=feed.entries
            if(entries!=null&&!entries.isEmpty())
            {
                news.clear()
                for(entry in entries)
                {
                    if(entry is SyndEntry)
                    {
                       news.add(entry.title)
                    }
                    if(news.size>5)
                        break
                }
            }

            }catch(e :Exception){
            Log.e("get news",e.toString())
        }
        return  news
    }
   fun hasPerson():Boolean
  {
      return hasPeople
  }
    fun stopService()
    {
        running=false
//        if(switchGpio!=null)
//            switchGpio.unregisterGpioCallback(mGpioCallback)
//            switchGpio.close()
    }
}
