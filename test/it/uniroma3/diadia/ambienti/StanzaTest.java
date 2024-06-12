package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	private Stanza stanza = new Stanza("stanza");
	private Stanza adiacente = new Stanza("adiacente");
	private Attrezzo primoAttrezzo = new Attrezzo("primo", 0);
	private Attrezzo secondoAttrezzo = new Attrezzo("secondo", 0);
	
	@Test
	public void testAddAttrezzoSingolo() {
		assertTrue(stanza.addAttrezzo(primoAttrezzo));
	}
	
	@Test
	public void testAddAttrezzoDuplicato() {
		stanza.addAttrezzo(primoAttrezzo);
		assertFalse(stanza.addAttrezzo(primoAttrezzo));
	}

	@Test
	public void testHasAttrezzo_stanzaVuota() {
		assertFalse(stanza.hasAttrezzo("inesistente"));
	}

	@Test
	public void testHasAttrezzo_stanzaNonVuota_assente() {
		assertFalse(stanza.hasAttrezzo("inesistente"));
	}

	@Test
	public void testHasAttrezzo_stanzaNonVuota_presente() {
		stanza.addAttrezzo(primoAttrezzo);
		assertTrue(stanza.hasAttrezzo("primo"));
	}

	@Test
	public void testGetAttrezzo_stanzaVuota() {
		assertNull(stanza.getAttrezzo("inesistente"));
	}
	
	@Test
	public void testGetAttrezzo_stanzaNonVuota_assente() {
		assertNull(stanza.getAttrezzo("non presente"));
	}	
	
	@Test 
	public void testGetAttrezzo_stanzaNonVuota_presente() {
		stanza.addAttrezzo(primoAttrezzo);
		assertSame(primoAttrezzo,stanza.getAttrezzo("primo"));
	}
	
	@Test
	public void testRemoveAttrezzo_stanzaVuota() {
		assertFalse(stanza.removeAttrezzo("inesistente"));
	}
	
	@Test
	public void testRemoveAttrezzo_stanzaNonVuota_assente() {
		stanza.addAttrezzo(primoAttrezzo);
		assertFalse(stanza.removeAttrezzo("sbagliato"));
	}
	
	@Test 
	public void testRemoveAttrezzo_stanzaNonVuota_presente() {
		stanza.addAttrezzo(primoAttrezzo);
		stanza.removeAttrezzo("primo");
		assertNull(stanza.getAttrezzo("primo"));
	}
	
	@Test
	public void testRemoveAttrezzo_stanzaNonVuota_dueAttrezzi_rimuoviIlPrimo() {
		stanza.addAttrezzo(primoAttrezzo);
		stanza.addAttrezzo(secondoAttrezzo);
		stanza.removeAttrezzo("primo");
		assertNull(stanza.getAttrezzo("primo"));
	}
	
	@Test
	public void testRemoveAttrezzo_stanzaNonVuota_dueAttrezzi_rimuoviIlPrimo_senzaRimuovereilSecondo() {
		stanza.addAttrezzo(primoAttrezzo);
		stanza.addAttrezzo(secondoAttrezzo);
		stanza.removeAttrezzo("primo");
		assertEquals(secondoAttrezzo ,stanza.getAttrezzo("secondo"));
	}
	
	@Test
	public void testGetStanzaAdiacente_stanzaScollegata() {
		assertNull(stanza.getStanzaAdiacente("inesistente"));
	}
	
	@Test
	public void testGetStanzaAdiacente_stanzaCollegata() {
		stanza.impostaStanzaAdiacente("nord", adiacente);
		assertSame(adiacente,stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetDirezioni_conStanzaAdiacente() {
		stanza.impostaStanzaAdiacente("nord", adiacente);
		assertTrue(stanza.getDirezioni().contains(Direzione.valueOf("nord")));
	}
}
