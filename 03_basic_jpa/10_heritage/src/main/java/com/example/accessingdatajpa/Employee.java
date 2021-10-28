package com.example.accessingdatajpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//// 1 : JOINED
//@Entity
//@DiscriminatorValue("EMP")
//// 2 : TABLE_PER_CLASS
//@Entity
//3 : SINGLE_TABLE
@Entity
public class Employee extends User {

//// 1 : JOINED
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//// 2 : TABLE_PER_CLASS
//  @Id
//  @GeneratedValue(strategy = GenerationType.TABLE)
//3 : SINGLE_TABLE
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int salary;

  public Employee() {
    super();
    this.salary = 0;
  }

  public Employee(String nom, int salary) {
    super(nom);
    this.salary = salary;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  @Override
  public String toString() {
    return "Employee [salary=" + salary + "]";
  }

}
