package Lab.Lab5.Task1;

public class NoSuchRoomException extends RuntimeException{
    public NoSuchRoomException(String roomName) {
        super(String.format("Room %s doesnt exists!", roomName));
    }
}
