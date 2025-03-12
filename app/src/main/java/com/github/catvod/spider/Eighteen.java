package com.github.catvod.spider;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.github.catvod.bean.Class;
import com.github.catvod.bean.Result;
import com.github.catvod.bean.Vod;
import com.github.catvod.crawler.Spider;
import com.github.catvod.net.OkHttp;
import com.github.catvod.net.OkHttpUtil;
import com.github.catvod.utils.AES;
import com.github.catvod.utils.Util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Eighteen extends Spider {

    private final String url = "https://mjv002.com/zh/";
    private final String url_1 = "https://mjv002.com/zh/chinese_IamOverEighteenYearsOld/19/index.html";
    private String cookie="";

    @Override
    public void init(Context context, String extend) throws Exception {
        OkHttp.newCall("https://mjv002.com/zh/chinese_IamOverEighteenYearsOld/19/index.html").close();
    }

    protected HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Referer", "https://mjv002.com/zh/chinese_IamOverEighteenYearsOld/19/index.html");
        headers.put("User-Agent", Util.CHROME);
        String cookie = "";
        Map<String, List<String>> cookies = new HashMap<>();
        Document doc1 = Jsoup.parse(OkHttpUtil.string(url_1, headers, cookies));
        for( Map.Entry<String, List<String>> entry : cookies.entrySet() ){
            if("set-cookie".equals(entry.getKey())){
                cookie = String.join(";",entry.getValue());
                break;
            }
        }
        cookie = cookie + "; TSCvalue=gb";
        if(cookie.length()>0){
            headers.put("Cookie", cookie);
        }
        return headers;
    }

    public static String decrypto(String g, Integer hcdeedg252, Integer hadeedg252) {
//        int hcdeedg252 = 2; // 初始值
        hcdeedg252 = 25 >= hcdeedg252 ? hcdeedg252 : hcdeedg252 % 25; // 经过计算，hcdeedg252变为2
        char h = (char) (hcdeedg252 + 97); // 计算得到的分隔符为'b'
        String[] parts = g.split(String.valueOf(h)); // 使用分隔符'b'分割字符串

        StringBuilder decryptedStringBuilder = new StringBuilder();
//        int hadeedg252 = 29; // 根据说明，hadeedg252的值为29
        for (String part : parts) {
            int k = Integer.parseInt(part, hcdeedg252); // 假设数字是以10进制存储的
            k = k ^ hadeedg252; // 执行异或操作
            char decryptedChar = (char) k;
            decryptedStringBuilder.append(decryptedChar);
        }

        return decryptedStringBuilder.toString();
    }

    // 使用AES类进行解密
    public static String decrSun(String g, String f, String h, Integer hcdeedg252, Integer hadeedg252, String correctKey, String correctIV) {
//        int hcdeedg252 = 29; // 假设的偏移量，根据实际情况调整
        String decryptedData = decrypto(g, hcdeedg252, hadeedg252);

        // 使用AES类解密
//        String correctKey = "265e299a90e68e41";
//        String correctIV = "94466ad4af34a7bd";
        try {
            decryptedData = AES.CBC(decryptedData, correctKey, correctIV);
        } catch (Exception e) {
            System.out.println("AES解密失败：" + e.getMessage());
        }

        // 构建图片URL
        String imageUrl = f + decryptedData + h;
        // 示例：打印解密后的URL，实际应用中应根据环境进行UI更新
//        System.out.println("解密后的图片URL：" + imageUrl);
        return imageUrl;
    }

    @Override
    public String homeContent(boolean filter) throws Exception {
        List<Class> classes = new ArrayList<>();
        List<Vod> list = new ArrayList<>();
        Document doc = Jsoup.parse(OkHttpUtil.string(url, getHeaders()));
        for (Element a : doc.select("ul.animenu__nav > li > a")) {
            String typeName = a.text();
            String typeId = a.attr("href").replace(url, "");
            if (!typeId.contains("random/all/")) continue;
            if (typeName.contains("18H")) break;
            classes.add(new Class(typeId, typeName));
        }
        for (Element div : doc.select("div.post")) {
            String id = div.select("a").attr("href").replace(url, "");
            String name = div.select("h3").text();
            String pic = div.select("a > img").attr("src");
            String remark = div.select("div.meta").text();
            list.add(new Vod(id, name, pic, remark));
        }
        return Result.string(classes, list);
    }

    @Override
    public String categoryContent(String tid, String pg, boolean filter, HashMap<String, String> extend) throws Exception {
        List<Vod> list = new ArrayList<>();
        tid = tid.replace("random", "list");
        tid = tid.replace("index", pg);
        Document doc = Jsoup.parse(OkHttp.string(url + tid, getHeaders()));
        for (Element div : doc.select("div.post")) {
            String id = div.select("a").attr("href").replace(url, "");
            String name = div.select("h3").text();
            String pic = div.select("a > img").attr("src");
            String remark = div.select("div.meta").text();
            list.add(new Vod(id, name, pic, remark));
        }
        return Result.string(list);
    }

    @Override
    public String detailContent(List<String> ids) throws Exception {
        Document doc = Jsoup.parse(OkHttp.string(url + ids.get(0), getHeaders()));
        Element wrap = doc.select("div.video-wrap").get(0);
        String name = wrap.select("div.archive-title > h1").text();
        String pic = wrap.select("div.player-wrap > img").attr("src");
        String g = "";
        Element wrap1 = doc.select("script").get(6);
        // 正则表达式
        String regex = "mvarr\\['10_1'\\]\\=\\[\\['(.*?)','(.*?)'";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        // 创建匹配器
        Matcher matcher = pattern.matcher(wrap1.data());
        // 查找匹配项
        if (matcher.find()) {
            // 获取第一个捕获组（即我们想要的字符串）
            g = matcher.group(2);
        } else {
            System.out.println("String not found.");
        }
        String play_url = "";
        String hadeedg252Value = "";
        String hcdeedg252Value = "";
        String correctKey = "";
        String correctIV = "";
        Element wrap2 = doc.select("script").get(3);
        // 定义正则表达式
        String regex1 = "hadeedg252=(\\d+);hcdeedd252=(\\d+);var hdddedd252 = '([^']+)';var argdeqweqweqwe = '([^']+)';hadeedd252=(\\d+);var hdddedg252 = '([^']+)';" +
                "var argdeqweqweqww = '([^']+)';hcdeedg252=(\\d+);";
        // 编译正则表达式
        Pattern pattern1 = Pattern.compile(regex1);
        // 使用正则表达式执行匹配
        Matcher matcher1 = pattern1.matcher(wrap2.data());
        // 如果匹配成功，则提取所需参数
        if (matcher1.find()) {
            hadeedg252Value = matcher1.group(1);
            correctKey = matcher1.group(4);
            correctIV = matcher1.group(6);
            hcdeedg252Value = matcher1.group(8);
            String f = "https://mjv002.com/js/player/play.php?lo=on&id=";
            String imageSuffix = "";
            play_url = decrSun(g, f, imageSuffix, Integer.parseInt(hcdeedg252Value), Integer.parseInt(hadeedg252Value), correctKey, correctIV);
        }
        Document doc1 = Jsoup.parse(OkHttp.string(play_url, getHeaders()));
        String regex2 = "src:\\s*'([^']+)'";
        // 编译正则表达式
        Pattern pattern2 = Pattern.compile(regex2);
        // 使用正则表达式执行匹配
        Matcher matcher2 = pattern2.matcher(doc1.select("script").get(8).data());
        String vodplayurl = "";
        if (matcher2.find()) {
            vodplayurl = matcher2.group(1);
        }
        Vod vod = new Vod();
        vod.setVodId(ids.get(0));
        vod.setVodPic(pic);
        vod.setVodName(name);
        vod.setVodPlayFrom("18AV");
        vod.setVodPlayUrl("播放$" + vodplayurl);
        return Result.string(vod);
    }

    @Override
    public String searchContent(String key, boolean quick) throws Exception {
        return searchContent(key, "1");
    }

    @Override
    public String searchContent(String key, boolean quick, String pg) throws Exception {
        return searchContent(key, pg);
    }

    @Override
    public String playerContent(String flag, String id, List<String> vipFlags) throws Exception {
        JSONObject result = new JSONObject();
        Map<String, String> head = getHeaders();
        result.put("url", id);
        result.put("header", head);
        return result.toString();
    }

    @Override
    public boolean manualVideoCheck() throws Exception {
        return true;
    }

    @Override
    public boolean isVideoFormat(String url) throws Exception {
        return !url.contains("afcdn.net") && (url.contains(".m3u8") || url.contains(".mp4"));
    }

    private String searchContent(String key, String pg) {
        HashMap<String, String> params = new HashMap<>();
        params.put("search_keyword", key);
        params.put("search_type", "fc");
        params.put("op", "search");
        String res = OkHttp.post(url + "searchform_search/all/" + pg + ".html", params);
        List<Vod> list = new ArrayList<>();
        for (Element div : Jsoup.parse(res).select("div.post")) {
            String id = div.select("a").attr("href").replace(url, "");
            String name = div.select("h3").text();
            String pic = div.select("a > img").attr("src");
            String remark = div.select("div.meta").text();
            list.add(new Vod(id, name, pic, remark));
        }
        return Result.string(list);
    }
}
