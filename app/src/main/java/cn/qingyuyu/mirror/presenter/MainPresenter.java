package cn.qingyuyu.mirror.presenter;

import android.content.Context;
import android.util.Log;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.TtsMode;


import org.json.JSONArray;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.TreeMap;


import cn.qingyuyu.mirror.model.WeatherModel;
import cn.qingyuyu.mirror.util.HttpUtils;
import cn.qingyuyu.mirror.view.MirrorService;
import cn.qingyuyu.mirror.view.inter.MainActivityInterface;

/**
 * Created by harry on 2018/4/7.
 *
 */

public class MainPresenter  {
    private boolean running=true;
    private  SpeechSynthesizer mSpeechSynthesizer=SpeechSynthesizer.getInstance();
    private EventManager asr;
    private  EventListener shibieListener ;
    private  EventListener weakupListener;
    private String yuyincmd="";
    private EventManager wp ;
   private MainActivityInterface mai;
   private ArrayList<String> schedules=new ArrayList<>();
    private MirrorService service=new MirrorService();
    private Context context;
   public MainPresenter(MainActivityInterface mai,Context context)
    {
        this.mai=mai;
        this.context=context;
    }
    public void start()
    {
        new Thread(new Runnable(){
            @Override
            public void run() {

                ArrayList<String> news=service.getNetNews();
                mai.setNews(news);
//
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                WeatherModel wm=service.getNetWeather();
                if(wm!=null)
                mai.setWeather(wm);
//
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Date d=service.getNetTime();
                Calendar ca=Calendar.getInstance();
                ca.setTime(d);
                ca.add(Calendar.HOUR_OF_DAY,8);

                mai.setTime(ca.getTime());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                initTts();

                readyWeakup();

                ArrayList<String> greetings=new ArrayList<>();
                greetings.add(" 任这世间百态成妖，风弛火燎，\n狂浪拍礁。只需静心一笑，\n安然等待就好。早安");
                greetings.add(" 无论怎样美好的前程，\n都要路过一个认真的现在。\n上午好");
                greetings.add(" 能享受最好的，也能承受最坏的。\n 早安");
                greetings.add("早上的晨光是我问候的主题，愿你今日愉悦多彩");
                greetings.add("早上好！每一天都是新的开始！");

                greetings.add(" 你今天看起来很不错！");
                greetings.add("音乐让人舒心，赞美让人甜心，\n爱情让人痴心，友情让人诚心，\n而你让人挂心");
                greetings.add(" 孔子曰：中午不睡，下午崩溃。\n孟子曰：孔子说的对！");
                greetings.add(" 中午好！");
                greetings.add("有一种心情，不管阳光灿烂还是阴雨连绵，笑容依旧甜甜");

                greetings.add(" 今天过的怎么样？");
                greetings.add(" 愿我的祝福像高高低低的风铃，\n给你带去叮叮铛铛的快乐！");
                greetings.add(" 问候不因疲惫而变懒，\n思念不因劳累而改变，\n祝福不因休息而变缓，晚安");
                greetings.add("每天努力一点，每天进步一点，\n就能创造一个意想不到的奇迹。晚安！");
                greetings.add("愿所有的幸福都陪伴着你，仰首是春，俯首是秋");

                greetings.add(" 天气或阴或晴，永不改变的是好心情");
                greetings.add(" 时光飞逝，抹不去情感的轨迹；\n岁月流转，荡不去美好的日志");
                greetings.add("生活有进有退，输什么也不能输了心情。");
                greetings.add("人生没有失败，哪里跌倒，就从哪里爬起，\n重整衣装，抖擞精神，迈开脚步，踏上旅途");
                greetings.add(" 每一缕暖暖的阳光，都是跳跃的音符；\n每一树香香的鲜花，都是一首甜美的歌谣");

                int index;
                while(running)
                {
                        Calendar cal = Calendar.getInstance();
                        int hour = cal.get(Calendar.HOUR_OF_DAY);
                        Random rand = new Random();

                        if (9 > hour && hour > 6) {
                            index = rand.nextInt(5);
                        } else if (hour > 11 && hour < 13) {
                            index = rand.nextInt(5) + 5;
                        } else if (hour > 19 && hour < 23) {
                            index = rand.nextInt(5) + 10;
                        } else {
                            index = rand.nextInt(5) + 15;
                        }
                        if(!service.hasPerson()) {//如果镜子前没有人了
                            mai.screenOff();
                        }
                        else {//镜子前有人
                            mai.setGreeting(greetings.get(index));
                            speak(greetings.get(index));
                            if(!mai.screenOnOrOff()) {//屏幕是关的
                               mai.screenOn();
                            }

//                                news=service.getNetNews();
//                                mai.setNews(news);
//                                wm=service.getNetWeather();
//                                mai.setWeather(wm);
                        }


                    try {
                        Thread.sleep(3*60*1000);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }


                }

            }
        }).start();
    }

    private void initTts()
    {
        //合成
        String ttsId = "*****";
        String ttsApiKey = "CV5********TV";
        String ttsSecretKey = "25e119******7982b1";
        mSpeechSynthesizer.setContext(context); // this 是Context的之类，如Activity
        mSpeechSynthesizer.setAppId(ttsId);
        mSpeechSynthesizer.setApiKey(ttsApiKey, ttsSecretKey);
        mSpeechSynthesizer.auth(TtsMode.MIX);//离在线混合
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");// 设置发声的人声音，在线生效
        mSpeechSynthesizer.initTts(TtsMode.MIX); // 初始化离在线混合模式

        //唤醒
        wp = EventManagerFactory.create(context, "wp");
        weakupListener = new EventListener() {
            @Override
            public void onEvent(String name, String params, byte[] bytes, int i, int i1) {
                //唤醒成功
                if (name .equals( "wp.data")) {
                    try {
                        JSONObject json =new JSONObject(params);
                        int errorCode = json.getInt("errorCode");
                        if (errorCode == 0) {
                            //唤醒成功
                            weakUp();
                        } else {
                            Log.e("weakup", " fail");
                            //唤醒失败
                        }
                    } catch (Exception e) {
                        Log.e("weakup", " "+e);
                    }
                } else if ("wp.exit" .equals( name)) {
                    Log.e("weakup", " stop");
                    //唤醒已停止
                }
            }
        };
        wp.registerListener(weakupListener);


        //识别
        asr = EventManagerFactory.create(context, "asr");
        shibieListener =new EventListener() {
            @Override
            public void onEvent(String name, String paramrs, byte[] bytes, int i, int i1) {
                if (name.equals( SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL) ){
                    try {
                        JSONObject json =new  JSONObject(paramrs);
                        String result = json.getString("results_recognition");
                        Log.e("parital", json.toString());
                        yuyincmd = result;
                        mai.setGreeting(result);
                    }
                    catch (Exception e)
                    {
                        Log.e("shibie",e.toString());
                    }
                } else if (name.equals( SpeechConstant.CALLBACK_EVENT_ASR_FINISH)) {
                    recYuyin(yuyincmd);
                }
            }
        };
        asr.registerListener(shibieListener);
    }

    private void readyWeakup()
    {
        TreeMap<String, Object> param = new TreeMap<>();
        param.put(SpeechConstant.WP_WORDS_FILE, "assets:///WakeUp.bin");
        param.put("appid", "****");

        JSONObject json =new  JSONObject(param);

        Log.e("weakup json",json.toString());

        wp.send(SpeechConstant.WAKEUP_START, json.toString(), null, 0, 0);
    }
    private void speak(String text)
    {
        try {
            Log.e("speak", text);
            mSpeechSynthesizer.speak(text);
        }catch (Exception e)
        {
            Log.e("speak",e.toString());
        }
    }
    private void readyListen()
    {
        //启动识别
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put(SpeechConstant.ACCEPT_AUDIO_VOLUME, false);
        params.put(SpeechConstant.VAD_ENDPOINT_TIMEOUT,1000);
        // 如识别短句，不需要需要逗号，使用1536搜索模型。其它PID参数请看文档
        params.put(SpeechConstant.PID, 1936);
        JSONObject json =new JSONObject(params);
        asr.send(SpeechConstant.ASR_START, json.toString(), null, 0, 0);
    }
    private void stopListen() {

            //停止识别
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put(SpeechConstant.ACCEPT_AUDIO_VOLUME,false);
            params.put(SpeechConstant.VAD_ENDPOINT_TIMEOUT, 2000);
            // 如识别短句，不需要需要逗号，使用1536搜索模型。其它PID参数请看文档
            params.put(SpeechConstant.PID, 1936);
            JSONObject json =new  JSONObject(params);
            asr.send(SpeechConstant.ASR_STOP, json.toString(), null, 0, 0);
    }
    private void weakUp()
    {
        mai.setGreeting("我在");
        speak("我在");
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        readyListen();
        Log.e("read listen","true");
    }
     private void recYuyin(final String cmd)
    {
        stopListen();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String s="null";
        if(cmd.contains("灯"))
        {
           s="ol";
            if(cmd.contains("关"))
                s="cl";

                Log.e("yuyin","灯");
        }

        else if(cmd.contains("窗帘"))
        {
            s="oc";
            if(cmd.contains("关"))
                s="cc";
                Log.e("yuyin","窗帘");

        }
        else if(cmd.contains("电扇"))
        {
             s="of";
            if(cmd.contains("关"))
                s="cf";

                Log.e("yuyin","电扇");


        }
        else if(cmd.contains("床"))
        {
             s="hb";
            if(cmd.contains("高"))
               s="lb";

                Log.e("yuyin","床");

        }
        else if(cmd.contains("楼梯"))
        {
             s="es";
            if(cmd.contains("收"))
                s="ss";

                Log.e("yuyin","楼梯");
        }
        else
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        //与图灵机器人交流
                        try {
                            JSONObject param = new JSONObject();

                            param.put("reqType",0);

                            JSONObject inputText = new JSONObject();
                            inputText.put("text",cmd);

                            JSONObject perception = new JSONObject();
                            perception.put("inputText",inputText);

                            param.put("perception",perception);

                            JSONObject userInfo = new JSONObject();
                            userInfo.put("apiKey","afb99******4ba");
                            userInfo.put("userId","2***9");

                            param.put("userInfo",userInfo);

                            String result=HttpUtils.doPost("http://openapi.tuling123.com/openapi/api/v2",param.toString());
                            Log.e("tuling",result);

                            JSONObject jsonObject =new JSONObject(result);
                            JSONArray ja=jsonObject.getJSONArray("results");
                            JSONObject value=ja.getJSONObject(0).getJSONObject("values");

                             speak(value.getString("text"));
                            Thread.sleep(8000);
                        }catch (Exception e)
                        {
                            mai.setGreeting(e.toString());
                        }
                        finally {
                            Log.e("readWeakup","tuling hou");
                        }
                    }
                }
        ).start();

        mai.sendCMD(s);
    }
    public void stop()
    {
        service.stopService();
        running=false;
        try {
            mSpeechSynthesizer.release();
            wp.unregisterListener(weakupListener);
            asr.unregisterListener(shibieListener);
        }
        catch(Exception e)
        {
            Log.e("ondestory","true");
        }
    }
    public ArrayList<String> getSchedule()
    {
        schedules.add("要去买个保温杯");
        schedules.add("明天是纪念日");
        return schedules;
    }
}
