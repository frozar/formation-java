package fr.gouv.finances.dgfip.banque.v1;

public class SystemeBancaireException extends Exception {
  private static final long serialVersionUID = -6709396699756231789L;

  public SystemeBancaireException(String errorMessage) {
      super(errorMessage);
  }
}