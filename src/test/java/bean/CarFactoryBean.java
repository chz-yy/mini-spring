package bean;

import beans.factory.FactoryBean;

public class CarFactoryBean implements FactoryBean<Car> {

    private String color;

    @Override
    public Car getObject() throws Exception {
       Car car= new Car();
       car.setColor(color);
       return car;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
