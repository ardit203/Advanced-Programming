package Lab.Lab9.Task2;

import java.util.ArrayList;
import java.util.List;

public class XMLComposite extends XMLLeaf {
    private List<XMLComponent> components;

    public XMLComposite(String tagName) {
        super(tagName, null);
        this.components = new ArrayList<>();
    }

    @Override
    public String display(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t".repeat(indent));
        sb.append("<").append(tagName).append(" ").append(getAttrStringRepresentation()).append(">").append("\n");
        components.forEach(component -> sb.append(component.display(indent + 1)).append("\n"));
        sb.append("\t".repeat(indent));
        sb.append("</").append(tagName).append(">");
        return sb.toString();
    }

    public void addComponent(XMLComponent component) {
        components.add(component);
    }
}