package hr.vvg.programiranje.java.banka;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Tecajnica {

	public static List<Tecaj> dohvatiTecajeve(){
		
		List<Tecaj> listaValuta = new ArrayList<>();
		
		Tecaj euro = new Tecaj(Valuta.EUR, new BigDecimal("7.61"));
		Tecaj funta = new Tecaj(Valuta.GBP, new BigDecimal("8.96"));
		Tecaj dolar = new Tecaj(Valuta.USD, new BigDecimal("5.83"));
		
		listaValuta.add(euro);
		listaValuta.add(funta);
		listaValuta.add(dolar);
		
		return listaValuta;
		
	}
	
}
