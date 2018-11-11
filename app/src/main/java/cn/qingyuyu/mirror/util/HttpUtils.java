package cn.qingyuyu.mirror.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HttpUtils{

    public static String doGet(String urlStr,HashMap<String, String> headers)
    {
        int TIMEOUT_IN_MILLIONS = 3000;
        URL url;
        HttpURLConnection conn = null;
        InputStream is = null;
        StringBuilder result= new StringBuilder();
        try
        {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            conn.setRequestMethod("GET");
            Set<String> keys = headers.keySet();
            for (String key : keys) {
                conn.setRequestProperty(key, headers.get(key));
                System.out.println(key + ":" + headers.get(key));
            }
            if (conn.getResponseCode() == 200)
            {
                is = conn.getInputStream();
                int len ;
                byte[] buf = new byte[1024];

                while ((len = is.read(buf)) != -1)
                {
                    result.append(new String(buf, 0, len));
                }

                return result.toString();
            } else
            {
                throw new RuntimeException(" responseCode is not 200 ... "+conn.getResponseCode());
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (is != null)
                    is.close();
            } catch (Exception e)
            {
                System.out.print(e.toString());
            }
            if(conn!=null)
            conn.disconnect();
        }

        return null ;

    }


    public static String doPost(String url, String param) {  
        OutputStreamWriter out = null;  
        BufferedReader in = null;  
        String result = "";  
        try {  
            URL realUrl = new URL(url);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();  
            // 设置通用的请求属性  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("contentType", "application/json"); //发送json数据需要设置contentType
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            //1.获取URLConnection对象对应的输出流  
            out=new OutputStreamWriter(conn.getOutputStream(),"UTF-8");  
            // 发送请求参数  
            out.write(param);  
            // flush输出流的缓冲  
            out.flush();  
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("发送 POST 请求出现异常！"+e);  
            e.printStackTrace();  
        }  
        //使用finally块来关闭输出流、输入流  
        finally{  
            try{  
                if(out!=null){  
                    out.close();  
                }  
                if(in!=null){  
                    in.close();  
                }  
            }  
            catch(Exception ex){  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    } 



}