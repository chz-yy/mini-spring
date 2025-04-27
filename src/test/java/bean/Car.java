package bean;
public class Car {
    private String color;
    private String engine;

    public void setColor(String color) {
        this.color = color;
    }
    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Person getPerson() {
        return person;
    }

    private Person person;
    public String getColor() {
        return color;
    }
    public String getEngine() {
        return engine;
    }

    @Override
    public String toString() {
        return color+" and "+engine;
    }
}