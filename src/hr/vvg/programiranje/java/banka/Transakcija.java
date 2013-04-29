package hr.vvg.programiranje.java.banka;

import hr.vvg.programiranje.java.iznimke.NedozvoljenoStanjeRacunaException;

import java.math.BigDecimal;

public class Transakcija<T extends Racun, S extends Racun> {

	protected T polazniRacun;
	protected S dolazniRacun;
	BigDecimal iznosZaPrebaciti;

	// konstruktor
	public Transakcija(T polazniRacun, S dolazniRacun,
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
