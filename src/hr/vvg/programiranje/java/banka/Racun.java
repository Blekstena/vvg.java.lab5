package hr.vvg.programiranje.java.banka;

import hr.vvg.programiranje.java.osoba.Osoba;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Racun {

	private Osoba vlasnikRacuna;
	private BigDecimal stanjeRacuna;

	// konstruktor
	public Racun(Osoba vlasnikRacuna, BigDecimal stanjeRacuna) {

		this.vlasnikRacuna = vlasnikRacuna;
		this.stanjeRacuna = stanjeRacuna;
	}

	// get metode
	public Osoba getVlasnikRacuna() {
		return vlasnikRacuna;
	}

	public BigDecimal getStanjeRacuna() {
		return stanjeRacuna;
	}

	// metode za iznose uplata
	public void uplatiNaRacun(BigDecimal iznosZaPrenos) {
		stanjeRacuna = stanjeRacuna.add(iznosZaPrenos);

	}

	public void isplatiSRacuna(BigDecimal iznosZaPrenos) {
		stanjeRacuna = stanjeRacuna.subtract(iznosZaPrenos);

	}

	public BigDecimal divide(BigDecimal tecajEurKn, int i, RoundingMode halfUp) {
		// TODO Auto-generated method stub
		return null;
	}

}
