package at.ac.fhcampuswien;
import  java.util.*;

public class AppController {

    private List<Article> articles;


    public AppController() { //constructor

    }

    private static List<Article> generateMockList() {


        List<Article> articles = new ArrayList<>();

        Article art1 = new Article("Sravya Attaluri", "The activist of colour who is shaking up mental health and feminism through art.");
        Article art2 = new Article("Maeve Campbell", "How to have an eco-friendly period every month.");
        Article art3 = new Article("Valentina Rossi", "El Salvador Bitcoin city planned at base of Conchagua volcano.");
        Article art4 = new Article("Sofia Vanoli", "Bitcoin less green since China ban, research suggests.");

        articles.add(art1);
        articles.add(art2);
        articles.add(art3);
        articles.add(art4);

        return articles;

    }


}
