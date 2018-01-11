package com.system.net.springFactory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CarFactoryBean implements FactoryBean<Car> {

    private String carInfo;

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        String[] infos = carInfo.split(",");
        car.setBrand(infos[0]);
        car.setMaxSpeed(Integer.valueOf(infos[1]));
        car.setPrice(Double.valueOf(infos[2]));
        return car;
    }

    @Override
    public Class<Car> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public String getCarInfo() {
        return this.carInfo;
    }

    // 接受逗号分割符设置属性信息
    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/applicationContext.xml"});
        // 正常情况
        Car car = (Car) context.getBean("car");
        System.out.println(car.getBrand());
        System.out.println(car.getMaxSpeed());
        System.out.println(car.getPrice());

        // 获取CarFactoryBean的实例
        CarFactoryBean carFactoryBean = (CarFactoryBean) context.getBean("&car");
        System.out.println(carFactoryBean.getCarInfo());
    }
}