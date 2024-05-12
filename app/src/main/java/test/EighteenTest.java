//package test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.github.catvod.spider.Eighteen;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class EighteenTest {
//
//    private Eighteen eighteen;
//
//    @BeforeEach
//    void setUp() {
//        eighteen = new Eighteen();
//    }
//    @Test
//    void init() throws Exception {
//        eighteen.init(null, "");
//    }
//
//    @Test
//    void homeContent() throws Exception {
//        String s = eighteen.homeContent(false);
//        System.out.println(s);
//    }
//
//    @Test
//    void categoryContent() throws Exception {
//        String s = eighteen.categoryContent("chinese_random/all/index.html", "1", false, null);
//        System.out.println(s);
//    }
//
//    @Test
//    void detailContent() throws Exception {
//        List<String> ids = new ArrayList<>();
//        ids.add("chinese_content/43140/NNPJ-345.html");
//        String s = eighteen.detailContent(ids);
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
//
//    @Test
//    void playerContent() throws Exception {
//        String s = eighteen.playerContent("", "https://cdnhb.streamfastpro.com/9b068bcced6c51599c5184283c7c6484/3/2/9y386MZufCWtlOUyiI1IdA/9y386MZufCWtlOUyiI1IdA.m3u8", null);
//        System.out.println(s);
//    }
//}