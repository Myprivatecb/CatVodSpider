//package test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import android.content.Context;
//
//import com.alibaba.fastjson.JSONException;
//import com.alibaba.fastjson.JSONObject;
//import com.github.catvod.crawler.SpiderDebug;
//import com.github.catvod.spider.Auete;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class AueteTest {
//
//    private Auete auete;
//    /**
//     * 播放源配置
//     */
//    private JSONObject playerConfig;
//    /**
//     * 筛选配置
//     */
//    private JSONObject filterConfig;
//
//    @BeforeEach
//    void setUp() {
//        auete = new Auete();
//    }
//
//    @Test
//    void homeContent() {
//        String s = auete.homeContent(true);
//        System.out.println(s);
//    }
//
//    @Test
//    void categoryContent() {
//    }
//
//    @Test
//    void detailContent() {
//        List<String> ids = new ArrayList<>();
//        ids.add("Dm/donghua/xiongchumeinizhuanshikong");
//        String s = auete.detailContent(ids);
//        System.out.println(s);
//    }
//
//    @Test
//    void playerContent() {
//        String s = auete.playerContent("", "/Dm/donghua/xiongchumeinizhuanshikong/play-1-0.html", null);  // todo:https://www.haozhansou.com/jiexi.htm?url=SMDCQgo6O61Du7iZ967H_JGCZdUJtRU44ejNMUzJFcENQMw
//        System.out.println(s);
//    }
//
//    @Test
//    void searchContent() {
//    }
//}