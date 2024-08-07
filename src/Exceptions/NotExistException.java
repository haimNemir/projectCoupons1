package Exceptions;

public class NotExistException extends Exception{
    public NotExistException() {
        super("The information you entered does not exist or is incorrect ");
    }

    public NotExistException(String massage) {
        super(massage);
    }
}
