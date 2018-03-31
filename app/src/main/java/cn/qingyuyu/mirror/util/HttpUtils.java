package cn.qingyuyu.mirror.util;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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










}