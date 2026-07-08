package Lab.Lab9.Task2;

public class Attribute {
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