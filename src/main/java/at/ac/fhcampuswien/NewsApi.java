package at.ac.fhcampuswien;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
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

    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        }
    }


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


        } else { //format of "whole news" (content, author, etc, etc)

            if (news.getTotalResults() >= 3) {     // if there´s more than 3 articles of the specific query, we only want to display 3
                sb.append(news.getArticles().get(0));
                sb.append(news.getArticles().get(1));
                sb.append(news.getArticles().get(2));

            } else {
                for (int i = 0; i < totalResults(); i++) {  //if the number of articles(total results) is smaller than 3, we only want the amount there is
                    sb.append(news.getArticles().get(i));
                }
            }
        }
        return sb.toString();
        // depending on which "if" statement was done, sb will either have the Top Headlines, whole news, etc, etc
        // toString() because we needed for our TextArea (gui)
    }



    //give the total results of all "available" news in at
    //according to ex2: "'q' must always be sent to the API!, you can choose the query freely - we choose corona
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


}
