# thingsmirror
用android things开发魔镜
(这里属于阉割版)

## 实现的功能:
            显示中文,全局自定义字体
            语音唤醒 语音识别 语音合成 识别命令后通过串口发送指令 (使用的百度TTS)
            智能对讲(使用的图灵机器人接口)
            显示时间 显示天气(付费接口 0.1￥/年)
            显示新闻(删减了滚动) 显示计划任务(删减了新增和修改) 显示问候语  (部分内容做删减)
            去除了服务端和IO口的控制


## 目前BUG: 因网络质量问题,会出现 语音延迟 时间错乱  甚至无限重启

## 如何使用

            **  AndroidManifest.xml  **
                对应的百度APP ID KEY 自己去注册申请


            **     MirrorService      **
                自己修改成自己的城市 获取天气的接口码,自己购买: https://market.aliyun.com/products/57096001/cmapi023133.html?sku=yuncode1713300000

            **　　重点来了 MainPresenter　　　**
                initTts()
                填入TTS的ID KEY之类的
                readyWeakup()
                填入APPID
                recYuyin()
                在  //与图灵机器人交流  中 修改userinfo 信息


## 最后,毕业快乐 (加星点赞来一波)