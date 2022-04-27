package at.ac.fhcampuswien;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.Objects;


public class NewsApi {
    //key: 9945141504194293afe0fadac8190f08
    //url: https://newsapi.org/v2/top-headlines?country=at&apiKey=9945141504194293afe0fadac8190f08&q=corona


    public String urlMaker(String query, String country, boolean trueForTopHeadlinesOnly) {
        if (trueForTopHeadlinesOnly) {
            return "https://newsapi.org/v2/top-headlines?country="+country+"&apiKey=9945141504194293afe0fadac8190f08&q="+query;
        } else  {
            return "https://newsapi.org/v2/everything?q="+query+"&apiKey=9945141504194293afe0fadac8190f08";
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


    /** NOTE TO SELF: topHeadlinesOnly and completeNews could be easily simplified!! **/
    public String topHeadlinesOnly(String query, String country, boolean trueForTopHeadlinesOnly) {
        String url = urlMaker(query, country,trueForTopHeadlinesOnly);
        String json = null;
        try {
            json = run(url);         // topHeadlinesOnly has this parameters: query = a, country =at, &true for top headlines only
        } catch (IOException e) {
            e.printStackTrace();
        }


        Gson gson = new Gson();
        NewsResponse news = gson.fromJson(json,NewsResponse.class);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            sb.append("** ");
            sb.append(news.getArticles().get(i).getTitle());
            sb.append(System.lineSeparator()).append("Read more: ").append(news.getArticles().get(i).getUrl());
            sb.append(System.lineSeparator());
            sb.append("******************************************************************************************************");
            sb.append(System.lineSeparator());
        }
        return sb.toString();

    }


    public String completeNews(String query, String country, boolean trueForTopHeadlinesOnly){
        String url = urlMaker(query, country,trueForTopHeadlinesOnly);
        String json = null;
        try {
            json = run(url);                 /** TO DO: !!get query from somewhere else!! */
        } catch (IOException e) {
            e.printStackTrace();
        }


        Gson gson = new Gson();
        NewsResponse news = gson.fromJson(json,NewsResponse.class);
        StringBuilder sb = new StringBuilder();

        //some queries don´t have a lot of articles, how many should we even have? If the amount of articles with query
        //word are more than 3, we still will only display 3
        //if it´s less than 3... we will just display them
        if (news.getTotalResults() >=3) {
            sb.append(news.getArticles().get(0));
            sb.append(news.getArticles().get(1));
            sb.append(news.getArticles().get(2));

        } else {
            for (int i = 0; i < totalResults(); i++) {
                sb.append(news.getArticles().get(i));
            }
        }
        return sb.toString();
    }



    //doesn´t work yet
    public int totalResults() {         /** to be simplified so that we only have 1 totalResults method **/
        String json = null;
        try {
            json = run(urlMaker("a","at", true));         /** TO DO: !!get query from somewhere else!! */
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        NewsResponse news = gson.fromJson(json,NewsResponse.class);
        return news.getTotalResults();
    }


}
