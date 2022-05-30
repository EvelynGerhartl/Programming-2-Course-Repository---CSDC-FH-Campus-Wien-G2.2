package at.ac.fhcampuswien;

import java.util.*;
import java.util.stream.Collectors;

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

    public String getTopHeadlinesAustria() throws NewsApiException {         //String
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

    public String getAllNewsBitcoin() throws NewsApiException {         //String
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

    /**
     * exercise 3: streams to analyze loaded articles
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
               // System.out.println("else if != 0");
                return "These articles have a title with less than 15 characters:"
                        + System.lineSeparator() + articles.toString();

            } else {
             //   System.out.println("else - articles.size = 0");
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

            if(articles.size() < 1) {
                //Index -1 out of bounds for length 0
                throw new NewsApiException("It's not possible to get the longest author name in the previously loaded article."
                        + System.lineSeparator() + "Please choose an article and try again.");
            }
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


            if(max.size() < 1) {
                //Index -1 out of bounds for length 0
                throw new NewsApiException("It's not possible to get the biggest provider of the previously loaded article."
                        + System.lineSeparator() + "Please choose an article and try again.");
            } else {
                // we need the LAST source (because it's sorted to be the biggest one)
                // last index of the list: size of list minus 1 - if list's size is 6, the last index is 5
                String biggestProvider = max.get(max.size() - 1).getKey();            //key = source
                String articleCount = max.get(max.size() - 1).getValue().toString();  //value = article count
                return "'" + biggestProvider + "'" + " is the provider with more articles, with " + articleCount + " articles.";

            }



        }

    }


    //5) Sort the articles by the length of their description in ascending order.  ✓
    // If the descriptions of articles are of the same length, the sorting should be alphabetical. ✓
    List<Article> sortByDescription() throws NewsApiException {

        if (articles == null || articles.isEmpty()) {
           // System.out.println("caught at 5th analysis"); //comment out before deadline
            throw new NewsApiException("Hey there! Please choose an article option before sorting by length.");

        } else {
            articles = articles.stream()
                    .filter(article -> article.getDescription() != null)
                    .sorted(Comparator.comparingInt(Article::getDescriptionLength) //by length
                            .thenComparing(Article::getDescription)) //alphabetical
                    .collect(toList());
            return articles;

        }

    }
}
