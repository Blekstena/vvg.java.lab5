package hr.vvg.programiranje.java.glavna;

import hr.vvg.programiranje.java.banka.DeviznaTransakcija;
import hr.vvg.programiranje.java.banka.DevizniRacun;
import hr.vvg.programiranje.java.banka.TekuciRacun;
import hr.vvg.programiranje.java.banka.Transakcija;
import hr.vvg.programiranje.java.banka.Valuta;
import hr.vvg.programiranje.java.iznimke.NedozvoljenoStanjeRacunaException;
import hr.vvg.programiranje.java.iznimke.NepodrzanaValutaException;
import hr.vvg.programiranje.java.osoba.Osoba;
import hr.vvg.programiranje.java.sortirtanje.SortiranjeTransakcija;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Glavna {

	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

	public static void main(String[] args) {

		Scanner unos = new Scanner(System.in);

		// podaci o prvom korisniku

		System.out.print("Unesi ime prvog korisnika racuna: ");
		String ime1 = unos.next();
		logger.info("Uneseno ime vlasnika prvog raèuna: " + ime1);

		System.out.print("Unesi prezime prvog korisnika racuna: ");
		String prezime1 = unos.next();
		logger.info("Uneseno prezime vlasnika prvog raèuna: " + prezime1);

		System.out.print("Unesi OIB prvog korisnika racuna: ");
		String oib1 = unos.next();
		logger.info("Unesen OIB vlasnika prvog raèuna: " + oib1);

		Osoba vlasnikRacuna1 = new Osoba(ime1, prezime1, oib1);

		// unos podataka o racunu prvog korisnika

		System.out.print("Unesi broj prvog racuna: ");
		String brojRacuna1 = unos.next();
		logger.info("Unesen broj vlasnika prvog raèuna: " + brojRacuna1);

		boolean error = false;
		BigDecimal stanjeRacuna1 = null;

		do {
			error = false;
			System.out.print("Unesi stanje prvog raèuna: ");
			try {
				stanjeRacuna1 = unos.nextBigDecimal();
				logger.info("Uneseno stanje prvog raèuna: " + stanjeRacuna1);
			} catch (InputMismatchException ex) {
				error = true;
				logger.error("Unesen neispravan iznos za stanje prvog raèuna!"
						+ stanjeRacuna1, ex);
				unos.nextLine();

			}

		} while (error == true);

		TekuciRacun polazniRacun = new TekuciRacun(vlasnikRacuna1,
				stanjeRacuna1, brojRacuna1);

		// podaci o drugom korisniku

		System.out.print("Unesi ime drugog korisnika racuna: ");
		String ime2 = unos.next();
		logger.info("Uneseno ime korisnika drugog raèuna: " + ime2);

		System.out.print("Unesi prezime drugog korisnika racuna: ");
		String prezime2 = unos.next();
		logger.info("Uneseno prezime korisnika drugog raèuna: " + prezime2);

		System.out.print("Unesi OIB drugog korisnika racuna: ");
		String oib2 = unos.next();
		logger.info("Unesen OIB korisnika drugog raèuna: " + oib2);

		Osoba vlasnikRacuna2 = new Osoba(ime2, prezime2, oib2);

		// podavi o racunu drugog korisnika

		System.out.print("Unesi broj drugog racuna: ");
		String brojRacuna2 = unos.next();
		logger.info("Unesen broj drugog raèuna: " + brojRacuna2);

		BigDecimal stanjeRacuna2 = null;
		do {
			error = false;
			System.out.print("Unesi stanje drugog raèuna: ");
			try {
				stanjeRacuna2 = unos.nextBigDecimal();
				logger.info("Uneseno stanje drugog raèuna: " + stanjeRacuna2);
			} catch (InputMismatchException ex) {
				error = true;
				logger.error("Unesen neispravan iznos za stanje drugog raèuna!"
						+ stanjeRacuna2, ex);
				unos.nextLine();

			}
		} while (error == true);

		System.out.print("Unesi iBan racuna: ");
		String iban = unos.next();
		logger.info("Unesen iBan drugog raèuna: " + iban);

		// valuta

		String valuta = null;
		Valuta valutaDeviznogRacuna = null;
		do {
			error = false;
			System.out.print("Unesite valutu drugog raèuna:");
			try {
				valuta = unos.next();
				valutaDeviznogRacuna = DeviznaTransakcija
						.provjeriValutu(valuta);

			} catch (NepodrzanaValutaException ex) {
				error = true;
				String message = "Unešena je krivo valuta racuna, ";
				System.out.printf(message + valuta
						+ " valuta nije podržana. \n", ex); // // problem s
															// ispisom je ok
															// ovdje ali me pati
															// ex
				logger.error(message);
				unos.nextLine();
			}

		} while (error == true);

		DevizniRacun dolazniRacun = new DevizniRacun(vlasnikRacuna2,
				stanjeRacuna2, iban, valutaDeviznogRacuna, brojRacuna2);

		System.out.print("Unesi iznos za prebaciti sa prvog na drugi racun: ");
		BigDecimal iznosZaPrebaciti = unos.nextBigDecimal();
		logger.info("Unesen iznos transakcije: " + iznosZaPrebaciti);
		// Transakcija transakcija = new DeviznaTransakcija(polazniRacun,
		// dolazniRacun, iznosZaPrebaciti);

		SortedSet<Transakcija> setTransakcija = new TreeSet<Transakcija>(
				new SortiranjeTransakcija());

		/*
		 * do { try { transakcija.provediTransakciju(); String message =
		 * "Uspješna transakcija."; System.out.println(message);
		 * logger.info(message); } catch (NedozvoljenoStanjeRacunaException ex)
		 * { System.out.println(ex.getMessage()); logger.error(ex.getMessage(),
		 * ex); } } while (error == true);
		 */
		boolean ponavljam = false;
		do {
			ponavljam = false;

			DeviznaTransakcija transakcija = new DeviznaTransakcija(
					polazniRacun, dolazniRacun, iznosZaPrebaciti);
			try {
				transakcija.provediTransakciju();
				setTransakcija.add(transakcija);
			} catch (NedozvoljenoStanjeRacunaException ex) {
				String message = "Transakcija nije provedena!!!!";
				System.out.println(message);
				logger.error(message, ex);
			}

			System.out.println("Želite li novu transakciju (D/N)?");
			String odgovor = unos.next();

			if (odgovor.equals("D")) {
				ponavljam = true;
		}
			System.out.println("Unesi iznos novi za prebaciti sa prvog na drugi racun: ");
		} while (ponavljam == true);

		BigDecimal iznosNajveceTran = setTransakcija.first()
				.getIznosTransakcije();

		System.out.println("Iznos najviše transakcije je: " + iznosNajveceTran);

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