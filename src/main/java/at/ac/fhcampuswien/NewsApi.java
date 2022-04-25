package at.ac.fhcampuswien;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.Objects;

public class NewsApi {

    String url1 = "https://newsapi.org/v2/top-headlines?q=&apiKey=9945141504194293afe0fadac8190f08&country=at";
    String url2 = "https://newsapi.org/v2/everything?q=bitcoin&apiKey=9945141504194293afe0fadac8190f08&country=at";
    String q = "co";
    String q2 = "Biotechkonzern";
    String part1 = "https://newsapi.org/v2/top-headlines?q=";
    String part2 = "&apiKey=9945141504194293afe0fadac8190f08&country=at";
    String urlBuilder = part1 + q + part2;
    String urlBuilder2 = part1 + q2 + part2;

    private String getUrl(String q) {
        return part1 + q + part2;
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


    /* public void tryingOutOkHttp() throws IOException {
        System.out.println(
                run("https://newsapi.org/v2/top-headlines?q=corona&apiKey=9945141504194293afe0fadac8190f08&country=at"));
        //what I get is a string of json, need to translate it now
    } */


    public String topHeadlinesOnly() {
        String json = null;
        try {
            json = run(urlBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        NewsResponse news = gson.fromJson(json,NewsResponse.class);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            sb.append(news.getArticles().get(i).getTitle());
            sb.append(System.lineSeparator()).append("Read more: ").append(news.getArticles().get(i).getUrl());
            sb.append(System.lineSeparator());
        }
        return sb.toString();

    }




    public String bitcoinHeadlines(){
        String json = null;
        try {
            json = run(urlBuilder2);
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
            for (int i = 0; i < totalResults2(); i++) {
                sb.append(news.getArticles().get(i));
            }
        }
        return sb.toString();
    }
/*
    public List<Article> topHeadlines() {
        String json = null;
        try {
            json = run("https://newsapi.org/v2/top-headlines?q=corona&apiKey=9945141504194293afe0fadac8190f08&country=at");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        NewsResponse news = gson.fromJson(json,NewsResponse.class);
        return news.getArticles();
    }

 */


    public int totalResults() {
        String json = null;
        try {
            json = run(urlBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        NewsResponse news = gson.fromJson(json,NewsResponse.class);
        return news.getTotalResults();
    }

    public int totalResults2() {
        String json = null;
        try {
            json = run(urlBuilder2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        NewsResponse news = gson.fromJson(json,NewsResponse.class);
        return news.getTotalResults();
    }



}
