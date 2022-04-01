package at.ac.fhcampuswien;

public class Article {
    private String author;
    private String title;

    public Article(String author, String title) { //constructor
        this.author = author;
        this.title = title;
    }

    //getters:
    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String toString() {
        return "Title: " + this.title + " Author: " + this.author;
    }

}
