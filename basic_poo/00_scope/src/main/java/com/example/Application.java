package com.example;

import java.util.Arrays;
import java.util.List;

// As Parent class is in the same package "com.example", 
// d'ont need to import it
// import com.example.Parent;
import com.util.Essential;

public class Application {
  public static void main(String[] args) {
    System.out.println(Application.class);

    // Create an instance of Parent class
    Parent p = new Parent();
    System.out.println("Parent mood: " + p.mood);
    p.mood = "mauvais";
    System.out.println("New mood: " + p.mood);

    System.out.println(p.firstName);
    System.out.println(p.lastName);
    // As Parent.secret field is private, it cannot be used
    // outside of the Parent class
    // System.out.println(p.secret);

    // As Parent.firstName field is final, cannot modify it
    // p.firstName = "Laurent";

    p.firstName();
    p.lastName();

    // As Parent.secret() method is private, it cannot be called
    // outside of the Parent class
    // p.secret();

    // As Parent.age() method is a static method, doesn't need to be
    // instanciated to call the method
    Parent.age();

    Enfant e = new Enfant();
    e.firstName();
    e.lastName();

    Essential.hello();
    // As Essential.salut() is protected and is not the package com.example,
    // it cannot be called here
    // Essential.salut();

    new FinalParent().name();

    Cat c = new Cat();
    c.name();
    c.sound();
    System.out.println("Cat speed " + c.speed());
    System.out.println("Cat height " + c.height());

    Dog d = new Dog();
    d.name();
    d.sound();
    System.out.println("Dog speed " + d.speed());
    System.out.println("Dog height " + d.height());

    List<? extends AbstractAnimal> l = Arrays.asList(c, d);
    for (AbstractAnimal animal : l) {
      System.out.print("In for ");
      animal.sound();
    }
  }
}
