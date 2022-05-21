package at.ac.fhcampuswien;

import java.util.*;


public class AppController {

    private List<Article> articles;
    NewsApi newsApi = new NewsApi();


    public void setArticles(List<Article> articles){
        this.articles = articles;
    }

    public List<Article> getArticles(){
        return articles;
    }

    public AppController() {
    }


/*
    public int getArticleCount() {
        if (newsApi.getTheNews("corona", "at", false) == null) {
            return 0;
        } else {
            return newsApi.totalResults();
        }
    }

 */

    public int getArticleCount(){
        if(articles == null) {
            return 0;
        }
        return articles.size();
    }

    public String getTopHeadlinesAustria() { //changed from List to String
        if (newsApi.getTheNews("corona", "at", true) == null) { //top
            return new ArrayList<>().toString();
        } else {
            articles = newsApi.getTheNews("corona", "at", true); //top
            setArticles(articles);


            //format
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < articles.size(); i++) {          // maximum 10 articles
                sb.append(getArticleCount());
                sb.append(articles.size());
                sb.append(">> ");
                sb.append(articles.get(i).getTitle());
                sb.append(System.lineSeparator()).append("Read more: ").append(articles.get(i).getUrl());
                sb.append(System.lineSeparator());
                sb.append("***************************************************************************************************");
                sb.append(System.lineSeparator());
            }
            return sb.toString();

        }
    }


    public String getAllNewsBitcoin() {         //String
        if (newsApi.getTheNews("bitcoin", "", false) == null) { //complete
            return new ArrayList<>().toString();
        } else {
            articles = newsApi.getTheNews("bitcoin", "", false);  //complete
            setArticles(articles);

            //better format (no [ or , from array)
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < articles.size(); i++) {
                sb.append(articles.get(i));
            }
            return sb.toString();
        }
    }

}
