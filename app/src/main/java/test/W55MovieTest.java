//package test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.github.catvod.spider.W55Movie;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class W55MovieTest {
//
//    private W55Movie w55Movie;
//
//    @BeforeEach
//    void setUp() {
//        w55Movie = new W55Movie();
//    }
//
//    @Test
//    void homeContent() throws Exception {
//        String s = w55Movie.homeContent(false);
//        System.out.println(s);
//    }
//
//    @Test
//    void categoryContent() throws Exception {
//        String s = w55Movie.categoryContent("/vodshow/4", "1", false, null);
//        System.out.println(s);
//    }
//
//    @Test
//    void detailContent() throws Exception {
//        List<String> ids = new ArrayList<>();
//        ids.add("467257.html");
//        String s = w55Movie.detailContent(ids);
//        System.out.println(s);
//    }
//
//    @Test
//    void searchContent() {
//    }
//
//    @Test
//    void playerContent() throws Exception {
//        String s = w55Movie.playerContent("", "467257-2-1.html", null);
//        System.out.println(s);
//    }
//}