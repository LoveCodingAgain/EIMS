package com.lx.eims.util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-04-05
 * time: 11:02
 * description:网页抓取工具类
 */
public class HtmlUtils {
    public static String getHtml(String urlStr,String encoding){
        URL url;
        StringBuffer html = new StringBuffer();
        try {
            url = new URL(urlStr);
            // 打开这个url 输入输出流
            URLConnection conn = url.openConnection();
            // 将字节流转换成字符流
            InputStreamReader isr = new InputStreamReader(conn.getInputStream(),encoding);
            // 使用缓存读取器
            BufferedReader reader = new BufferedReader(isr);
            String tmp = "";
            while((tmp = reader.readLine())!=null){
                html.append(tmp);
            }
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html.toString();
    }
    /**
     * 返回抓取的Map集合形式的数据
     */
    public static Map<String,Integer> getRange(String url){
        Map<String,Integer> results = new LinkedHashMap<String,Integer>();
        for(int i = 1 ; i <=12 ;i++){
            String tmpUrl ="";
            if(i>9){
                tmpUrl= url.replace("##", ""+i);
            }else{
                tmpUrl = url.replace("##", "0"+i);
            }
            String html = getHtml(tmpUrl,"gbk");
            // 如何解析 最好的解析方式是dom操作 js操作dom document.getElementById()
            Document document = Jsoup.parse(html.toString());
            //找到当前的薪资范围选值
            Element element = document.getElementById("filter_providesalary");
            Elements els = document.getElementsByClass("rt");
            results.put(element.getElementsByClass("dw_c_orange").get(0).text(), Integer.valueOf(els.get(0).text().replace("共","").replace("条职位", "")));
        }
        return results;
    }
}
