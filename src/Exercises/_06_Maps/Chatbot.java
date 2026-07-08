package Exercises._06_Maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chatbot {
    private LLMModelDetails details;
    private List<String> notSupportedFiles;
    private int allowedAttachmentsSize;
    private Map<String, User> users;

    public Chatbot(LLMModelDetails details, List<String> notSupportedFiles, int allowedAttachmentsSize) {
        this.details = details;
        this.notSupportedFiles = notSupportedFiles;
        this.allowedAttachmentsSize = allowedAttachmentsSize;
        users = new HashMap<>();
    }


    public void addInteraction(String userId, String sessionId, String question, long timestampQuestion, String answer, long timestampAnswer, List<Attachment> attachments) throws Exception {
        checkFiles(attachments);
        users.computeIfAbsent(userId, User::new).addInteraction(sessionId, question, timestampQuestion, answer, timestampAnswer, attachments);
    }

    private void checkFiles(List<Attachment> attachments) throws Exception {
        long count = attachments.stream().mapToInt(a -> a.fileSizeInMb).sum();
        if(count > allowedAttachmentsSize){
            throw new AttachmentsSizeExceededException(allowedAttachmentsSize);
        }

        for (String file : notSupportedFiles){
            for (Attachment attachment : attachments){
                if (attachment.fileName.endsWith(file)) {
                    throw new FileNotSupportedException(notSupportedFiles);
                }
            }
        }
    }

    public void printConversation(String userId, String sessionId){

    }
}
