package hr.vvg.programiranje.java.banka;

import hr.vvg.programiranje.java.iznimke.NedozvoljenoStanjeRacunaException;
import hr.vvg.programiranje.java.iznimke.NepodrzanaValutaException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DeviznaTransakcija<T extends TekuciRacun, S extends DevizniRacun>
		extends Transakcija<T, S> implements Devizna {

	// private static final BigDecimal TECAJ_EUR_KN = new BigDecimal(7.5);
	// private static final String PODRZANA_VALUTA = "Euro";

	// mora ti biti public da ju mozes pozvat u glavnoj klasi

	public DeviznaTransakcija(T polazniRacun,
			S dolazniRacun, BigDecimal iznosZaPrebaciti) {
		super(polazniRacun, dolazniRacun, iznosZaPrebaciti);
	}

	public static Valuta provjeriValutu(String valuta)
			throws NepodrzanaValutaException {
		try {
			return Valuta.valueOf(valuta);
		} catch (IllegalArgumentException ex) {
			throw new NepodrzanaValutaException("Valuta " + valuta
					+ " nije podr�ana!", ex);
		}
	}

	public BigDecimal mjenjacnica(BigDecimal polazniIznosKN, Valuta valuta) {
		for (Tecaj tecaj : Tecajnica.dohvatiTecajeve()) {
			if (tecaj.getValuta().compareTo(valuta) == 0) {
				BigDecimal iznos = polazniIznosKN.divide(
						tecaj.getTecajPremaKuni(), 2, RoundingMode.HALF_UP);
				return iznos;
			}
		}
		return polazniIznosKN;
	}

	@Override
	public void provediTransakciju() throws NedozvoljenoStanjeRacunaException {

		if (polazniRacun.getStanjeRacuna().compareTo(super.iznosZaPrebaciti) == -1) {

			throw new NedozvoljenoStanjeRacunaException(
					"Nedovoljno sredstava na ra�unu :"
							+ polazniRacun.getStanjeRacuna()
							+ "; unesite ponovno iznos."
							+ super.iznosZaPrebaciti);
		} else {
			polazniRacun.isplatiSRacuna(super.iznosZaPrebaciti);
			BigDecimal konvertiraniIznos = mjenjacnica(super.iznosZaPrebaciti,
					((DevizniRacun) dolazniRacun).getValuta());
			dolazniRacun.uplatiNaRacun(konvertiraniIznos);
		}
	}

}