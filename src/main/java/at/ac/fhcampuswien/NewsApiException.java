package at.ac.fhcampuswien;

public class NewsApiException extends Exception{

    NewsApiException(String message) {
        super(message);
        //System.out.println(getMessage()); //change before pushing

    }
    NewsApiException() {
        super("There's seem to be an error! Please try again.");

    }


/*
    NewsApiException(String message, Error error) { //?
        super(message);
        message= message + System.lineSeparator() +"("+ error.getMessage() + ")";
    }*/

}



//Declare the specific checked exceptions that your method can
//throw
// method() throws NewsApiException
