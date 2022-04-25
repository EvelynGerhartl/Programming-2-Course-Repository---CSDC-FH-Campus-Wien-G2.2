package at.ac.fhcampuswien;

import java.util.Date;

public class Article {
    private String author;
    private String title;
    private String description;
    private String url; //String?
    private String urlToImage; //String?


    private Date publishedAt;
    private String content;

    public Article(String author, String title, String description, String url, String urlToImage, Date publishedAt, String content) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public Article() {

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
   public String toString() {
        return "Title: " + getTitle() + System.lineSeparator() +
                "Author: " + this.author + System.lineSeparator() +
                //"Description: " + this.description + System.lineSeparator() +
               //"UrlToImage: " + this.urlToImage + System.lineSeparator() +
                "Published at: " + this.publishedAt + System.lineSeparator() +
                "Content: " + this.content + System.lineSeparator() +
                "Read more on their website: " + this.url + System.lineSeparator();

    }

/*
    public String toString() {
        return "Title: " + getTitle() + System.lineSeparator() +
                "Author: " + getAuthor();
    } */

}

