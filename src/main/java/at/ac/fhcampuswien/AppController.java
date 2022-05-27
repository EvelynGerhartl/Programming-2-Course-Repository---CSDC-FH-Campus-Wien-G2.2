package at.ac.fhcampuswien;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


public class AppController {

    NewsApi newsApi = new NewsApi();
    private List<Article> articles;


    public AppController() {
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public int getArticleCount() {
        if (articles == null) {
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

    /**
     * START methods finished
     */
    //3) Which provider (=source) delivers the most articles?
    int getCountFromSource(String source) throws NewsApiException {
        if (articles == null) {
            throw new NewsApiException("Hey there! Please choose an article option before sorting");
        } else {
            ;
            articles = articles.stream()
                    .filter(article -> article.getSource().getName().equals(source)) //source = New York Times
                    .collect(Collectors.toList());
            return getArticleCount();
        }
    }

    //4) Which articles have a title that consists of less than 15 characters?
    String getTitleLessThan15() throws NewsApiException {
        if (articles == null) {
            throw new NewsApiException("Hey there! Please choose an article option first.");
        } else {
            articles = articles.stream()
                    .filter(article -> article.getTitle().length() > 15)
                    .collect(Collectors.toList());

            if (articles.size() != 0) {
                System.out.println("else if != 0");
                return articles.toString();

            } else {
                System.out.println("else");
                return "None of the articles have a title with less than 15 characters!";
            }
        }
    }

    /**
     * END methods finished
     */


    void largestSource() {
        //Which provider (= source) delivers the most articles?
        articles = articles.stream()
                .sorted()
                .collect(Collectors.toList());
    }


    /** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
/** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
/** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
/** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
/** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
/** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
    /** WHY IS getAuthor() NULL??!?!??! */
    /**
     * WHY IS getAuthor() NULL??!?!??!
     */


    String longestAuthorName() {
        //Which author has the longest name?
         /*
         articles = articles.stream()
                 .sorted(Comparator.comparingInt(Article::getAuthorLength))
                 .collect(Collectors.toList());
*/
        return articles.stream()
                .max(Comparator.comparingInt(Article::getAuthorLength))
                .get().toString();
    }




    //5) Sort the articles by the length of their description in ascending order.  ✓
    // If the descriptions of articles are of the same length, the sorting should be alphabetical. ✓
    List<Article> streamAnalysis5() {
        articles = articles.stream()
                .sorted(Comparator.comparingInt(Article::getDescriptionLength) //by length
                        .thenComparing(Article::getDescription)) //alphabetical
                .collect(Collectors.toList());
        return articles;

    }

    List<Article> biggestSource() {
        //Which provider (= source) delivers the most articles?

        //.collect(Collectors.groupingBy(Article::getSource, Collectors.counting()));

        //.map(Source::getName).collect(Collectors.toList());
        /** .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
         .entrySet()
         .max(Map.Entry.comparingByValue())
         .ifPresent(System.out::println); */
        return articles;
    }

}