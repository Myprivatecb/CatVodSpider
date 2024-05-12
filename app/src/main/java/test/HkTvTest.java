//package test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.github.catvod.spider.HkTv;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class HkTvTest {
//
//    private HkTv hkTv;
//
//    @BeforeEach
//    void setUp() {
//        hkTv = new HkTv();
//    }
//
//    @Test
//    void homeContent() throws Exception {
//        String s = hkTv.homeContent(false);
//        System.out.println(s);
//    }
//
//    @Test
//    void categoryContent() {
//    }
//
//    @Test
//    void detailContent() throws Exception {
//        List<String> ids = new ArrayList<>();
//        ids.add("166106.html");
//        String s = hkTv.detailContent(ids);
//        System.out.println(s);
//    }
//
//    @Test
//    void searchContent() {
//    }
//
//    @Test
//    void playerContent() throws Exception {
//        String s = hkTv.playerContent("", "166106/sid/1/nid/1.html", null);
//        System.out.println(s);
//    }
//}