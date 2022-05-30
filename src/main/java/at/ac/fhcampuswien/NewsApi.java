package at.ac.fhcampuswien;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class NewsApi {

    //our key: 9945141504194293afe0fadac8190f08
    //url: https://newsapi.org/v2/top-headlines?country=at&apiKey=9945141504194293afe0fadac8190f08&q=corona

    NewsResponse newsResponse = new NewsResponse();


    // builds URL to get according to query, country and if top headlines only or everything
    public String urlMaker(String query, String country, boolean trueForTopHeadlinesOnly) {
        if (trueForTopHeadlinesOnly) {
            return "https://newsapi.org/v2/top-headlines?country=" + country + "&apiKey=9945141504194293afe0fadac8190f08&q=" + query;
        } else {
            return "https://newsapi.org/v2/everything?q=" + query + "&apiKey=9945141504194293afe0fadac8190f08";
        }
    }


    //okhttp
    OkHttpClient client = new OkHttpClient();

    public String run(String url) throws IOException, NewsApiException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException | NullPointerException e) {
            throw new NewsApiException(""); // giuli's comment: second
        }
    }


    /** Feedback: change that it gets the last possible count (bitcoin or top headlines)
     * Solution: changed that get the news returns a List<Article> (not string).
     * getArticleCount in AppController works without any extra totalResults method.
     *
     * Feedback: "format" crowding NewsApi... better to have it in AppController
     * Solution: StringBuilder/Format is now in AppController
     * */
    /*
      public int totalResults() {

        String url = urlMaker("corona", "at", false);
        String json = null;
        try {
            json = run(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        NewsResponse news = gson.fromJson(json, NewsResponse.class);
        return news.getTotalResults();
    }

*/
    /*
    public String getTheNews(String query, String country, boolean trueForTopHeadlinesOnly) {
        String url = urlMaker(query, country, trueForTopHeadlinesOnly); //builds the url according to the parameters

        String json = null;
        try {
            json = run(url);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // "translating json to gson"
        Gson gson = new Gson();
        NewsResponse news = gson.fromJson(json, NewsResponse.class);


        // String Builder to build whatever "format" we need.
        StringBuilder sb = new StringBuilder();

        /** change the "format" to make it better, less crowded
         * better to have it in AppController */
/*
        //"format" of only Top Headlines
        if (trueForTopHeadlinesOnly) {
            for (int i = 0; i < 10; i++) {          // maximum 10 articles
                sb.append(">> ");
                sb.append(news.getArticles().get(i).getTitle());
                sb.append(System.lineSeparator()).append("Read more: ").append(news.getArticles().get(i).getUrl());
                sb.append(System.lineSeparator());
                sb.append("***************************************************************************************************");
                sb.append(System.lineSeparator());
            }


        } else { //format of "all news" - used in "get all news bitcoin" (content, author, etc, etc)

            if (news.getTotalResults() >= 3) {
                // if there´s more than 3 articles of the specific query, we only want to display 3
                sb.append(news.getArticles().get(0));
                sb.append(news.getArticles().get(1));
                sb.append(news.getArticles().get(2));

            } else {
                for (int i = 0; i < totalResults(); i++) {
                    //if the number of articles(total results) is smaller than 3, we only want the amount there is
                    sb.append(news.getArticles().get(i));
                }
            }
        }
        return sb.toString();
        // depending on which "if" statement was done, sb will either have the Top Headlines, whole news, etc, etc
        // toString() because we needed for our TextArea (gui)
    }

*/


    public List<Article> getTheNews(String query, String country, boolean trueForTopHeadlinesOnly) throws NewsApiException {
        String url = urlMaker(query, country, trueForTopHeadlinesOnly); //builds the url according to the parameters

        String json = null;
        try {
            json = run(url);
        } catch (IOException | NewsApiException | NullPointerException e) {  // giuli's comment: third
            System.out.println("");
        }


        // "translating json to gson"
        try {
            Gson gson = new Gson();
            NewsResponse news = gson.fromJson(json, NewsResponse.class);
            return news.getArticles(); }

        catch (Exception e) { // // giuli's comment: fourth
          //  System.out.println("There's no internet connection! Please check your connection and try again!");
        }
        return Collections.emptyList();

    }





}
