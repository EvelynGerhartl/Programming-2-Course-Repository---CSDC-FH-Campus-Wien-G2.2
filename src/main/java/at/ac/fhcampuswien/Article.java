package at.ac.fhcampuswien;

import java.util.Date;

public class Article {
    private String author;
    private String title;
    public String description;
    private String url;
    private String urlToImage;
    private Date publishedAt;
    private String content;
    private Source source;

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

    public int getAuthorLength() {
        return getAuthor().length();
    }

    //public int getAuthorLength() {
     //   return getAuthor().length();
   // }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDescriptionLength() {
        return description.length();
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }


    public Date getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }


    @Override
    public String toString() {
        return ">> " + getTitle() + " <<" + System.lineSeparator() +
                "(Published by " + getAuthor() + ", on " + getPublishedAt() + ")" + System.lineSeparator() +
                //   "Description length: " + getDescriptionLength() +
                "Description: " + getDescription() + System.lineSeparator() +
                //"UrlToImage: " + this.urlToImage + System.lineSeparator() +
                getContent() + System.lineSeparator() + System.lineSeparator() +
                "Copy the link to read more on their website: " + getUrl() + System.lineSeparator() +
                "Source: " + getName() +
                "****************************************************************************************************"
                + System.lineSeparator() + System.lineSeparator();
    }



    public Source getSource() {
        return source;
    }

    public String getName() {
        return source.getName();
    }

}
