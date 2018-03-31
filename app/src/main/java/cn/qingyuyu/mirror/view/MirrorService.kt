package cn.qingyuyu.mirror.view

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import cn.qingyuyu.mirror.model.WeatherModel
import cn.qingyuyu.mirror.util.HttpUtils
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEntry
import com.google.code.rome.android.repackaged.com.sun.syndication.io.SyndFeedInput
import com.google.code.rome.android.repackaged.com.sun.syndication.io.XmlReader
import com.google.gson.Gson
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class MirrorService : Service() {
  private var running=true
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

    }


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
       running=false
        super.onDestroy()
    }

    private fun getWeather():WeatherModel {
        var weather=WeatherModel()
        val url = "http://chkj02.market.alicloudapi.com/qgtq?city=上海"
        val headers = HashMap<String,String>()

        headers["Host"] = "chkj02.market.alicloudapi.com"
        headers["Content-Type"] = "application/json; charset=utf-8"
        headers["Authorization"] = "APPCODE "+""
        try {
           val resposnse= HttpUtils.doGet(url, headers)

            Log.e("get weather",resposnse)

            val gson = Gson()
           weather = gson.fromJson(resposnse, WeatherModel::class.java)

        } catch (e: Exception) {
            Log.e("weather err",e.toString())
        }
        return weather
    }

    private fun getNetTime():Date {
        var date=Date(0)
        try {
           val url = URL("http://www.360.cn")
            val uc = url.openConnection()//生成连接对
            uc.connect() //发出连接
            val ld = uc.date //取得网站日期时间

             date= Date(ld)// 转换为标准时间对象
            val ca = Calendar.getInstance()
            ca.time = date
            ca.add(Calendar.HOUR_OF_DAY, 8)//树莓派时区问题 +8

            date=Date(ca.timeInMillis)

            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)// 输出北京时间

            Log.e("当前时间",""+sdf.format(date))
        } catch (e: Exception) {
            Log.e("get net time",""+e)
        }
        return date
    }

    private  fun getNews():List<String>{
        val rssUrl="http://news.qq.com/newsgn/rss_newsgn.xml"
        val rssItems =  ArrayList<String>()
        try{
            val feedUrl =URL(rssUrl).openConnection()
            feedUrl.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)")
            val input = SyndFeedInput()
            val feed = input.build(XmlReader(feedUrl))
            val feedType = feed.feedType//获取原feed属于哪种格式

            Log.e("type ",feedType)
            var entries=feed.entries
            if(entries!=null&&!entries.isEmpty())
            {
                for(entry in entries)
                {
                    if(entry is SyndEntry)
                    {
                       rssItems.add(entry.title)
                    }
                }
            }

            }catch(e :Exception){
            Log.e("get news",e.toString())
        }
        return rssItems
    }

}
