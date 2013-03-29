package hr.vvg.programiranje.java.banka;

import hr.vvg.programiranje.java.osoba.Osoba;

import java.math.BigDecimal;

public class TekuciRacun extends Racun {
	
	private String brojRacuna;
	
	public TekuciRacun (Osoba vlasnikRacuna, BigDecimal stanjeRacuna, String brojRacuna){
		super(vlasnikRacuna, stanjeRacuna);
		this.brojRacuna = brojRacuna;
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}

}
