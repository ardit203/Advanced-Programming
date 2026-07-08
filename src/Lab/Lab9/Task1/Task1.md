# Lab Exercise 9 - Advanced Programming

## Task 1

<div class="clearfix" id="yui_3_18_1_1_1767574283332_96">
<div class="flex flex-col text-sm pb-25" id="yui_3_18_1_1_1767574283332_95">
<article class="text-token-text-primary w-full focus:outline-none [--shadow-height:45px] has-data-writing-block:pointer-events-none has-data-writing-block:-mt-(--shadow-height) has-data-writing-block:pt-(--shadow-height) [&amp;:has([data-writing-block])&gt;*]:pointer-events-auto scroll-mt-[calc(var(--header-height)+min(200px,max(70px,20svh)))]" dir="auto" tabindex="-1" data-turn-id="request-WEB:6014d68a-406f-4dbe-a0ea-b7fbde49941d-6" data-testid="conversation-turn-14" data-scroll-anchor="true" data-turn="assistant" id="yui_3_18_1_1_1767574283332_94">
<div class="text-base my-auto mx-auto pb-10 [--thread-content-margin:--spacing(4)] @w-sm/main:[--thread-content-margin:--spacing(6)] @w-lg/main:[--thread-content-margin:--spacing(16)] px-(--thread-content-margin)" id="yui_3_18_1_1_1767574283332_93">
<div class="[--thread-content-max-width:40rem] @w-lg/main:[--thread-content-max-width:48rem] mx-auto max-w-(--thread-content-max-width) flex-1 group/turn-messages focus-visible:outline-hidden relative flex w-full min-w-0 flex-col agent-turn" tabindex="-1" id="yui_3_18_1_1_1767574283332_92">
<div class="flex max-w-full flex-col grow" id="yui_3_18_1_1_1767574283332_91">
<div class="min-h-8 text-message relative flex w-full flex-col items-end gap-2 text-start break-words whitespace-normal [.text-message+&amp;]:mt-1" dir="auto" data-message-author-role="assistant" data-message-id="b448fb93-fc89-4a34-b0c0-3771738ca36c" data-message-model-slug="gpt-5-2" id="yui_3_18_1_1_1767574283332_90">
<div class="flex w-full flex-col gap-1 empty:hidden first:pt-[1px]" id="yui_3_18_1_1_1767574283332_89">
<div class="markdown prose dark:prose-invert w-full break-words light markdown-new-styling" id="yui_3_18_1_1_1767574283332_88">
<p data-start="0" data-end="116" id="yui_3_18_1_1_1767574283332_117">Implement a document viewing system. You are required to implement a class <code data-start="72" data-end="88">DocumentViewer</code> with the following methods:</p>
<ul data-start="118" data-end="962" data-is-last-node="" data-is-only-node="" id="yui_3_18_1_1_1767574283332_87">
<li data-start="118" data-end="154">
<p data-start="120" data-end="154"><code data-start="120" data-end="138">DocumentViewer()</code> – constructor</p>
</li>
<li data-start="155" data-end="332">
<p data-start="157" data-end="332"><code data-start="157" data-end="194">addDocument(String id, String text)</code> – method for adding a new document with ID <code data-start="238" data-end="242" data-is-only-node="">id</code> and content <code data-start="255" data-end="261">text</code>. The documents consist of multiple lines of text (separated by <code data-start="325" data-end="329">\n</code>)</p>
</li>
<li data-start="333" data-end="552">
<p data-start="335" data-end="552"><code data-start="335" data-end="365">enableLineNumbers(String id)</code> – method for numbering each line in the document with a sequential number before the line (e.g. <code data-start="462" data-end="466" data-is-only-node="">1.</code> at the beginning of the first line, <code data-start="503" data-end="507">2.</code> at the beginning of the second line, etc.)</p>
</li>
<li data-start="553" data-end="725">
<p data-start="555" data-end="725"><code data-start="555" data-end="583">enableWordCount(String id)</code> – method for adding a new line to the document with the content <code data-start="648" data-end="660" data-is-only-node="">"Words: W"</code>, where <code data-start="668" data-end="671">W</code> is the total number of words in the entire document</p>
</li>
<li data-start="726" data-end="886">
<p data-start="728" data-end="886"><code data-start="728" data-end="785">enableRedaction(String id, List&lt;String&gt; forbiddenWords)</code> – method that redacts (replaces with <code data-start="823" data-end="826" data-is-only-node="">*</code>) all words from the list <code data-start="852" data-end="868">forbiddenWords</code> in the document</p>
</li>
<li data-start="887" data-end="962" data-is-last-node="" id="yui_3_18_1_1_1767574283332_86">
<p data-start="889" data-end="962" data-is-last-node="" id="yui_3_18_1_1_1767574283332_85"><code data-start="889" data-end="909">display(String id)</code> – method that prints the document to standard output</p>
</li>
</ul>
</div>
</div>
</div>
</div>
</div>
</div>
</article>
</div>
<div>
</div></div>

### Starter code
```java
** There is no starter code **
```

### Solution
```java
import java.util.*;
import java.util.stream.Collectors;

interface Document {
    void enableLineNumbers();

    void enableWordCount();

    void enableRedaction();

    void display();

    List<String> getLines();
}


class ConcreteDocument implements Document {
    private String id;
    private List<String> lines;

    public ConcreteDocument(String id, String text) {
        this.id = id;
        this.lines = Arrays.stream(text.split("\n")).collect(Collectors.toList());
    }

    @Override
    public void enableLineNumbers() {
        //nothing
    }

    @Override
    public void enableWordCount() {
        //nothing
    }

    @Override
    public void enableRedaction() {
        //nothing
    }

    @Override
    public void display() {
        lines.forEach(System.out::println);
    }

    public List<String> getLines() {
        return lines;
    }

    public String getId() {
        return id;
    }
}


abstract class DocumentEnhancer implements Document {
    protected Document document;

    public DocumentEnhancer(Document document) {
        this.document = document;
    }

    @Override
    public List<String> getLines() {
        return document.getLines();
    }

    @Override
    public void display() {
        document.display();
    }
}


class EnableLineNumbers extends DocumentEnhancer {

    public EnableLineNumbers(Document document) {
        super(document);
    }

    @Override
    public void enableLineNumbers() {
        List<String> lines = getLines();
        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, (i + 1) + ": " + lines.get(i));
        }
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


class WordCount extends DocumentEnhancer {

    public WordCount(Document document) {
        super(document);
    }

    @Override
    public void enableLineNumbers() {
        //nothing
    }

    @Override
    public void enableWordCount() {
        List<String> lines = getLines();
        long count = lines.stream().flatMap(l -> Arrays.stream(l.split("\\s++"))).count();
        lines.add("Words: " + count);
    }

    @Override
    public void enableRedaction() {
        //nothing
    }
}

class EnableRedaction extends DocumentEnhancer {
    private Set<String> redactions;

    public EnableRedaction(Document document, List<String> redactions) {
        super(document);
        this.redactions = redactions.stream().map(String::toLowerCase).collect(Collectors.toSet());
    }

    @Override
    public void enableLineNumbers() {
        //nothing
    }

    @Override
    public void enableWordCount() {
        //nothing
    }

    @Override
    public void enableRedaction() {
        List<String> lines = getLines();
        for (int i = 0; i < lines.size(); i++) {
            String[] line = lines.get(i).split("\\s++");
            for (int j = 0; j < line.length; j++) {
                if(redactions.contains(line[j].toLowerCase())){
                    line[j] = "*";
                }
            }
            lines.set(i, String.join(" ", line));
        }
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
        document = new EnableLineNumbers(document);
        document.enableLineNumbers();
    }

    public void enableWordCount(String id) {
        Document document = documents.get(id);
        document = new WordCount(document);
        document.enableWordCount();
    }

    public void enableRedaction(String id, List<String> forbiddenWords) {
        Document document = documents.get(id);
        document = new EnableRedaction(document, forbiddenWords);
        document.enableRedaction();
    }

    public void display(String id) {
        documents.get(id).display();
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
            if (instructions.length > 2) {
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
```