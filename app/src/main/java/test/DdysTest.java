//package test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.github.catvod.spider.Ddys;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class DdysTest {
//
//    private Ddys ddys;
//
//    @BeforeEach
//    void setUp() {
//        ddys = new Ddys();
//    }
//
//    @Test
//    void homeContent() {
//        String s = ddys.homeContent(true);
//        System.out.println(s);
//    }
//
//    @Test
//    void categoryContent() {
//        String s = ddys.categoryContent("anime", "1", true, null);
//        System.out.println(s);
//    }
//
//    @Test
//    void detailContent() {
//        List<String> ids = new ArrayList<>();
//        ids.add("girl-from-nowhere");
//        String s = ddys.detailContent(ids);
//        System.out.println(s);
//    }
//
//    @Test
//    void playerContent() {
//        String s = ddys.playerContent("mp4", "https://v.ddys.pro/v/other/Girl_from_Nowhere/Girl_from_Nowhere_S01E01.mp4|https://ddys.pro/subddr//v/other/Girl_from_Nowhere/Girl_from_Nowhere_S01E01.ddr", null);
//        System.out.println(s);
//    }
//
//    @Test
//    void searchContent() {
//    }
//}