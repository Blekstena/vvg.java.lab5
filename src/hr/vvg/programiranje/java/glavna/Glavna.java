package hr.vvg.programiranje.java.glavna;

import hr.vvg.programiranje.java.banka.DeviznaTransakcija;
import hr.vvg.programiranje.java.banka.DevizniRacun;
import hr.vvg.programiranje.java.banka.TekuciRacun;
import hr.vvg.programiranje.java.iznimke.NedozvoljenoStanjeRacunaException;
import hr.vvg.programiranje.java.iznimke.NepodrzanaValutaException;
import hr.vvg.programiranje.java.osoba.Osoba;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Glavna {

	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

	public static void main(String[] args) {

		Scanner unos = new Scanner(System.in);
		
		//podaci o prvom korisniku
		
		System.out.print("Unesi ime prvog korisnika racuna: ");
		String ime1 = unos.next();
		logger.info("Uneseno ime vlasnika prvog raèuna: " + ime1);
		System.out.print("Unesi prezime prvog korisnika racuna: ");
		String prezime1 = unos.next();
		System.out.print("Unesi OIB prvog korisnika racuna: ");
		String oib1 = unos.next();
		Osoba vlasnikRacuna1 = new Osoba(ime1, prezime1, oib1);
		
		
		//unos podataka o racunu prvog korisnika
		
		System.out.print("Unesi broj prvog racuna: ");
		String brojRacuna1 = unos.next();
		boolean error = false;
		BigDecimal stanjeRacuna1 = null;
		do {
			error = false;
			System.out.print("Unesi stanje prvog raèuna: ");
			try {
				stanjeRacuna1 = unos.nextBigDecimal();
			} catch (InputMismatchException ex) {
				error = true;
				logger.error("Unesen neispravan iznos za stanje prvog raèuna!" + stanjeRacuna1, ex);

			}
		} while (error = true);
		TekuciRacun polazniRacun = new TekuciRacun(vlasnikRacuna1,
				stanjeRacuna1, brojRacuna1);
		
		//podaci o drugom korisniku
		
		System.out.print("Unesi ime drugog korisnika racuna: ");
		String ime2 = unos.next();
		System.out.print("Unesi prezime drugog korisnika racuna: ");
		String prezime2 = unos.next();
		System.out.print("Unesi OIB drugog korisnika racuna: ");
		String oib2 = unos.next();
		Osoba vlasnikRacuna2 = new Osoba(ime2, prezime2, oib2);
		
		//podavi o racunu drugog korisnika
		
		System.out.print("Unesi broj drugog racuna: ");
		String brojRacuna2 = unos.next();
		
		BigDecimal stanjeRacuna2 = null;
		do {
			error = false;
			System.out.print("Unesi stanje drugog raèuna: ");
			try {
				stanjeRacuna2 = unos.nextBigDecimal();
			} catch (InputMismatchException ex) {
				error = true;
				logger.error("Unesen neispravan iznos za stanje prvog raèuna!" + stanjeRacuna2, ex);

			}
		} while (error = true);
		
		System.out.print("Unesi iBan racuna: ");
		String iban = unos.next();
		
		
		//valuta
		
		String valuta;
		do {
			error = false;
			System.out.print("Unesi valutu drugog racuna: ");
			try {
				valuta = unos.next();
			} catch (NepodrzanaValutaException ex) {
				error = true;
				logger.error("Unesena pogresna valuta! Unesite ponovno. " + valuta, ex);

			}
		} while (error = true);
		

		
		
		
		DevizniRacun dolazniRacun = new DevizniRacun(vlasnikRacuna2,
				stanjeRacuna2, iban, valuta, brojRacuna2);

		System.out.print("Unesi iznos za prebaciti sa prvog na drugi racun: ");
		BigDecimal iznosZaPrebaciti = unos.nextBigDecimal();
		DeviznaTransakcija transakcija = new DeviznaTransakcija(polazniRacun,
				dolazniRacun, iznosZaPrebaciti);
		
		
		do{
			error = false;
			try{
			transakcija.provediTransakciju();
			}
			catch (NedozvoljenoStanjeRacunaException ex) {
				error = true;
				// malo l je a ne veliko L
				// veliko znaci da zoves staticku metodu u Logger klasi (a ta metoda ne postoji, tj nije static)
				Logger.error("Nedovoljno sredstava na raèunu za prebacivanje zeljenog iznosa!" + ex);
			}
		} while (error = true);
		unos.close();

		System.out.println("Vlasnik prvog racuna:\nime:  "
				+ polazniRacun.getVlasnikRacuna().getIme() + "\nprezime:  "
				+ polazniRacun.getVlasnikRacuna().getPrezime() + "\noib:  "
				+ polazniRacun.getVlasnikRacuna().getOib() + "\nbroj raèuna: "
				+ polazniRacun.getBrojRacuna()
				+ "\nstanje na racunu nakon transakcije: "
				+ polazniRacun.getStanjeRacuna());

		System.out.println("Vlasnik drugog racuna:\nime: "
				+ dolazniRacun.getVlasnikRacuna().getIme() + ";\nprezime:  "
				+ dolazniRacun.getVlasnikRacuna().getPrezime() + ";\noib:  "
				+ dolazniRacun.getVlasnikRacuna().getOib() + ";\nbroj raèuna: "
				+ dolazniRacun.getBrojRacuna()
				+ ";\nstanje na racunu nakon transakcije: "
				+ dolazniRacun.getStanjeRacuna() + dolazniRacun.getValuta());
	}
}