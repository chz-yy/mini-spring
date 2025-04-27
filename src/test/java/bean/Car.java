package bean;
public class Car {
    private String color;
    private String engine;

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