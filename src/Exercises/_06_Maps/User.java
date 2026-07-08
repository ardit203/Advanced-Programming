package Exercises._06_Maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private String userId;
    private Map<String, Session> sessions;

    public User(String userId){
        this.userId = userId;
        this.sessions = new HashMap<>();
    }

    public void addInteraction(String sessionId, String question, long timestampQuestion, String answer, long timestampAnswer, List<Attachment> attachments){
        sessions.computeIfAbsent(sessionId, Session::new).addInteraction(question, timestampQuestion, answer, timestampAnswer, attachments);
    }


}
