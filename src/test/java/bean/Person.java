package bean;

public class Person {
    private String name;
    private int age;
    private String love;
    private Car car;
    public void info() {
        System.out.println(name+" "+age+" "+love+" "+car.getColor()+" "+car.getEngine()+" "+car.getPerson().love);
    }

    @Override
    public String toString() {
        return name+" love "+love;
    }
}
