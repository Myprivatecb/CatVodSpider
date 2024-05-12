//package test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.github.catvod.spider.Bdys01;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//class Bdys01Test {
//
//    private Bdys01 bdys01;
//
//    @BeforeEach
//    void setUp() {
//        bdys01 = new Bdys01();
//    }
//
//    @Test
//    void homeContent() {
//        String s = bdys01.homeContent(true);
//        System.out.println(s);
//    }
//
//    @Test
//    void categoryContent() {
//        HashMap<String, String> extend = new HashMap<>();
//        extend.put("s", "donghua");
////        extend.put("area", "中国大陆");
////        extend.put("year", "2024");
////        extend.put("order", "1");
//        String s = bdys01.categoryContent("1", "1", true, extend);
//        System.out.println(s);
//    }
//
//    @Test
//    void detailContent() {
//        List<String> ids = new ArrayList<>();
//        ids.add("/donghua/22260.htm");
//        String s = bdys01.detailContent(ids);
//        System.out.println(s);
//    }
//
//    @Test
//    void playerContent() {
//        String s = bdys01.playerContent("", "/play/22260-0.htm", null);
//        System.out.println(s);
//    }
//
//    @Test
//    void searchContent() {
//    }
//}