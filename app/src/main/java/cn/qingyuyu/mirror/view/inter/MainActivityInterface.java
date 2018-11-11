package cn.qingyuyu.mirror.view.inter;

import java.util.ArrayList;
import java.util.Date;

import cn.qingyuyu.mirror.model.WeatherModel;

/**
 * Created by harry on 2018/4/7.
 *
 */

public interface MainActivityInterface {
    public void setTime(Date time);
    public void setGreeting(String text);
    public void screenOff();
    public void screenOn();
    public boolean screenOnOrOff();
    public void sendCMD(String s);
    public void updateSchedule();
    public void setNews(ArrayList<String> list);
    public void setWeather(WeatherModel wm);
}
