package at.ac.fhcampuswien;

import java.util.*;
import java.util.stream.Collectors;


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



    public int getArticleCount(){
        if(articles == null) {
            return 0;
        }
        return articles.size();
    }

    public String getTopHeadlinesAustria() {
        if (newsApi.getTheNews("a", "at", true) == null) { //top
            return new ArrayList<>().toString();
        } else {
            articles = newsApi.getTheNews("a", "at", true); //top
           // setArticles(articles);


            //format
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < articles.size(); i++) {
                sb.append(articles.get(i));
            }
            /*
            for (int i = 0; i < articles.size(); i++) {
                sb.append(">> ");
                sb.append(articles.get(i).getTitle());
                sb.append(System.lineSeparator()).append("Read more: ").append(articles.get(i).getUrl());
                sb.append(System.lineSeparator());
                sb.append("***************************************************************************************************");
                sb.append(System.lineSeparator());
            }*/
            return sb.toString();

        }
    }


    public String getAllNewsBitcoin() {         //String
        if (newsApi.getTheNews("bitcoin", "", false) == null) { //complete
            return new ArrayList<>().toString();
        } else {
            articles = newsApi.getTheNews("bitcoin", "", false);  //complete
          //  setArticles(articles);

            //better format (no [ or , from array)
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < articles.size(); i++) {
                sb.append(articles.get(i));
            }
            return sb.toString();

        }
    }








    //ex 3 Streams : Analyzes the loaded articles.


    // 2) Which author has the longest name?
/*
    public String streamsAnalysis2() {
        String authors = art.getAuthor();
        authors.stream
               // .sorted(Comparator.comparing(Article::getAuthor)
    }*/

    //3) Which provider (=source) delivers the most articles?
    public int streamsAnalysis3(String source) {
        articles = articles.stream()
                .filter(article -> article.getSource().getName().equals(source)) //source = New York Times
                .collect(Collectors.toList());
        return getArticleCount();


        /** TO-DO: exception handling for filter = 0  (NullPointer Exception!) */
    }



    //5) Sort the articles by the length of their description in ascending order.  ✓
    // If the descriptions of articles are of the same length, the sorting should be alphabetical. ✓
    public List<Article> streamAnalysis5() {
            articles = articles.stream()
                    .sorted(Comparator.comparingInt(Article::getDescriptionLength) //by length
                            .thenComparing(Article::getDescription)) //alphabetical
                    .collect(Collectors.toList());
        return articles;
    }

}
