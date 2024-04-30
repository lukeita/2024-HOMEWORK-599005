package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	private StanzaBloccata s = new StanzaBloccata("s", "nord", "chiave");
	private Attrezzo chiave = new Attrezzo("chiave", 0);

	@Test
	public void testStanzaBloccata() {
		assertEquals(s, s.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testStanzaSbloccata() {
		s.addAttrezzo(chiave);
		assertNull(s.getStanzaAdiacente("nord"));
	}

}
