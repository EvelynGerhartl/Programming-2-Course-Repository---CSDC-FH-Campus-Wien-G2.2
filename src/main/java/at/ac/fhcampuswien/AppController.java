package at.ac.fhcampuswien;

import java.util.*;


public class AppController {

    private List<Article> articles;
    NewsApi newsApi = new NewsApi();


    public AppController() {//constructor
        setArticles(newsApi.newsResponse.getArticles());

    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }


    public int getArticleCount() {
        if (newsApi.getTheNews("corona","at",false) == null) {
            return 0;
        } else {
            return newsApi.totalResults();
        }
    }


    public String getTopHeadlinesAustria() { //changed from List to String
        if (newsApi.getTheNews("a","at", true) == null) { //top
            return new  ArrayList<>().toString();
        } else {
            return newsApi.getTheNews("a","at", true); //top

        }
    }


    /** not needed in ex2*/
/*
    protected List<Article> filterList(String query, List<Article> articles) {
        articles.removeIf(a -> !a.getTitle().toLowerCase().contains(query.toLowerCase())); // Removes all Articles that don't contain query in the title, not case-sensitive
        return articles;
    }
*/

    public String getAllNewsBitcoin() {         //String
        if (newsApi.getTheNews("bitcoin", "", false) == null) { //complete
            return new  ArrayList<>().toString();
        } else {
            return newsApi.getTheNews("bitcoin", "", false);  //complete
        }
    }

}
