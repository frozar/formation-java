package fr.gouv.finances.dgfip.banque.v1;

public class CompteException extends Exception {
  private static final long serialVersionUID = 4956588939715236834L;

  public CompteException(String errorMessage) {
      super(errorMessage);
  }
}
