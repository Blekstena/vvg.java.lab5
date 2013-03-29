package hr.vvg.programiranje.java.banka;

import hr.vvg.programiranje.java.iznimke.NedozvoljenoStanjeRacunaException;
import hr.vvg.programiranje.java.iznimke.NepodrzanaValutaException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DeviznaTransakcija extends Transakcija implements Devizna {

	private static final BigDecimal TECAJ_EUR_KN = new BigDecimal(7.5);
	private static final String Podrzana_Valuta = "Euro";

	private static void provjeriValutu(String valuta)
			throws NepodrzanaValutaException {
		if (Podrzana_Valuta.equals(valuta)) {
			throw new NepodrzanaValutaException("unijeli ste valutu " + valuta
					+ "koja nije podrzana.");
		}
	}

	public DeviznaTransakcija(TekuciRacun polazniRacun,
			DevizniRacun dolazniRacun, BigDecimal iznosZaPrebaciti) {
		super(polazniRacun, dolazniRacun, iznosZaPrebaciti);
	}

	public BigDecimal mjenjacnica(BigDecimal iznosZaPrebaciti, String valuta) {

		if ("EURO".equals(valuta) || "euro".equals(valuta)) {
			BigDecimal iznos = iznosZaPrebaciti.divide(TECAJ_EUR_KN, 2,
					RoundingMode.HALF_UP);
			return iznos;
		} else
			return iznosZaPrebaciti;
	}
	
	public void provediTransakciju() {

		if (polazniRacun.getStanjeRacuna().compareTo(super.iznosZaPrebaciti) == -1) {
			throw new NedozvoljenoStanjeRacunaException(
					"Nedovoljno sredstava na raèunu :"
							+ polazniRacun.getStanjeRacuna()
							+ "; unesite ponovno iznos."
							+ super.iznosZaPrebaciti);
		}else {
		polazniRacun.isplatiSRacuna(super.iznosZaPrebaciti);
		BigDecimal konvertiraniIznos = mjenjacnica(super.iznosZaPrebaciti,
				((DevizniRacun) dolazniRacun).getValuta());
		dolazniRacun.uplatiNaRacun(konvertiraniIznos);
		}
	}

}