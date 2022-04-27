package at.ac.fhcampuswien;

import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class NewsApi {

    String url1 = "https://newsapi.org/v2/top-headlines?q=&apiKey=9945141504194293afe0fadac8190f08&country=at";
    String url2 = "https://newsapi.org/v2/everything?q=bitcoin&apiKey=9945141504194293afe0fadac8190f08";
    String url3 = "https://newsapi.org/v2/top-headlines?q=corona&apiKey=9945141504194293afe0fadac8190f08&country=at";
    String url4 = "https://newsapi.org/v2/top-headlines?country=at&apiKey=9945141504194293afe0fadac8190f08";


    String q = "bitcoin";
    String queryTop = "corona";
    String q2 = "Biotechkonzern";
    String part1 = "https://newsapi.org/v2/top-headlines?q=";
    String part2 = "&apiKey=9945141504194293afe0fadac8190f08&country=at";
    //String urlBuilder = part1 + q + part2; // bticoin
    //String urlBuilder2 = part1 + queryTop + part2; // corona

    public String urlMaker(String query) {                      //why doesn´t this work???
        return part1+query+part2;
    }

    public void setQ(String q) {
        this.q = q;
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
            json = run(url1);               //why doesn´t it work with urlMaker???!!
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
            sb.append("*******************************************************************************************************");
            sb.append(System.lineSeparator());
        }
        return sb.toString();

    }


    /* public List<Article> topHeadlinesOnly() {
        String json = null;
        try {
            json = run(urlBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        NewsResponse news = gson.fromJson(json,NewsResponse.class);

        List<Article> articlesFromNewsApi = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            articlesFromNewsApi.add(news.getArticles().get(i));
        }
        return articlesFromNewsApi;

    } */



    public String completeNews(){
        String json = null;
        try {
            json = run(url2); // q  = bitcoin
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




    public int totalResults() {         /** to be simplified so that we only have 1 totalResults method **/
        String json = null;
        try {
            json = run(url3);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        NewsResponse news = gson.fromJson(json,NewsResponse.class);
        return news.getTotalResults();
    }

  /*  public int totalResultsBitcoin() {        /** to be simplified so that we only have 1 totalResults method
        String json = null;
        try {
            json = run(getUrl("bitcoin"));    // urlBuilder should be changeable
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        NewsResponse news = gson.fromJson(json,NewsResponse.class);
        return news.getTotalResults();
    }
*/


}
