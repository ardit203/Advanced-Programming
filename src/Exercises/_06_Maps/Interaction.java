package Exercises._06_Maps;


import java.util.Comparator;
import java.util.List;

public class Interaction implements Comparable<Interaction> {
    private String question;
    private long timestampQuestion;
    private String answer;
    private long timestampAnswer;
    private List<Attachment> attachments;

    public Interaction(String question, long timestampQuestion, String answer, long timestampAnswer, List<Attachment> attachments) {
        this.question = question;
        this.timestampQuestion = timestampQuestion;
        this.answer = answer;
        this.timestampAnswer = timestampAnswer;
        this.attachments = attachments;
    }

    public String getQuestion() {
        return question;
    }

    public long getTimestampQuestion() {
        return timestampQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public long getTimestampAnswer() {
        return timestampAnswer;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    @Override
    public int compareTo(Interaction other) {
        return Comparator.comparingLong(Interaction::getTimestampQuestion)
                .thenComparing(Interaction::getTimestampAnswer)
                .compare(this, other);
    }
}