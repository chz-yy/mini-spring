package beans;


public class PropertyValue {
    private final String name;
    private final Object value;
    public PropertyValue(final String name, final Object value) {
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
