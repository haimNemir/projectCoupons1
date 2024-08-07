package Exceptions;

public class AlreadyExistException extends Exception {
    public AlreadyExistException(String massage) {
        System.out.println(massage);
    }

    public AlreadyExistException() {
    }
}
