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


    //setters:
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Title:" + this.title + " Author:" + this.author;
    }

}
