package be.butskri.automail;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

public class KlantnummerTest {

	@Test
	public void isGeldigGeeftTrueIndienGeldig() {
		assertTrue(Klantnummer.isGeldig("12 - 12345"));
		assertTrue(Klantnummer.isGeldig("34 - 1234"));
		assertTrue(Klantnummer.isGeldig("56 - 123"));
		assertTrue(Klantnummer.isGeldig("78 - 12"));
		assertTrue(Klantnummer.isGeldig("90 - 1"));
		assertTrue(Klantnummer.isGeldig("12-12345"));
		assertTrue(Klantnummer.isGeldig("34-1234"));
		assertTrue(Klantnummer.isGeldig("56-123"));
		assertTrue(Klantnummer.isGeldig("78-12"));
		assertTrue(Klantnummer.isGeldig("90-1"));
	}

	@Test
	public void isGeldigGeeftFalseIndienNietGeldig() {
		assertFalse(Klantnummer.isGeldig("1 - 12345"));
		assertFalse(Klantnummer.isGeldig("34 - 123456"));
		assertFalse(Klantnummer.isGeldig("a6 - 123"));
		assertFalse(Klantnummer.isGeldig("78 - b2"));
	}

	@Test
	public void getFormattedHaaltSpatiesWeg() {
		Klantnummer klantnummer = new Klantnummer("60 - 220");
		assertEquals("60-220", klantnummer.getFormatted());
	}

	@Test
	public void getGebruikersnaamZet1VooraanEersteGetalEnLaatsteGetalAanvullenMetNullenTot5Cijfers() {
		Klantnummer klantnummer = new Klantnummer("60 - 220");
		assertEquals("160-00220", klantnummer.getGebruikersnaam());
	}

	@Test
	public void getGebruikersnaamZet1VooraanEersteGetalEnLaatsteGetalAanvullenMetNullenTot5Cijfers2() {
		Klantnummer klantnummer = new Klantnummer("60 - 22");
		assertEquals("160-00022", klantnummer.getGebruikersnaam());
	}
}
