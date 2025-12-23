package Exercises._06_Maps;

public class AttachmentsSizeExceededException extends Exception{
    public AttachmentsSizeExceededException(int maxAllowedSize) {
        super(String .format("You have exceeded the max allowed size for the attachments, which is %d", maxAllowedSize));
    }
}
