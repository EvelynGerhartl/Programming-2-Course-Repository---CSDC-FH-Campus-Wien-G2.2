package at.ac.fhcampuswien;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class TryingOutGson {
    String query = "corona";

    String url2 = "";


    public void tryingOutGson() {
    }



    //okhttp
    OkHttpClient client = new OkHttpClient();

    public  String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        }
    }

    public void tryingOutOkHttp() throws IOException {
        System.out.println(
                run("https://newsapi.org/v2/top-headlines?q=corona&apiKey=9945141504194293afe0fadac8190f08&country=at"));
        //what I get is a string of json, need to translate it now
    }

    public void tryingOutGson2() throws IOException {
        String jsonString = run("https://newsapi.org/v2/top-headlines?q=corona&apiKey=9945141504194293afe0fadac8190f08&country=at");
        Gson gson = new Gson();
        Article article = gson.fromJson(jsonString, Article.class);
        //Article is our class to convert string to an object
        System.out.println(article.toString());
        System.out.println(article.getTitle());
        System.out.println(article.getContent());
        System.out.println(article.getContent());
    }


    public void tryingOutGson3() throws IOException {
        String jsonString = run("https://newsapi.org/v2/top-headlines?q=corona&apiKey=9945141504194293afe0fadac8190f08&country=at");
        File input = new File(jsonString);
        JsonElement fileElement = null;
        try {
            fileElement = JsonParser.parseReader(new FileReader(input));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonObject fileObject = fileElement.getAsJsonObject();

        //Extracting the basic fields
        String address = fileObject.get("status").getAsString();
        System.out.println(address);


    }


    public void tryNr300() throws IOException {
        String json = run("https://newsapi.org/v2/top-headlines?q=corona&apiKey=9945141504194293afe0fadac8190f08&country=at");
        Gson gson = new Gson();
        Article art = gson.fromJson(json,Article.class);
        System.out.println(art);


    }


}
