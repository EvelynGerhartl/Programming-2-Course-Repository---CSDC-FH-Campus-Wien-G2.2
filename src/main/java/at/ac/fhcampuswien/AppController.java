package at.ac.fhcampuswien;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


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

    /*
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
                }
                return sb.toString();

            }
        }
    */
    public String getTopHeadlinesAustria() {         //String
        if (newsApi.getTheNews("a", "at", true) == null) {
            return new ArrayList<>().toString();
        } else {
            articles = newsApi.getTheNews("a", "at", true);  //complete
            //  setArticles(articles);

            //better format (no [ or , from array)
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < articles.size(); i++) {
                sb.append(articles.get(i));
            }
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
    //3) How many articles come from the source "New York Times"?
    int getCountFromSource(String source) throws NewsApiException {
        if (articles == null) {
            throw new NewsApiException("Hey there! Please choose an article option before sorting");
        } else {
            articles = articles.stream()
                    .filter(article -> article.getSource().getName().equals(source)) //source = New York Times
                    .collect(toList());
            return getArticleCount();
        }
    }

    // 4) Which articles have a title that consists of less than 15 characters?
    String getTitleLessThan15() throws NewsApiException {
        if (articles == null) {
            throw new NewsApiException("Hey there! Please choose an article option first.");
        } else {
            articles = articles.stream()
                    .filter(article -> article.getTitle().length() < 15)
                    .collect(toList());

            if (articles.size() != 0) {
                System.out.println("else if != 0");
                return "These articles have a title with less than 15 characters:"
                        + System.lineSeparator() + articles.toString();

            } else {
                System.out.println("else - articles.size = 0");
                return "None of the articles have a title with less than 15 characters!";
            }
        }
    }

    // 2) Which author has the longest name?
    String getLongestAuthorName() throws NewsApiException {
        if (articles == null) {
            throw new NewsApiException("Choose an article option before sorting");
        } else {
            articles = articles.stream()
                    .filter(article -> article.getAuthor() != null)
                    .sorted(Comparator.comparingInt(article -> article.getAuthor().length()))
                    .collect(Collectors.toList());
            return "The author with the longest name is: " + System.lineSeparator()
                    + articles.get(articles.size() - 1).getAuthor();
            //articles.size()-1 .. last index = longest author name

        }
    }

    // 1) Which provider (= source) delivers the most articles?
    String biggestSource() throws NewsApiException {

        if (articles == null) {
            throw new NewsApiException();
        } else {

            // groups by source & counts how many articles each source has
            Map<String, Long> grouped = articles.stream()
                    .collect(Collectors.groupingBy(Article::getName, Collectors.counting()));

            // after grouping, it takes the "value" (count of articles) and sorts from min to max
            // (sorted reverse doesn't work for some reason)
            List<Map.Entry<String, Long>> max = grouped.entrySet()
                    .stream().sorted(Comparator.comparingLong(Map.Entry::getValue)).toList();

            // we need the LAST source (because it's sorted to be the biggest one)
            // last index of the list: size of list minus 1 - if list's size is 6, the last index is 5
            String biggestProvider = max.get(max.size() - 1).getKey();            //key = source
            String articleCount = max.get(max.size() - 1).getValue().toString();  //value = article count

            return "'" + biggestProvider + "'" + " is the provider with more articles, with " + articleCount + " articles.";

        }

    }

    /**
     * END methods finished
     */


    /** START almost ready */

    /**
     * DOESN'T WORK WITH TOP HEADLINES FOR SOME REASON!!!
     */
    //5) Sort the articles by the length of their description in ascending order.  ✓
    // If the descriptions of articles are of the same length, the sorting should be alphabetical. ✓
    List<Article> streamAnalysis5() throws NewsApiException {
        //List<Article> descripList = articles;
        //descripList.removeIf(article -> article.getDescription().isEmpty());
//|| descripList.size() == 0
        //                throw new NewsApiException("Hey there! It seems the article you chose cannot be sorted by description. Please choose another option");


        if (articles == null || articles.isEmpty()) {
            System.out.println("caught at 5th analysis"); //comment out before deadline
            throw new NewsApiException("Hey there! Please choose an article option before sorting by length.");

        } else {
            articles = articles.stream()
                    .sorted(Comparator.comparingInt(Article::getDescriptionLength) //by length
                            .thenComparing(Article::getDescription)) //alphabetical
                    .collect(toList());
            return articles;

        }

    }

    /**
     * END almost ready
     */

/*
    String largestSource() {
        //Which provider (= source) delivers the most articles?
        articles = articles.stream()
                .max(Comparator.comparingInt(article-> article.getName().length()))
                .stream().toList();
        return articles.toString();
    }*/



}
