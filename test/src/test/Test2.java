package test;

abstract class Person {
    public abstract void eat();
}
 
public class Test2 {
    public static void main(String[] args) {
        Person p = new Person() {
            public void eat() {
               Integer a = 1000;
            }
        };
        p.eat();
    }
}
