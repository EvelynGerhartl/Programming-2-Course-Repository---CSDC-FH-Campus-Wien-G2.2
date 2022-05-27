package at.ac.fhcampuswien;

import java.util.*;
import java.util.function.Function;
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

    //4) Which articles have a title that consists of less than 15 characters?
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

    /**
     * END methods finished
     */


    /** START almost ready */

    /**DOESN'T WORK WITH TOP HEADLINES FOR SOME REASON!!!*/
    //5) Sort the articles by the length of their description in ascending order.  ✓
    // If the descriptions of articles are of the same length, the sorting should be alphabetical. ✓
    List<Article> streamAnalysis5() throws NewsApiException {
        //List<Article> descripList = articles;
        //descripList.removeIf(article -> article.getDescription().isEmpty());
//|| descripList.size() == 0
        //                throw new NewsApiException("Hey there! It seems the article you chose cannot be sorted by description. Please choose another option");


        if(articles == null || articles.isEmpty()) {
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
    /**END almost ready*/

/*
    String largestSource() {
        //Which provider (= source) delivers the most articles?
        articles = articles.stream()
                .max(Comparator.comparingInt(article-> article.getName().length()))
                .stream().toList();
        return articles.toString();
    }*/


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

    List<Article> longestAuthorName() throws NewsApiException { //not fully working
        if (articles == null) {
            throw new NewsApiException("Choose an article option before sorting");
        } else {
            articles = articles.stream()
                    .sorted(Comparator.comparingInt(article -> article.getAuthor().length()))
                    .collect(Collectors.toList());
            //    .max(Comparator.comparingInt(article-> article.getAuthor().length()))
            //      .stream().toList();


            return articles;
        }
    }




    String biggestSource() throws NewsApiException{
        //Which provider (= source) delivers the most articles?

        if(articles == null) {
            throw new NewsApiException();
        } else {
            Map<String, List<Article>> biggest = articles.stream()
                    .collect(Collectors.groupingBy(Article::getName,Collectors.toList()));
            //Collectors.counting()
            StringBuilder sb = new StringBuilder();
          //  sb.append(biggest.index);

            return biggest.toString();



                  //  .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            //.sorted(Comparator.comparing(Article::getName))
             //       .collect(Collectors.toList());
        }
        //.collect(Collectors.groupingBy(Article::getSource, Collectors.counting()));

        //.map(Source::getName).collect(Collectors.toList());
        /** .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
         .entrySet()
         .max(Map.Entry.comparingByValue())
         .ifPresent(System.out::println); */
    }

}
