package com.github.catvod.spider;

import android.content.Context;
//import android.util.Base64;
import java.io.IOException;
import java.util.Base64;

import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.net.OKCallBack;
import com.github.catvod.net.OkHttp;

//import org.json.JSONArray;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONException;
import com.github.catvod.net.OkHttpUtil;
import com.github.catvod.net.OkResult;
//import org.json.JSONException;
//import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Response;

public class Auete extends Spider {
    private static final String siteUrl = "https://auete.com";
    private static final String siteHost = "auete.com";

    /**
     * 播放源配置
     */
    private JSONObject playerConfig = JSONObject.parseObject("{\"byun\":{\"sh\":\"云播B线\",\"pu\":\"\",\"sn\":0,\"or\":999},\"dphd\":{\"sh\":\"云播A线\",\"pu\":\"\",\"sn\":0,\"or\":999},\"cyun\":{\"sh\":\"云播C线\",\"pu\":\"\",\"sn\":0,\"or\":999},\"dbm3u8\":{\"sh\":\"云播D线\",\"pu\":\"\",\"sn\":0,\"or\":999},\"i8i\":{\"sh\":\"云播E线\",\"pu\":\"\",\"sn\":0,\"or\":999},\"m3u8hd\":{\"sh\":\"云播F线\",\"pu\":\"https://auete.com/api/?url=\",\"sn\":1,\"or\":999},\"languang\":{\"sh\":\"云播G线\",\"pu\":\"https://auete.com/api/?url=\",\"sn\":1,\"or\":999},\"hyun\":{\"sh\":\"云播H线\",\"pu\":\"https://auete.com/api/?url=\",\"sn\":1,\"or\":999},\"kyun\":{\"sh\":\"云播K线\",\"pu\":\"https://auete.com/api/?url=\",\"sn\":1,\"or\":999},\"bpyueyu\":{\"sh\":\"云播粤语\",\"pu\":\"\",\"sn\":0,\"or\":999},\"bpguoyu\":{\"sh\":\"云播国语\",\"pu\":\"\",\"sn\":0,\"or\":999},\"lyun\":{\"sh\":\"云播L线\",\"pu\":\"https://auete.com/api/?url=\",\"sn\":1,\"or\":999},\"myun\":{\"sh\":\"云播M线\",\"pu\":\"\",\"sn\":0,\"or\":999},\"dp\":{\"sh\":\"Dplayer\",\"pu\":\"\",\"sn\":0,\"or\":999}}");;
    /**
     * 筛选配置
     */
    private JSONObject filterConfig = JSONObject.parseObject("{\"Movie\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"喜剧片\",\"v\":\"xjp\"},{\"n\":\"动作片\",\"v\":\"dzp\"},{\"n\":\"爱情片\",\"v\":\"aqp\"},{\"n\":\"科幻片\",\"v\":\"khp\"},{\"n\":\"恐怖片\",\"v\":\"kbp\"},{\"n\":\"惊悚片\",\"v\":\"jsp\"},{\"n\":\"战争片\",\"v\":\"zzp\"},{\"n\":\"剧情片\",\"v\":\"jqp\"}]}],\"Tv\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"美剧\",\"v\":\"oumei\"},{\"n\":\"韩剧\",\"v\":\"hanju\"},{\"n\":\"日剧\",\"v\":\"riju\"},{\"n\":\"泰剧\",\"v\":\"yataiju\"},{\"n\":\"网剧\",\"v\":\"wangju\"},{\"n\":\"台剧\",\"v\":\"taiju\"},{\"n\":\"国产\",\"v\":\"neidi\"},{\"n\":\"港剧\",\"v\":\"tvbgj\"}]}],\"Zy\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国综\",\"v\":\"guozong\"},{\"n\":\"韩综\",\"v\":\"hanzong\"},{\"n\":\"美综\",\"v\":\"meizong\"}]}],\"Dm\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"动画\",\"v\":\"donghua\"},{\"n\":\"日漫\",\"v\":\"riman\"},{\"n\":\"国漫\",\"v\":\"guoman\"},{\"n\":\"美漫\",\"v\":\"meiman\"}]}],\"qita\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"纪录片\",\"v\":\"Jlp\"},{\"n\":\"经典片\",\"v\":\"Jdp\"},{\"n\":\"经典剧\",\"v\":\"Jdj\"},{\"n\":\"网大电影\",\"v\":\"wlp\"},{\"n\":\"国产老电影\",\"v\":\"laodianying\"}]}]}");;
    //分类页URL规则
    private Pattern regexCategory = Pattern.compile("/(\\w+)/index.html");
    //详情页URL规则
    private Pattern regexVid = Pattern.compile("/(\\w+/\\w+/\\w+)/");
    //播放页URL规则
    private Pattern regexPlay = Pattern.compile("(/\\w+/\\w+/\\w+/play-\\d+-\\d+.html)");
    //筛选页URL规则
    //private Pattern regexPage = Pattern.compile("/\\w+/\\w+/index(\\d+).html");
    private Pattern regexPage = Pattern.compile("/index(\\d+).html");

    @Override
    public void init(Context context) throws Exception {
        super.init(context);
        try {
            playerConfig = JSONObject.parseObject("{\"byun\":{\"sh\":\"云播B线\",\"pu\":\"\",\"sn\":0,\"or\":999},\"dphd\":{\"sh\":\"云播A线\",\"pu\":\"\",\"sn\":0,\"or\":999},\"cyun\":{\"sh\":\"云播C线\",\"pu\":\"\",\"sn\":0,\"or\":999},\"dbm3u8\":{\"sh\":\"云播D线\",\"pu\":\"\",\"sn\":0,\"or\":999},\"i8i\":{\"sh\":\"云播E线\",\"pu\":\"\",\"sn\":0,\"or\":999},\"m3u8hd\":{\"sh\":\"云播F线\",\"pu\":\"https://auete.com/api/?url=\",\"sn\":1,\"or\":999},\"languang\":{\"sh\":\"云播G线\",\"pu\":\"https://auete.com/api/?url=\",\"sn\":1,\"or\":999},\"hyun\":{\"sh\":\"云播H线\",\"pu\":\"https://auete.com/api/?url=\",\"sn\":1,\"or\":999},\"kyun\":{\"sh\":\"云播K线\",\"pu\":\"https://auete.com/api/?url=\",\"sn\":1,\"or\":999},\"bpyueyu\":{\"sh\":\"云播粤语\",\"pu\":\"\",\"sn\":0,\"or\":999},\"bpguoyu\":{\"sh\":\"云播国语\",\"pu\":\"\",\"sn\":0,\"or\":999},\"lyun\":{\"sh\":\"云播L线\",\"pu\":\"https://auete.com/api/?url=\",\"sn\":1,\"or\":999},\"myun\":{\"sh\":\"云播M线\",\"pu\":\"\",\"sn\":0,\"or\":999},\"dp\":{\"sh\":\"Dplayer\",\"pu\":\"\",\"sn\":0,\"or\":999}}");
            filterConfig = JSONObject.parseObject("{\"Movie\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"喜剧片\",\"v\":\"xjp\"},{\"n\":\"动作片\",\"v\":\"dzp\"},{\"n\":\"爱情片\",\"v\":\"aqp\"},{\"n\":\"科幻片\",\"v\":\"khp\"},{\"n\":\"恐怖片\",\"v\":\"kbp\"},{\"n\":\"惊悚片\",\"v\":\"jsp\"},{\"n\":\"战争片\",\"v\":\"zzp\"},{\"n\":\"剧情片\",\"v\":\"jqp\"}]}],\"Tv\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"美剧\",\"v\":\"oumei\"},{\"n\":\"韩剧\",\"v\":\"hanju\"},{\"n\":\"日剧\",\"v\":\"riju\"},{\"n\":\"泰剧\",\"v\":\"yataiju\"},{\"n\":\"网剧\",\"v\":\"wangju\"},{\"n\":\"台剧\",\"v\":\"taiju\"},{\"n\":\"国产\",\"v\":\"neidi\"},{\"n\":\"港剧\",\"v\":\"tvbgj\"}]}],\"Zy\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国综\",\"v\":\"guozong\"},{\"n\":\"韩综\",\"v\":\"hanzong\"},{\"n\":\"美综\",\"v\":\"meizong\"}]}],\"Dm\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"动画\",\"v\":\"donghua\"},{\"n\":\"日漫\",\"v\":\"riman\"},{\"n\":\"国漫\",\"v\":\"guoman\"},{\"n\":\"美漫\",\"v\":\"meiman\"}]}],\"qita\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"纪录片\",\"v\":\"Jlp\"},{\"n\":\"经典片\",\"v\":\"Jdp\"},{\"n\":\"经典剧\",\"v\":\"Jdj\"},{\"n\":\"网大电影\",\"v\":\"wlp\"},{\"n\":\"国产老电影\",\"v\":\"laodianying\"}]}]}");
        } catch (JSONException e) {
            SpiderDebug.log(e);
        }
    }

    /**
     * 爬虫headers
     *
     * @param url
     * @return
     */
    protected HashMap<String, String> getHeaders(String url) {
        HashMap<String, String> headers = new HashMap<>();
//        headers.put("method", "GET");
//        headers.put("Host", siteHost);
        headers.put("Upgrade-Insecure-Requests", "1");
        headers.put("DNT", "1");
        headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        headers.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        return headers;
    }

    /**
     * 获取分类数据 + 首页最近更新视频列表数据
     *
     * @param filter 是否开启筛选 关联的是 软件设置中 首页数据源里的筛选开关
     * @return
     */
    @Override
    public String homeContent(boolean filter) {
        try {
            Document doc = Jsoup.parse(OkHttp.string(siteUrl, getHeaders(siteUrl)));
            // 分类节点
            Elements elements = doc.select("ul[class='navbar-nav mr-auto']> li a");
            JSONArray classes = new JSONArray();
            for (Element ele : elements) {
                //分类名
                String name = ele.text();
                boolean show = name.equals("电影") ||
                        name.equals("电视剧") ||
                        name.equals("综艺") ||
                        name.equals("动漫") ||
                        name.equals("其他");
                if (show) {
                    Matcher mather = regexCategory.matcher(ele.attr("href"));
                    if (!mather.find())
                        continue;
                    // 把分类的id和名称取出来加到列表里
                    String id = mather.group(1).trim();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("type_id", id);
                    jsonObject.put("type_name", name);
                    classes.add(jsonObject);
                }
            }
            JSONObject result = new JSONObject();
            if (filter) {
                result.put("filters", filterConfig);
            }
            result.put("class", classes);
            try {
                // 取首页推荐视频列表
                Element homeList = doc.select("ul.threadlist").get(0);
                Elements list = homeList.select("li");
                JSONArray videos = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    Element vod = list.get(i);
                    String title = vod.selectFirst("h2 a").attr("title");
                    String cover = vod.selectFirst("a img").attr("src");
                    String remark = vod.selectFirst("a button").text();
                    Matcher matcher = regexVid.matcher(vod.selectFirst("a").attr("href"));
                    if (!matcher.find())
                        continue;
                    String id = matcher.group(1);
                    JSONObject v = new JSONObject();
                    v.put("vod_id", id);
                    v.put("vod_name", title);
                    v.put("vod_pic", cover);
                    v.put("vod_remarks", remark);
                    videos.add(v);
                }
                result.put("list", videos);
            } catch (Exception e) {
                SpiderDebug.log(e);
            }
            return result.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    /**
     * 获取分类信息数据
     *
     * @param tid    分类id
     * @param pg     页数
     * @param filter 同homeContent方法中的filter
     * @param extend 筛选参数{k:v, k1:v1}
     * @return
     */
    @Override
    public String categoryContent(String tid, String pg, boolean filter, HashMap<String, String> extend) {
        // 获取分类数据的url
        String url = "";
        try {
            if (extend != null && extend.size() > 0) {
                for (Iterator<String> it = extend.keySet().iterator(); it.hasNext(); ) {
                    String key = it.next();
                    String value = extend.get(key);
                    if (value != null && value.length() != 0 && value != " ") {
                        url = siteUrl + "/" + tid + "/" + value;
                    } else {
                        url = siteUrl + "/" + tid;
                    }
                    ;
                }
            } else {
                url = siteUrl + "/" + tid;
            }
            ;
            if (pg.equals("1")) {
                url = url + "/index.html";
            } else {
                url = url + "/index" + pg + ".html";
            }
            //System.out.println(url);
            String html = OkHttp.string(url, getHeaders(url));
            Document doc = Jsoup.parse(html);
            JSONObject result = new JSONObject();
            int pageCount = 0;
            int page = -1;

            // 取页码相关信息
            Elements pageInfo = doc.select("ul.pagination li");
            if (pageInfo.size() == 0) {
                page = Integer.parseInt(pg);
                pageCount = page;
            } else {
                for (int i = 0; i < pageInfo.size(); i++) {
                    Element li = pageInfo.get(i);
                    Element a = li.selectFirst("a");
                    if (a == null)
                        continue;
                    String name = a.text();
                    //System.out.println("名称"+name);
                    if (page == -1 && li.hasClass("active")) {
                        Matcher matcher = regexPage.matcher(a.attr("href"));
                        if (matcher.find()) {
                            //System.out.println("哈哈"+matcher.group(1));
                            page = Integer.parseInt(matcher.group(1));
                        } else {
                            Elements list = doc.select("ul.threadlist li");
                            if (list.size() > 0) {
                                page = 1;
                            } else {
                                page = 0;
                            }
                        }
                    }
                    if (name.equals("尾页")) {
                        Matcher matcher = regexPage.matcher(a.attr("href"));
                        if (matcher.find()) {
                            //System.out.println("尾页" + matcher.group(1));
                            pageCount = Integer.parseInt(matcher.group(1));
                        } else {
                            pageCount = 0;
                        }
                        break;
                    }
                }
            }

            JSONArray videos = new JSONArray();
            if (!html.contains("没有找到您想要的结果哦")) {
                // 取当前分类页的视频列表
                Elements list = doc.select("ul.threadlist li");
                for (int i = 0; i < list.size(); i++) {
                    Element vod = list.get(i);
                    String title = vod.selectFirst("h2 a").attr("title");
                    String cover = vod.selectFirst("a img").attr("src");
                    String remark = vod.selectFirst("a button").text();
                    Matcher matcher = regexVid.matcher(vod.selectFirst("a").attr("href"));
                    if (!matcher.find())
                        continue;
                    String id = matcher.group(1);
                    JSONObject v = new JSONObject();
                    v.put("vod_id", id);
                    v.put("vod_name", title);
                    v.put("vod_pic", cover);
                    v.put("vod_remarks", remark);
                    videos.add(v);
                }
            }
            result.put("page", page);
            result.put("pagecount", pageCount);
            result.put("limit", 20);
            result.put("total", pageCount <= 1 ? videos.size() : pageCount * 20);

            result.put("list", videos);
            return result.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    /**
     * 视频详情信息
     *
     * @param ids 视频id
     * @return
     */
    @Override
    public String detailContent(List<String> ids) {
        try {
            // 视频详情url
            String url = siteUrl + "/" + ids.get(0) + "/";
            //System.out.println(url);
            Document doc = Jsoup.parse(OkHttp.string(url, getHeaders(url)));
            JSONObject result = new JSONObject();
            JSONObject vodList = new JSONObject();

            // 取基本数据
            String cover = doc.selectFirst("div.cover a").attr("href");
            String title = doc.selectFirst("div.cover a").attr("title");
            Elements list1 = doc.select("div.message>p");
            String desc = doc.select("div.message>p").get(list1.size() - 1).text();
            String category = "", area = "", year = "", remark = "", director = "", actor = "";
            Elements span_text_muted = doc.select("div.message>p");
            for (int i = 0; i < span_text_muted.size() - 2; i++) {
                Element text = span_text_muted.get(i);
                String info = text.text();
                if (info.contains("分类")) {
                    try {
                        category = text.text().split(": ")[1];
                    } catch (Exception e) {
                        category = "";
                    }
                } else if (info.contains("年份")) {
                    try {
                        year = text.text().split(": ")[1];
                    } catch (Exception e) {
                        year = "";
                    }
                } else if (info.contains("地区")) {
                    try {
                        area = text.text().split(": ")[1];
                    } catch (Exception e) {
                        area = "";
                    }
                } else if (info.contains("影片备注")) {
                    try {
                        remark = text.text().split(": ")[1];
                    } catch (Exception e) {
                        remark = "";
                    }
                } else if (info.contains("导演")) {
                    try {
                        director = text.text().split(": ")[1];
                    } catch (Exception e) {
                        director = "";
                    }
                } else if (info.contains("主演")) {
                    try {
                        actor = text.text().split(": ")[1];
                    } catch (Exception e) {
                        actor = "";
                    }
                }
            }

            vodList.put("vod_id", ids.get(0));
            vodList.put("vod_name", title);
            vodList.put("vod_pic", cover);
            vodList.put("type_name", category);
            vodList.put("vod_year", year);
            vodList.put("vod_area", area);
            vodList.put("vod_remarks", remark);
            vodList.put("vod_actor", actor);
            vodList.put("vod_director", director);
            vodList.put("vod_content", desc);
            //System.out.println(vodList.toString());
            Map<String, String> vod_play = new TreeMap<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    try {
                        int sort1 = playerConfig.getJSONObject(o1).getIntValue("or");
                        int sort2 = playerConfig.getJSONObject(o2).getIntValue("or");

                        if (sort1 == sort2) {
                            return 1;
                        }
                        return sort1 - sort2 > 0 ? 1 : -1;
                    } catch (JSONException e) {
                        SpiderDebug.log(e);
                    }
                    return 1;
                }
            });

            // 取播放列表数据
            Elements sources = doc.select("div#player_list>h2");
            //System.out.println(sources.size());
            Elements sourceList = doc.select("div#player_list>ul");
            //System.out.println(sourceList.size());
            for (int i = 0; i < sources.size(); i++) {
                Element source = sources.get(i);
                //System.out.println(sources.text().split("：")[0].split("』")[1]);
                String sourceName = source.text().split("』")[1].split("：")[0];
                boolean found = false;
                for (String flag : playerConfig.keySet()) {
                    JSONObject subConfig = playerConfig.getJSONObject(flag);
                    if (subConfig.getString("sh").equals(sourceName)) {
                        sourceName = flag;
                        found = true;
                        break;
                    }
                }
                if (!found)
                    continue;
                String playList = "";
                Elements playListA = sourceList.get(i).select("li > a");
                //System.out.println(playListA.size());
                List<String> vodItems = new ArrayList<>();

                for (int j = 0; j < playListA.size(); j++) {
                    Element vod = playListA.get(j);
                    Matcher matcher = regexPlay.matcher(vod.attr("href"));
                    if (!matcher.find())
                        continue;
                    String playURL = matcher.group(1);
                    vodItems.add(vod.text() + "$" + playURL);
                }
                if (vodItems.size() > 0)
                    playList = String.join("#", vodItems);

                if (playList.length() == 0)
                    continue;

                vod_play.put(sourceName, playList);
            }

            if (vod_play.size() > 0) {
                String vod_play_from = String.join("$$$", vod_play.keySet());
                String vod_play_url = String.join("$$$", vod_play.values());
                vodList.put("vod_play_from", vod_play_from);
                vodList.put("vod_play_url", vod_play_url);
            }
            JSONArray list = new JSONArray();
            list.add(vodList);
            result.put("list", list);
            return result.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    /**
     * 获取视频播放信息
     *
     * @param flag     播放源
     * @param id       视频id
     * @param vipFlags 所有可能需要vip解析的源
     * @return
     */
    @Override
    public String playerContent(String flag, String id, List<String> vipFlags) {
        try {
            // 播放页 url
            String url = siteUrl + id;
            Document doc = Jsoup.parse(OkHttp.string(url, getHeaders(url)));
            /*取得script下面的JS变量*/
            Elements e = doc.select("div>script");
            /*循环遍历script下面的JS变量*/
            String player = "";
            String pn = "";
            JSONObject result = new JSONObject();
            for (Element element : e) {
                /*取得JS变量数组*/
                String[] data = element.data().toString().split("var");
                /*取得单个JS变量*/
                for (String variable : data) {
                    /*过滤variable为空的数据*/
                    if (variable.contains("=")) {
                        /*取到满足条件的JS变量*/
                        if (variable.contains("now")) {
                            String[] kvp = variable.split("=");
                            player = kvp[1].replaceAll("\"", "").replaceAll(";", "");
                            if (player.startsWith("base64")) {
                                String[] plist1 = player.split("\\(");
                                String[] plist2 = plist1[1].split("\\)");
                                // 解码
//                                player = new String(Base64.decode(plist2[0].getBytes(), Base64.DEFAULT));
                                byte[] base64Data = Base64.getDecoder().decode(plist2[0]);
                                player = new String(base64Data, "utf-8");
                            }
                            if (player.startsWith("http")) {
                                result.put("parse", 0);
                                result.put("playUrl", "");
                                result.put("url", player);
                                result.put("header", "");
                            } else if (player.contains("m3u8")) {
                                String finalvideo = "https://datas-s8pwfqdu9yystn90fb----------------cache.haozhansou.com/" + player;
                                result.put("parse", 0);
                                result.put("playUrl", "");
                                result.put("url", finalvideo);
                                result.put("header", "");
                            }
                        }
                        if (variable.contains("pn")) {
                            String[] kvp = variable.split("=");
                            pn = kvp[1].replaceAll("\"", "").replaceAll(";", "");
                        }
                    }
                }
                if (playerConfig.containsKey(pn)) {
                    JSONObject pCfg = playerConfig.getJSONObject(pn);
                    String videoUrl = "";
                    if (player.contains("https")) {
                        videoUrl = player;
                    }else {
                        String jiexiUrl = "https://jx.sc1080.top/?url=" + player;
                        Map<String, String> header = getHeaders(jiexiUrl);
                        header.put("Referer", "https://www.haozhansou.com/");
                        header.put("host", "jx.sc1080.top");
                        header.put("sec-fetch-dest", "document");
                        header.put("sec-fetch-mode", "navigate");
                        header.put("sec-fetch-site", "cross-site");
                        Document doc1 = Jsoup.parse(OkHttp.string(jiexiUrl, header));
                        Element e1 = doc1.select("body>script").get(0);
                        String regex = "(\"key\":\"|\"time\":\")([^\"]+)";
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(e1.data());
                        Map<String, String> values = new HashMap<>();
                        while (matcher.find()) {
                            if (matcher.group(1).startsWith("\"key\":\"")) {
                                values.put("key", matcher.group(2));
                            } else if (matcher.group(1).startsWith("\"time\":\"")) {
                                values.put("time", matcher.group(2));
                            }
                        }
                        String key = values.get("key");
                        String time = values.get("time");
                        Map<String, String> param = new HashMap<>();
                        param.put("url", player);
                        param.put("time", time);
                        param.put("key", key);
//                        param.put("title", "");
                        Map<String, String> header1 = new HashMap<>();
                        header1.put("origin", "https://jx.sc1080.top");
                        header1.put("host", "jx.sc1080.top");
                        header1.put("referer", "https://jx.sc1080.top/");
                        header1.put("accept", "application/json, text/javascript, */*; q=0.01");
                        header1.put("accept-language", "zh-CN,zh;q=0.9");
                        header1.put("sec-fetch-dest", "empty");
                        header1.put("sec-fetch-mode", "cors");
                        header1.put("sec-fetch-site", "same-origin");
                        header1.put("x-requested-with", "XMLHttpRequest");
                        header1.put("content-length", "104");
                        header1.put("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
                        header1.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.139 Safari/537.36");
                        String jsonData = OkHttp.post("https://jx.sc1080.top/api_config.php", param, header1).getBody();
                        // 解析JSON字符串
                        JSONObject jsonObject = JSONObject.parseObject(jsonData);;
                        videoUrl = jsonObject.getString("url");

                    }
                    String playUrl = pCfg.getString("pu");
                    result.put("parse", pCfg.getIntValue("sn"));
                    result.put("playUrl", playUrl);
                    result.put("url", videoUrl);
                    result.put("header", "");
                }
            }
            return result.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    /**
     * 搜索
     *
     * @param key
     * @param quick 是否播放页的快捷搜索
     * @return
     */
    @Override
    public String searchContent(String key, boolean quick) {
        try {
            String url = siteUrl + "/search.php?searchword=" + key;
            Document doc = Jsoup.parse(OkHttp.string(url, getHeaders(url)));
            JSONObject result = new JSONObject();

            JSONArray videos = new JSONArray();
            Elements list = doc.select("div.card-body>ul>li.media");
            for (int i = 0; i < list.size(); i++) {
                Element vod = list.get(i);
                String title = vod.select("div.media-body>div.subject>a>span").text();
                String cover = "";
                String remark = vod.selectFirst("div.media-body>div.d-flex>div.text-muted>span").text();
                Matcher matcher = regexVid.matcher(vod.select("div.media-body>div.subject>a").attr("href"));
                if (!matcher.find())
                    continue;
                String id = matcher.group(1);
                JSONObject v = new JSONObject();

                // 视频封面
                String vodurl = siteUrl + "/" + id + "/";
                Document voddoc = Jsoup.parse(OkHttp.string(vodurl, getHeaders(vodurl)));
                cover = voddoc.selectFirst("div.cover a").attr("href");

                v.put("vod_id", id);
                v.put("vod_name", title);
                v.put("vod_pic", cover);
                v.put("vod_remarks", remark);
                videos.add(v);
            }

            result.put("list", videos);
            return result.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }
}