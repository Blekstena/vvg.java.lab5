package hr.vvg.programiranje.java.banka;

import hr.vvg.programiranje.java.iznimke.NedozvoljenoStanjeRacunaException;

import java.math.BigDecimal;

public class Transakcija {

	protected Racun polazniRacun;
	protected Racun dolazniRacun;
	BigDecimal iznosZaPrebaciti;

	// konstruktor
	public Transakcija(Racun polazniRacun, Racun dolazniRacun,
			BigDecimal iznosZaPrebaciti) {

		this.polazniRacun = polazniRacun;
		this.dolazniRacun = dolazniRacun;
		this.iznosZaPrebaciti = iznosZaPrebaciti;
	}

	// get metode
	public Racun getPolazniRacun() {
		return polazniRacun;
	}

	public Racun getDolazniRacun() {
		return dolazniRacun;
	}

	public BigDecimal getIznosZaPrebaciti() {
		return iznosZaPrebaciti;
	}

	// metoda za transakciju
	public void provediTransakciju() throws NedozvoljenoStanjeRacunaException {
		polazniRacun.isplatiSRacuna(iznosZaPrebaciti);
		dolazniRacun.uplatiNaRacun(iznosZaPrebaciti);
	}

	public BigDecimal getIznosTransakcije() {
		return null;
	}

}
