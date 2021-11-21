package com.example.demo.models;

import java.io.Serializable;

public class RegisterModel implements Serializable {

  private static final long serialVersionUID = 1L;

  private PersonalInfo personalInfo;
  private BillingInfo billingInfo;

  public PersonalInfo getPersonalInfo() {
    return personalInfo;
  }

  public void setPersonalInfo(PersonalInfo personalInfo) {
    System.err.println("setPersonalInfo");
    System.err.println("personalInfo.getImageContent(): "
        + (personalInfo.getImageContent() == null));
    this.personalInfo = personalInfo;
  }

  public BillingInfo getBillingInfo() {
    return billingInfo;
  }

  public void setBillingInfo(BillingInfo billingInfo) {
    this.billingInfo = billingInfo;
  }
}