package at.ac.fhcampuswien;

public class NewsApiException extends Exception{

    NewsApiException(String message) {
        super(message);
        System.out.println(getMessage()); //change before pushing

    }
    NewsApiException() {
        super("Article is null");

    }
}



//Declare the specific checked exceptions that your method can
//throw
// method() throws NewsApiException
