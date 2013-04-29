package hr.vvg.programiranje.java.banka;

import hr.vvg.programiranje.java.osoba.Osoba;

import java.math.BigDecimal;

public class DevizniRacun extends Racun {
	
	private String iban;
	private Valuta valuta;
	private String brojRacuna;
	
	public DevizniRacun(Osoba vlasnikRacuna, BigDecimal stanjeRacuna, String iban, Valuta valuta, String brojRacuna){
		super(vlasnikRacuna, stanjeRacuna);
		this.iban = iban;
		this.valuta = valuta;
		this.brojRacuna = brojRacuna;
		
	}



	public String getIban() {
		return iban;
	}

	public Valuta getValuta() {
		return valuta;
	}



	public String getBrojRacuna() {
		return brojRacuna;
	}

}
