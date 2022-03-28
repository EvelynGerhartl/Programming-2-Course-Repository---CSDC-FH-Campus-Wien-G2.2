package at.ac.fhcampuswien;

import org.junit.jupiter.api.Test;
import java.util.List;
import static at.ac.fhcampuswien.AppController.generateMockList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppControllerTest {

    private AppController mockCtrl = new AppController();
    private List<Article> mockList = generateMockList();

    private final Article art1 = new Article("Mock Author", "Mock Title"); //used in setArticles and getArticleCount2


    /** Testing getArticleCount */
    @Test
    public void getArticleCount1() {
        int actual = mockCtrl.getArticleCount();
        assertEquals(4, actual);        // we know we have 4 articles
    }
    @Test
    public void getArticleCount2() {
        mockCtrl.setArticles(mockList);
        mockList.add(art1);                      // adding a new Article to make the count 5 and try that out
        int expected = mockList.size();          // also trying out the mockList.size() instead of writing the actual number
        int actual = mockCtrl.getArticleCount();

        // assertion
        assertEquals(expected, actual);
    }
    @Test
    public void getArticleCount3() {            // if empty = 0 should come out
        mockCtrl.setArticles(mockList);
        mockList.clear();                       // we "clear" our mocklist to "empty" it and test if 0 comes out
        int actual = mockCtrl.getArticleCount();

        // assertion
        assertEquals(0, actual);

        //restoring mockList
        mockList = generateMockList();

    }
    @Test
    public void setArticles(){
        List<Article> art = mockList;
        mockCtrl.setArticles(generateMockList());
        assertEquals(art.toString(), generateMockList().toString());
    }
}
