package hr.vvg.programiranje.java.banka;

import java.math.BigDecimal;

public interface Devizna {
	
	BigDecimal mjenjacnica(BigDecimal polazniIznosKN, Valuta valuta);

}
