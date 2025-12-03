package Lab.Lab5.Task1;


public class NoSuchUserException extends RuntimeException{
    public NoSuchUserException(String userName) {
        super(String.format("User with %s doesnt exists!", userName));
    }
}
