package com.gjt.jdk8.stream;

public class Prod {
    private String name;
    private double price;
    private String type;
    private String desc;
    private int grade;
    
    public Prod(String name, String desc, String type, int grade, double price) {
        super();
        this.name = name;
        this.price = price;
        this.type = type;
        this.desc = desc;
        this.grade = grade;
    }

    public String getName() {
        System.out.println(" getName-"+name);
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    @Override
    public String toString() {
        return "Prod [name=" + name + ", price=" + price + ", type=" + type + ", desc=" + desc + ", grade=" + grade
                + "]";
    }
    
}
