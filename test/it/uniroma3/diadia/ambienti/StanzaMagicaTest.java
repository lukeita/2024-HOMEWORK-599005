package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {
	private StanzaMagica s = new StanzaMagica("magica");
	private Attrezzo a = new Attrezzo("at", 1);

	@Test
	public void testGetContatoreAttrezziPosati_stanzaVuota_nessunOggettoPosato() {
		assertEquals(0, s.getContatoreAttrezziPosati());
	}

	@Test
	public void testGetContatoreAttrezziPosati_stanzaVuota_OggettoPosato() {
		s.addAttrezzo(a);
		assertEquals(1, s.getContatoreAttrezziPosati());
	}

	@Test
	public void testGetContatoreAttrezziPosati_stanzaVuota_OggettoPosatoRimosso() {
		s.addAttrezzo(a);
		s.removeAttrezzo("at");
		assertEquals(1, s.getContatoreAttrezziPosati());
	}

	@Test
	public void testAddAttrezzo_sottoSogliaMagica_nome() {
		s.addAttrezzo(a);
		assertEquals("at", s.getAttrezzo("at").getNome());
	}

	@Test
	public void testAddAttrezzo_sottoSogliaMagica_peso() {
		s.addAttrezzo(a);
		assertEquals(1, s.getAttrezzo("at").getPeso());
	}

	@Test
	public void testAddAttrezzo_sopraSogliaMagica_nome() {
		for(int i=0; i<3; i++) {
			s.addAttrezzo(a);
			s.removeAttrezzo("at");
		}
		s.addAttrezzo(a);
		assertEquals("ta", s.getAttrezzo("ta").getNome());
	}
	
	@Test
	public void testAddAttrezzo_sopraSogliaMagica_peso() {
		for(int i=0; i<3; i++) {
			s.addAttrezzo(a);
			s.removeAttrezzo("at");
		}
		s.addAttrezzo(a);
		assertEquals(2, s.getAttrezzo("ta").getPeso());
	}

}
