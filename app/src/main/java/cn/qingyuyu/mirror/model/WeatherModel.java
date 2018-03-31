package cn.qingyuyu.mirror.model;

import java.util.List;


public class WeatherModel {


    /**
     * data : {"city":"上海","temphigh":"23","templow":"12","updatetime":"2018-03-27 08:58:00","tempnow":"15","sendibletemp":"15","winddirect":"东风","windpower":"1级","humidity":"76","sunrise":"05:50","sunset":"18:10","weather":"晴","week":"星期二","nl":null,"date":"2018-03-27","index":[{"name":"紫外线强度指数","level":"弱","msg":"辐射较弱，涂擦SPF12-15、PA+护肤品。"},{"name":"运动指数","level":"不适宜","msg":"空气轻度污染，不宜在户外运动。"},{"name":"化妆指数","level":"控油","msg":"建议用露质面霜打底，水质无油粉底霜，透明粉饼，粉质胭脂。"},{"name":"感冒指数","level":"极易发","msg":"感冒极易发生，避免去人群密集的场所，勤洗手勤通风有利于降低感冒几率。"},{"name":"洗车指数","level":"非常适宜","msg":"洗车后，可至少保持4天车辆清洁，非常适宜洗车。"},{"name":"穿衣指数","level":"暖","msg":"春夏之交，天气渐暖，单衣单裤。"}],"pm25":{"aqi":0,"co":9,"o3":12,"pm10":-1,"pm2_5":105,"quality":"轻度","so2":9,"no2":35,"updatetime":"2018-03-27 08:00:00"},"daily":[{"date":"2018-03-27","week":"星期二","sunrise":"05:50","sunset":"18:10","temphigh":"23","templow":"12","weather":"晴"},{"date":"2018-03-28","week":"星期三","sunrise":"05:50","sunset":"18:10","temphigh":"25","templow":"14","weather":"多云"},{"date":"2018-03-29","week":"星期四","sunrise":"05:50","sunset":"18:10","temphigh":"23","templow":"15","weather":"晴"},{"date":"2018-03-30","week":"星期五","sunrise":"05:50","sunset":"18:10","temphigh":"19","templow":"14","weather":"多云"},{"date":"2018-03-31","week":"星期六","sunrise":"05:50","sunset":"18:10","temphigh":"21","templow":"15","weather":"晴"},{"date":"2018-04-01","week":"星期日","sunrise":"05:50","sunset":"18:10","temphigh":"22","templow":"15","weather":"小雨"},{"date":"2018-03-26","week":"星期一","sunrise":"05:50","sunset":"18:10","temphigh":"22","templow":"12","weather":"多云"}]}
     * status : 0
     * msg : ok
     */

    private DataBean data;
    private int status;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * city : 上海
         * temphigh : 23
         * templow : 12
         * updatetime : 2018-03-27 08:58:00
         * tempnow : 15
         * sendibletemp : 15
         * winddirect : 东风
         * windpower : 1级
         * humidity : 76
         * sunrise : 05:50
         * sunset : 18:10
         * weather : 晴
         * week : 星期二
         * nl : null
         * date : 2018-03-27
         * index : [{"name":"紫外线强度指数","level":"弱","msg":"辐射较弱，涂擦SPF12-15、PA+护肤品。"},{"name":"运动指数","level":"不适宜","msg":"空气轻度污染，不宜在户外运动。"},{"name":"化妆指数","level":"控油","msg":"建议用露质面霜打底，水质无油粉底霜，透明粉饼，粉质胭脂。"},{"name":"感冒指数","level":"极易发","msg":"感冒极易发生，避免去人群密集的场所，勤洗手勤通风有利于降低感冒几率。"},{"name":"洗车指数","level":"非常适宜","msg":"洗车后，可至少保持4天车辆清洁，非常适宜洗车。"},{"name":"穿衣指数","level":"暖","msg":"春夏之交，天气渐暖，单衣单裤。"}]
         * pm25 : {"aqi":0,"co":9,"o3":12,"pm10":-1,"pm2_5":105,"quality":"轻度","so2":9,"no2":35,"updatetime":"2018-03-27 08:00:00"}
         * daily : [{"date":"2018-03-27","week":"星期二","sunrise":"05:50","sunset":"18:10","temphigh":"23","templow":"12","weather":"晴"},{"date":"2018-03-28","week":"星期三","sunrise":"05:50","sunset":"18:10","temphigh":"25","templow":"14","weather":"多云"},{"date":"2018-03-29","week":"星期四","sunrise":"05:50","sunset":"18:10","temphigh":"23","templow":"15","weather":"晴"},{"date":"2018-03-30","week":"星期五","sunrise":"05:50","sunset":"18:10","temphigh":"19","templow":"14","weather":"多云"},{"date":"2018-03-31","week":"星期六","sunrise":"05:50","sunset":"18:10","temphigh":"21","templow":"15","weather":"晴"},{"date":"2018-04-01","week":"星期日","sunrise":"05:50","sunset":"18:10","temphigh":"22","templow":"15","weather":"小雨"},{"date":"2018-03-26","week":"星期一","sunrise":"05:50","sunset":"18:10","temphigh":"22","templow":"12","weather":"多云"}]
         */

        private String city;
        private String temphigh;
        private String templow;
        private String updatetime;
        private String tempnow;
        private String sendibletemp;
        private String winddirect;
        private String windpower;
        private String humidity;
        private String sunrise;
        private String sunset;
        private String weather;
        private String week;
        private Object nl;
        private String date;
        private Pm25Bean pm25;
        private List<IndexBean> index;
        private List<DailyBean> daily;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getTemphigh() {
            return temphigh;
        }

        public void setTemphigh(String temphigh) {
            this.temphigh = temphigh;
        }

        public String getTemplow() {
            return templow;
        }

        public void setTemplow(String templow) {
            this.templow = templow;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getTempnow() {
            return tempnow;
        }

        public void setTempnow(String tempnow) {
            this.tempnow = tempnow;
        }

        public String getSendibletemp() {
            return sendibletemp;
        }

        public void setSendibletemp(String sendibletemp) {
            this.sendibletemp = sendibletemp;
        }

        public String getWinddirect() {
            return winddirect;
        }

        public void setWinddirect(String winddirect) {
            this.winddirect = winddirect;
        }

        public String getWindpower() {
            return windpower;
        }

        public void setWindpower(String windpower) {
            this.windpower = windpower;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public Object getNl() {
            return nl;
        }

        public void setNl(Object nl) {
            this.nl = nl;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Pm25Bean getPm25() {
            return pm25;
        }

        public void setPm25(Pm25Bean pm25) {
            this.pm25 = pm25;
        }

        public List<IndexBean> getIndex() {
            return index;
        }

        public void setIndex(List<IndexBean> index) {
            this.index = index;
        }

        public List<DailyBean> getDaily() {
            return daily;
        }

        public void setDaily(List<DailyBean> daily) {
            this.daily = daily;
        }

        public static class Pm25Bean {
            /**
             * aqi : 0
             * co : 9
             * o3 : 12
             * pm10 : -1
             * pm2_5 : 105
             * quality : 轻度
             * so2 : 9
             * no2 : 35
             * updatetime : 2018-03-27 08:00:00
             */

            private int aqi;
            private int co;
            private int o3;
            private int pm10;
            private int pm2_5;
            private String quality;
            private int so2;
            private int no2;
            private String updatetime;

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public int getCo() {
                return co;
            }

            public void setCo(int co) {
                this.co = co;
            }

            public int getO3() {
                return o3;
            }

            public void setO3(int o3) {
                this.o3 = o3;
            }

            public int getPm10() {
                return pm10;
            }

            public void setPm10(int pm10) {
                this.pm10 = pm10;
            }

            public int getPm2_5() {
                return pm2_5;
            }

            public void setPm2_5(int pm2_5) {
                this.pm2_5 = pm2_5;
            }

            public String getQuality() {
                return quality;
            }

            public void setQuality(String quality) {
                this.quality = quality;
            }

            public int getSo2() {
                return so2;
            }

            public void setSo2(int so2) {
                this.so2 = so2;
            }

            public int getNo2() {
                return no2;
            }

            public void setNo2(int no2) {
                this.no2 = no2;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }
        }

        public static class IndexBean {
            /**
             * name : 紫外线强度指数
             * level : 弱
             * msg : 辐射较弱，涂擦SPF12-15、PA+护肤品。
             */

            private String name;
            private String level;
            private String msg;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }
        }

        public static class DailyBean {
            /**
             * date : 2018-03-27
             * week : 星期二
             * sunrise : 05:50
             * sunset : 18:10
             * temphigh : 23
             * templow : 12
             * weather : 晴
             */

            private String date;
            private String week;
            private String sunrise;
            private String sunset;
            private String temphigh;
            private String templow;
            private String weather;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public String getTemphigh() {
                return temphigh;
            }

            public void setTemphigh(String temphigh) {
                this.temphigh = temphigh;
            }

            public String getTemplow() {
                return templow;
            }

            public void setTemplow(String templow) {
                this.templow = templow;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }
        }
    }
}
