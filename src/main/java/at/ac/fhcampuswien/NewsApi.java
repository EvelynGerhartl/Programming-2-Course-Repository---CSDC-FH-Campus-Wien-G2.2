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


    /**
     * Feedback: change that it gets the last possible count (bitcoin or top headlines)
     * Solution: changed that get the news returns a List<Article> (not string).
     * getArticleCount in AppController works without any extra totalResults method.
     * <p>
     * Feedback: "format" crowding NewsApi... better to have it in AppController
     * Solution: StringBuilder/Format is now in AppController
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
            return news.getArticles();
        } catch (Exception e) {             // giuli's comment: fourth
            //  System.out.println("There's no internet connection! Please check your connection and try again!");
        }
        return Collections.emptyList();

    }
}
