//package test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.github.catvod.spider.Xb6v;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class Xb6vTest {
//
//    private Xb6v xb6v;
//
//    @BeforeEach
//    void setUp() {
//        xb6v = new Xb6v();
//    }
//
//    @Test
//    void homeContent() {
//    }
//
//    @Test
//    void categoryContent() {
//    }
//
//    @Test
//    void detailContent() throws Exception {
//        List<String> ids = new ArrayList<>();
//        ids.add("/dianshiju/guoju/12320.html");
//        String s = xb6v.detailContent(ids);
//        System.out.println(s);
//    }
//
//    @Test
//    void searchContent() throws Exception {
//        String s = xb6v.searchContent("银河写手", true);
//        System.out.println(s);
//    }
//
//    @Test
//    void testSearchContent() {
//    }
//
//    @Test
//    void playerContent() {
//    }
//}