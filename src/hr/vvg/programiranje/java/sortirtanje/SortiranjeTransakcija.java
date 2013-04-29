package hr.vvg.programiranje.java.sortirtanje;

import hr.vvg.programiranje.java.banka.Transakcija;

import java.util.Comparator;

public class SortiranjeTransakcija implements Comparator<Transakcija> {
	
	@Override
public int compare(Transakcija transakcija1, Transakcija transakcija2) {
return transakcija1.getIznosZaPrebaciti().compareTo(transakcija2.getIznosZaPrebaciti()) * (-1);
}

}
