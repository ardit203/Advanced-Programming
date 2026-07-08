package Lab.Lab9.Task1;

import java.util.*;
import java.util.stream.Collectors;

interface Document {
    void enableLineNumbers();

    void enableWordCount();

    void enableRedaction();

    void updateText(String text);

    String getText();
}


class ConcreteDocument implements Document {
    private String text;
    private String id;

    public ConcreteDocument(String id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }

    @Override
    public void enableLineNumbers() {}

    @Override
    public void enableWordCount() {

    }

    @Override
    public void enableRedaction() {

    }

    @Override
    public void updateText(String text) {
        this.text = text;
    }
}

abstract class DocumentDecorator implements Document {
    protected Document document;

    public DocumentDecorator(Document document) {
        this.document = document;
    }

    @Override
    public void updateText(String text) {
        document.updateText(text);
    }

    @Override
    public String getText() {
        return document.getText();
    }
}

class LineNumberer extends DocumentDecorator {

    public LineNumberer(Document document) {
        super(document);
    }

    @Override
    public void enableLineNumbers() {
        String text = getText();
        String[] lines = text.split("\n");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            sb.append(i + 1).append(": ").append(lines[i]);
            if (i == lines.length - 1) continue;
            sb.append("\n");
        }
        updateText(sb.toString());
    }

    @Override
    public void enableWordCount() {
        //nothing
    }

    @Override
    public void enableRedaction() {
        //nothing
    }
}

class WordCounter extends DocumentDecorator {

    public WordCounter(Document document) {
        super(document);
    }

    @Override
    public void enableLineNumbers() {
        //
    }

    @Override
    public void enableWordCount() {
        String text = getText();
        String [] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();
        sb.append(text).append("\n").append("Words: ").append(words.length);
        updateText(sb.toString());
    }

    @Override
    public void enableRedaction() {
        //
    }
}

class WordRedactor extends DocumentDecorator{
    private List<String> forbiddenWords;
    public WordRedactor(Document document, List<String> forbiddenWords) {
        super(document);
        this.forbiddenWords = forbiddenWords.stream().map(String::toLowerCase).collect(Collectors.toList());
    }

    @Override
    public void enableLineNumbers() {
        //
    }

    @Override
    public void enableWordCount() {
        //
    }

    @Override
    public void enableRedaction() {
        String text = getText();
        String [] lines = text.split("\n");

        String newText = Arrays.stream(lines).map(this::redact).collect(Collectors.joining("\n"));
        updateText(newText);
    }

    private String redact(String line){
        String [] words = line.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            if(forbiddenWords.contains(words[i].toLowerCase())){
                words[i] = "*";
            }
        }
        return String.join(" ", words);
    }

}


class DocumentViewer {
    private Map<String, Document> documents;

    public DocumentViewer() {
        this.documents = new HashMap<>();
    }

    public void addDocument(String id, String text) {
        documents.putIfAbsent(id, new ConcreteDocument(id, text));
    }

    public void enableLineNumbers(String id) {
        Document document = documents.get(id);
        document = new LineNumberer(document);
        document.enableLineNumbers();
    }

    public void enableWordCount(String id) {
        Document document = documents.get(id);
        document = new WordCounter(document);
        document.enableWordCount();
    }

    public void enableRedaction(String id, List<String> forbiddenWords) {
        Document document = documents.get(id);
        document = new WordRedactor(document, forbiddenWords);
        document.enableRedaction();
    }

    public void display(String id) {
        System.out.println(documents.get(id).getText());
    }
}

public class DocumentViewerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DocumentViewer documentViewer = new DocumentViewer();

        int numDocuments = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numDocuments; i++) {
            String id = scanner.nextLine();
            int numLines = Integer.parseInt(scanner.nextLine());
            List<String> lines = new ArrayList<>();
            for (int j = 0; j < numLines; j++) {
                lines.add(scanner.nextLine());
            }
            documentViewer.addDocument(id, String.join("\n", lines));
        }


        while (true) {
            String lineRead = scanner.nextLine();
            if (lineRead.equals("exit")) {
                break;
            }

            String[] instructions = lineRead.split(" ");
            String method = instructions[0];
            String id = instructions[1];

            List<String> redactions = new ArrayList<>();
            if (lineRead.length() > 2) {
                for (int i = 2; i < instructions.length; i++) {
                    redactions.add(instructions[i]);
                }
            }

            switch (method) {
                case "enableLineNumbers":
                    documentViewer.enableLineNumbers(id);
                    break;
                case "enableWordCount":
                    documentViewer.enableWordCount(id);
                    break;
                case "enableRedaction":
                    documentViewer.enableRedaction(id, redactions);
                    break;
                case "display":
                    System.out.printf("=== Document %s ===\n", id);
                    documentViewer.display(id);
                    break;
            }
        }


    }
}