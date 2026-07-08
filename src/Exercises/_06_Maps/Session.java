package Exercises._06_Maps;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Session {
    private String sessionId;
    private Set<Interaction> interactions;

    public Session(String sessionId) {
        this.sessionId = sessionId;
        this.interactions = new TreeSet<>();
    }

    public void addInteraction(String question, long timestampQuestion, String answer, long timestampAnswer, List<Attachment> attachments){
        interactions.add(new Interaction(question, timestampQuestion, answer, timestampAnswer, attachments));
    }
}
