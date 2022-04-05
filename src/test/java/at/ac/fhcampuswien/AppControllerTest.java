package at.ac.fhcampuswien;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppControllerTest {

    private AppController mockCtrl = new AppController();
    private List<Article> mockList = mockCtrl.getMockList();

    private final Article art1 = new Article("Sravya Attaluri", "The activist of colour who is shaking up mental health and feminism through art."); //used in setArticles and getArticleCount2

    /** https://www.baeldung.com/java-testing-system-out-println */
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    /** end  plus @BeforeEach / @AfterEach */

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);         // back to "original" so we can use our @BeforeEach set up all over again.
    }


    @Test
    @DisplayName("getArticleCount: 4")
    public void getArticleCount1() {
        int actual = mockCtrl.getArticleCount();

        //assertion
        assertEquals(4, actual);        // we know we have 4 articles
    }

    @Test
    @DisplayName("getArticleCount: 5")
    public void getArticleCount2() {
        mockCtrl.setArticles(mockList);
        mockList.add(art1);                      // adding a new Article to make the count 5 and try that out
        int expected = mockList.size();          // also trying out the mockList.size() instead of writing the actual number
        int actual = mockCtrl.getArticleCount();

        // assertion
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("getArticleCount: 0")
    public void getArticleCount3() {            // if empty = 0 should come out
        mockCtrl.setArticles(mockList);
        mockList.clear();                       // we "clear" our mocklist to "empty" it and test if 0 comes out
        int actual = mockCtrl.getArticleCount();

        // assertion
        assertEquals(0, actual);

        //restoring mockList
        mockList =  mockCtrl.getMockList();

    }

    @Test
    @DisplayName("setArticles")
    public void setArticles() {
        List<Article> art = mockList;
        mockCtrl.setArticles( mockCtrl.getMockList());
        assertEquals(art,  mockCtrl.getMockList());
    }


    @Test
    @DisplayName("getTopHeadlinesAustria")
    public void getTopHeadlinesAustria2() {
        String output = mockList.toString();

        assertEquals(output, mockCtrl.getTopHeadlinesAustria().toString());
    }

    @Test
    @DisplayName("getTopHeadlinesAustria when empty")
    public void getTopHeadlinesAustria3() { // emptying list to see if it returns an empty list
       mockCtrl.setArticles(null);

        assertEquals(Collections.emptyList(), mockCtrl.getTopHeadlinesAustria());
    }


    @Test
    @DisplayName("filterList")
    public void filterList() {                                  //updated
        List<Article> filteredArticles = new ArrayList<>();
        filteredArticles.add(art1);

        assertEquals(filteredArticles, mockCtrl.filterList("feminism",mockList));


    }
    @Test
    @DisplayName("filterList with case-sensitive test")
    public void filterList2() {                                 //updated
        List<Article> filteredArticles = new ArrayList<>();
        filteredArticles.add(art1);

        assertEquals(filteredArticles, mockCtrl.filterList("FEMINISM",mockList));
    }

    @Test
    @DisplayName("getAllNewsBitcoin filters for Bitcoin")       //updated
    public void getAllNewsBitcoin() {
        List<Article> containingBitcoin = new ArrayList<>();
        containingBitcoin.add(new Article("Valentina Rossi", "El Salvador Bitcoin city planned at base of Conchagua volcano."));
        containingBitcoin.add(new Article("Sofia Vanoli", "Bitcoin less green since China ban, research suggests."));

        assertEquals(containingBitcoin, mockCtrl.getAllNewsBitcoin());
    }

}
