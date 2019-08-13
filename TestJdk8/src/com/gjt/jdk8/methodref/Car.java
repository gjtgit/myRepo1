package com.gjt.jdk8.methodref;

public class Car {
    
    public static Car create(Supplier<Car> supplier) {
        return supplier.get();
    }
    
    public static void collide(Car car) {
        System.out.println("Collided " + car.toString());
    }
    
    public void follow(Car another) {
        System.out.println("Following the " + another.toString());
    }
    
    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
    
}
