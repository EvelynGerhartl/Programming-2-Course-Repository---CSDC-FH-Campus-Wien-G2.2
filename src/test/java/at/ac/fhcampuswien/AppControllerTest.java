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
    private List<Article> containingBitcoin = new ArrayList<>();
    Article art2 = new Article("Valentina Rossi", "El Salvador Bitcoin city planned at base of Conchagua volcano.");
    Article art3 = new Article("Sofia Vanoli", "Bitcoin less green since China ban, research suggests.");

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
        assertEquals(art.toString(),  mockCtrl.getMockList().toString());
    }


    @Test
    @DisplayName("getTopHeadlinesAustria")
    public void getTopHeadlinesAustria2() {
        System.out.print(mockCtrl.getTopHeadlinesAustria());
        String output = mockList.toString();

        assertEquals(output, outputStreamCaptor.toString());
    }

    @Test
    @DisplayName("getTopHeadlinesAustria when empty")
    public void getTopHeadlinesAustria3() {
        mockList.clear();            // emptying list to see if it returns an empty list
        System.out.print(mockList);
        assertEquals(Collections.emptyList().toString(), outputStreamCaptor.toString());
    }


    @Test
    @DisplayName("filterList")
    public void filterList() {
        List<Article> filteredArticles = new ArrayList<>();
        filteredArticles.add(art1);
        System.out.print(filteredArticles);


        assertEquals(outputStreamCaptor.toString(), mockCtrl.filterList("feminism", mockList).toString());


    }
    @Test
    @DisplayName("filterList with case-sensitive test")
    public void filterList2() {
        List<Article> filteredArticles = new ArrayList<>();
        filteredArticles.add(art1);
        System.out.print(filteredArticles);


        assertEquals(outputStreamCaptor.toString(), mockCtrl.filterList("FEMINISM",mockList).toString() );


    }

    @Test
    @DisplayName("getAllNewsBitcoin filters for Bitcoin")
    public void getAllNewsBitcoin() {
        System.out.print(mockCtrl.getAllNewsBitcoin());
        containingBitcoin.add(art2);
        containingBitcoin.add(art3);
        assertEquals(containingBitcoin.toString(), outputStreamCaptor.toString());
    }

}
