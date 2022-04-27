package at.ac.fhcampuswien;

import java.util.*;


public class AppController {

    private List<Article> articles;
    NewsApi newsApi = new NewsApi();
    NewsResponse newsResp = new NewsResponse();


    public AppController() {//constructor
        setArticles(newsResp.getArticles());
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public int getArticleCount() {
        if (newsResp.getArticles() == null) {
            return 0;
        } else {
            return newsResp.getTotalResults();                  /** to be fixed*/
        }
    }


    public String getTopHeadlinesAustria() { //changed from List to String
        if (newsApi.topHeadlinesOnly() == null) {
            return new  ArrayList<>().toString();
        } else {
            return newsApi.topHeadlinesOnly();
        }
    }

    protected List<Article> filterList(String query, List<Article> articles) {
        articles.removeIf(a -> !a.getTitle().toLowerCase().contains(query.toLowerCase())); // Removes all Articles that don't contain query in the title, not case-sensitive
        return articles;
    }

    public String getAllNewsBitcoin() {         //String
        if (newsApi.completeNews() == null) {
            return new  ArrayList<>().toString();
        } else {
            return newsApi.completeNews();
        }
    }

    public List<Article> getAllNewsBitcoinOld() {
        List<Article> containingBitcoin = filterList("Bitcoin", articles);
        return containingBitcoin;
    }



/*
    private static List<Article> generateMockList() {


        List<Article> articles = new ArrayList<>();

        Article art1 = new Article("Sravya Attaluri", "The activist of colour who is shaking up mental health and feminism through art.");
        Article art2 = new Article("Maeve Campbell", "How to have an eco-friendly period every month.");
        Article art3 = new Article("Valentina Rossi", "El Salvador Bitcoin city planned at base of Conchagua volcano.");
        Article art4 = new Article("Sofia Vanoli", "Bitcoin less green since China ban, research suggests.");

        articles.add(art1);
        articles.add(art2);
        articles.add(art3);
        articles.add(art4);

        return articles;

    }
    public List<Article> getMockList() { //to use private defined generateMockList in AppControllerTest for testing proposes
        return generateMockList();
    }
*/



}
