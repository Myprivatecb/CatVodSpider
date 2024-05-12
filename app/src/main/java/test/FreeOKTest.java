//package test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.github.catvod.spider.FreeOK;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class FreeOKTest {
//
//    private FreeOK freeOK;
//
//    @BeforeEach
//    void setUp() {
//        freeOK = new FreeOK();
//    }
//
//    @Test
//    void homeContent() {
//        String s = freeOK.homeContent(true);
//        System.out.println(s);
//    }
//
//    @Test
//    void categoryContent() {
//        String s = freeOK.categoryContent("/vod-show/2--------", "3", true, null);
//        System.out.println(s);
//    }
//
//    @Test
//    void detailContent() {
//        List<String> ids = new ArrayList<>();
//        ids.add("https://www.freeok.vip/xplay/63071-8-1.html");
//        String s = freeOK.detailContent(ids);
//        System.out.println(s);
//    }
//
//    @Test
//    void searchContent() {
//    }
//
//    @Test
//    void playerContent() {
//    }
//}