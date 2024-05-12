//package test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.github.catvod.spider.Wobg;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class WobgTest {
//
//    private Wobg wobg;
//
//    @BeforeEach
//    void setUp() {
//        wobg = new Wobg();
//    }
//
//    @Test
//    void homeContent() {
//        String s = wobg.homeContent(true);
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
//        ids.add("/index.php/vod/detail/id/378.html");
//        String s = wobg.detailContent(ids);
//        System.out.println(s);
//    }
//
//    @Test
//    void searchContent() {
//    }
//
//    @Test
//    void testSearchContent() {
//    }
//}