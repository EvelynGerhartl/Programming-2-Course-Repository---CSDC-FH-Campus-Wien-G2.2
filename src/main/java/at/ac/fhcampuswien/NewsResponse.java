package at.ac.fhcampuswien;

import java.util.List;

public class NewsResponse {

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    private String status;

    private int totalResults;

    private List<Article> articles;



}
