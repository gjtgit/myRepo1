package com.gjt.jdk8.methodref;

import java.util.Arrays;
import java.util.List;

public class TestMethodRefCar {
    public static void main(String[] args) {
        
        //1.构造器引用
        Car car = Car.create(Car::new);
        List<Car> cars = Arrays.asList( car );
        
        //2.静态方法引用
        cars.forEach( Car::collide );

        //3.特定类的任意对象方法引用
        cars.forEach( Car::repair );

        //4.特定对象的方法引用
        final Car police = Car.create( Car::new );
        cars.forEach( police::follow );

    }
}
