<div class="clearfix" id="yui_3_18_1_1_1771366545842_86">
<p data-start="1010" data-end="1366" id="yui_3_18_1_1_1771366545842_90">Implement a class <strong data-start="1028" data-end="1038">Bucket</strong> that represents a “bucket” for storing objects in the cloud. Each object is defined by a key (e.g., <code data-start="1139" data-end="1165">photos/2024/12/image.jpg</code>). The object key consists of a prefix (<code data-start="1205" data-end="1221">photos/2024/12</code>) and the object/file name (e.g., <code data-start="1255" data-end="1266">image.jpg</code>). The prefix defines a logical directory and subdirectory structure in which the object is located.</p>
<p data-start="1368" data-end="1407">For the class, implement the following:</p>
<ul data-start="1409" data-end="1945" data-is-last-node="" data-is-only-node="">
<li data-start="1409" data-end="1448">
<p data-start="1411" data-end="1448">constructor <code data-start="1427" data-end="1448">Bucket(String name)</code></p>
</li>
<li data-start="1449" data-end="1545">
<p data-start="1451" data-end="1545">method <code data-start="1462" data-end="1490">void addObject(String key)</code> – adds a new object to the bucket with the given <code data-start="1540" data-end="1545">key. </code>When adding objects, objects with the same prefixes are grouped (see example test cases)</p>
</li>
<li data-start="1546" data-end="1798">
<p data-start="1548" data-end="1656">method <code data-start="1559" data-end="1590">void removeObject(String key)</code> – removes an existing object from the bucket with the given <code data-start="1651" data-end="1656">key</code></p>
<ul data-start="1659" data-end="1798">
<li data-start="1659" data-end="1798">
<p data-start="1661" data-end="1798">When removing an object, the corresponding prefixes must also be cleaned up (i.e., no prefixes without files should remain in the bucket)</p>
</li>
</ul>
</li>
<li data-start="1799" data-end="1945" data-is-last-node="">
<p data-start="1801" data-end="1945" data-is-last-node="">method <code data-start="1812" data-end="1831">String toString()</code> – prints information about all objects in the bucket. Indentation should be used when printing objects whose prefixes contain several parts delimited by /.</p>
</li>
</ul></div>

### Starter Code
```java
import java.util.*;
import java.util.stream.Collectors;


public class BucketTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // bucket name is fixed
        Bucket bucket = new Bucket("bucket");

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+", 2);
            String command = parts[0];

            if (command.equalsIgnoreCase("ADD")) {
                bucket.addObject(parts[1]);
            } else if (command.equalsIgnoreCase("REMOVE")) {
                bucket.removeObject(parts[1]);
            } else if (command.equalsIgnoreCase("PRINT")) {
                System.out.print(bucket);
            }
        }
    }
}
```