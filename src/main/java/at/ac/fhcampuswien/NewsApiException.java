package at.ac.fhcampuswien;

public class NewsApiException extends Exception {

    NewsApiException(String message) {
        super(message);

    }

    NewsApiException() {
        super("There's seem to be an error! Please try again.");

    }
}
