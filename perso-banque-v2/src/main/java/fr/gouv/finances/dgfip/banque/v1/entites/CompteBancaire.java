package fr.gouv.finances.dgfip.banque.v1.entites;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.validation.constraints.NotBlank;

import fr.gouv.finances.dgfip.banque.v1.CompteException;
import fr.gouv.finances.dgfip.banque.v1.Util;

public abstract class CompteBancaire {
	protected String codeBanque;
	@NotBlank
	protected String codeGuichet;
	protected String numCompte;
	protected String cle;
	protected Double solde;

//  // bloc d'initialisation
//  {
//	  this.codeBanque = "";
//  }

	public String getCodeBanque() {
		return codeBanque;
	}

	public void setCodeBanque(String codeBanque) {
		this.codeBanque = codeBanque;
	}

	public String getCodeGuichet() {
		return codeGuichet;
	}

	public void setCodeGuichet(String codeGuichet) {
		this.codeGuichet = codeGuichet;
	}

	public String getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}

	public String getCle() {
		return cle;
	}

	public void setCle(String cle) {
		this.cle = cle;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public CompteBancaire(String codeBanque, String codeGuichet, String numCompte, String cle, Double solde) {
		this.codeBanque = codeBanque;
		this.codeGuichet = codeGuichet;
		this.numCompte = numCompte;
		this.cle = cle;
		this.solde = solde;
	}

	public CompteBancaire() {

	}

	public abstract Double calculerSolde();	

	public String getRib() {
		return String.format("%s %s %s %s", this.codeBanque, this.codeGuichet, this.numCompte, this.cle);
	}

	/********************************/
	private Banque banque;

	public Banque getBanque() {
		return banque;
	}

	public void setBanque(Banque banque) {
		this.banque = banque;
	}

}
