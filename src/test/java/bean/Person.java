package bean;

public class Person {
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    private String name;
    private int age;
    private String love;
    private Car car;
    public void info() {
        System.out.println(name+" "+age+" "+love+" "+car.getColor()+" "+car.getEngine()+" "+car.getPerson().love);
    }

    public void customInitMethod() {
        System.out.println("I was born in the method named customInitMethod");
    }

    public void customDestroyMethod() {
        System.out.println("I died in the method named customDestroyMethod");
    }


    @Override
    public String toString() {
        return name+" love "+love;
    }
}
