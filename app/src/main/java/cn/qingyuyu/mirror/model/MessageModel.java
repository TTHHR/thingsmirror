package cn.qingyuyu.mirror.model;

/**
 * Created by harry on 2018/4/8.
 */

public class MessageModel {

    /**
     * timeSize : 15
     * city : 上海
     * newsSize : 12
     * schedule : 吃饭
     睡觉
     打豆豆
     */

    private int timeSize;
    private String city;
    private int newsSize;
    private String schedule;

    public int getTimeSize() {
        return timeSize;
    }

    public void setTimeSize(int timeSize) {
        this.timeSize = timeSize;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
