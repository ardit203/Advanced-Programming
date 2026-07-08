package Lab.Lab9.Task2;


import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class XMLLeaf implements XMLComponent {
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
        return "\t".repeat(indent) +
                "<" + tagName + divider +
                getAttrStringRepresentation() +
                ">" + value + "</" + tagName + ">";
    }

    public String getAttrStringRepresentation() {
        return attributes.stream().map(Attribute::toString).collect(Collectors.joining(" "));
    }
}