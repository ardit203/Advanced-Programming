# Lab Exercise 9 - Advanced Programming

## Task 2

<div class="clearfix" id="yui_3_18_1_1_1767580521680_105">
<p><code>XML</code> is one of the most commonly used formats for semi-structured data. Using the <code>Composite</code> design pattern, you are required to define classes that will enable the representation of simple and more complex XML elements.</p>
<p>An XML element is represented in the following way:</p>
<pre><code>&lt;tag attribute1="value1" attribute2="value2", ...&gt; value &lt;/tag&gt;
</code></pre>
<p>example:</p>
<pre><code>&lt;student type="redoven" smer="KNI"&gt; Trajce Trajkov &lt;/student&gt;
</code></pre>
<p>However, XML can also have a more complex structure, meaning that instead of a value it can contain other XML elements. These XML (sub)elements may in turn contain other XML elements, and so on. Example:</p>
<pre><code>&lt;student type="redoven"&gt;
    &lt;name&gt;
        &lt;first-name&gt;Trajce&lt;/first-name&gt;
        &lt;last-name&gt;Trajkov&lt;/last-name&gt;
    &lt;/name&gt;
&lt;/student&gt;
</code></pre>
<p>For this purpose, you need to define an interface <code>XMLComponent</code> and from it create two classes: <code>XMLLeaf</code> and <code>XMLComposite</code>.</p>
<p>Complete the main class according to the requirements stated in the comments.</p></div>

### Starter code
```java
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class XMLTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        XMLComponent component = new XMLLeaf("student", "Trajce Trajkovski");
        component.addAttribute("type", "redoven");
        component.addAttribute("program", "KNI");

        XMLComposite composite = new XMLComposite("name");
        composite.addComponent(new XMLLeaf("first-name", "trajce"));
        composite.addComponent(new XMLLeaf("last-name", "trajkovski"));
        composite.addAttribute("type", "redoven");
        component.addAttribute("program", "KNI");

        if (testCase==1) {
            //TODO Print the component object
        } else if(testCase==2) {
            //TODO print the composite object
        } else if (testCase==3) {
            XMLComposite main = new XMLComposite("level1");
            main.addAttribute("level","1");
            XMLComposite lvl2 = new XMLComposite("level2");
            lvl2.addAttribute("level","2");
            XMLComposite lvl3 = new XMLComposite("level3");
            lvl3.addAttribute("level","3");
            lvl3.addComponent(component);
            lvl2.addComponent(lvl3);
            lvl2.addComponent(composite);
            lvl2.addComponent(new XMLLeaf("something", "blabla"));
            main.addComponent(lvl2);
            main.addComponent(new XMLLeaf("course", "napredno programiranje"));

            //TODO print the main object
        }
    }
}
```

### Solution
```java
// package Lab.Lab9.Task2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Attribute {
    private String attribute;
    private String value;

    public Attribute(String attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%s=%c%s%c", attribute, '"', value, '"');
    }

    @Override
    public int hashCode() {
        return attribute.hashCode();
    }

@Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(getClass() != obj.getClass()) return false;
        Attribute attrObj = (Attribute) obj;
        return this.attribute.equals(attrObj.attribute);
    }
}
interface XMLComponent {
    void addAttribute(String attribute, String value);

    String display(int indent);
}


class XMLLeaf implements XMLComponent {
    protected Set<Attribute> attributes;
    protected String tagName;
    protected String value;

    public XMLLeaf(String tagName, String value) {
        this.tagName = tagName;
        this.value = value;
        this.attributes = new LinkedHashSet<>();
    }

    @Override
    public void addAttribute(String attribute, String value) {
        attributes.add(new Attribute(attribute, value));
    }

    @Override
    public String display(int indent) {
        String divider = attributes.isEmpty() ? "" : " ";
        return " ".repeat(indent * 4) +
                "<" + tagName + divider +
                getAttrStringRepresentation() +
                ">" + value + "</" + tagName + ">";
    }

    public String getAttrStringRepresentation() {
        return attributes.stream().map(Attribute::toString).collect(Collectors.joining(" "));
    }
}

class XMLComposite extends XMLLeaf {
    private List<XMLComponent> components;

    public XMLComposite(String tagName) {
        super(tagName, null);
        this.components = new ArrayList<>();
    }

    @Override
    public String display(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(indent * 4));
        sb.append("<").append(tagName).append(" ").append(getAttrStringRepresentation()).append(">").append("\n");
        components.forEach(component -> sb.append(component.display(indent + 1)).append("\n"));
        sb.append(" ".repeat(indent * 4));
        sb.append("</").append(tagName).append(">");
        return sb.toString();
    }

    public void addComponent(XMLComponent component) {
        components.add(component);
    }
}


public class XMLTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        XMLComponent component = new XMLLeaf("student", "Trajce Trajkovski");
        component.addAttribute("type", "redoven");
        component.addAttribute("program", "KNI");

        XMLComposite composite = new XMLComposite("name");
        composite.addComponent(new XMLLeaf("first-name", "trajce"));
        composite.addComponent(new XMLLeaf("last-name", "trajkovski"));
        composite.addAttribute("type", "redoven");
        component.addAttribute("program", "KNI");

        if (testCase == 1) {
            //TODO Print the component object
            System.out.println(component.display(0));
        } else if (testCase == 2) {
            //TODO print the composite object
            System.out.println(composite.display(0));
        } else if (testCase == 3) {
            XMLComposite main = new XMLComposite("level1");
            main.addAttribute("level", "1");
            XMLComposite lvl2 = new XMLComposite("level2");
            lvl2.addAttribute("level", "2");
            XMLComposite lvl3 = new XMLComposite("level3");
            lvl3.addAttribute("level", "3");
            lvl3.addComponent(component);
            lvl2.addComponent(lvl3);
            lvl2.addComponent(composite);
            lvl2.addComponent(new XMLLeaf("something", "blabla"));
            main.addComponent(lvl2);
            main.addComponent(new XMLLeaf("course", "napredno programiranje"));

            //TODO print the main object
            System.out.println(main.display(0));
        }
    }
}
```