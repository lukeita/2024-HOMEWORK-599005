package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	StanzaBuia s = new StanzaBuia("s", "lanterna");
	Attrezzo luce = new Attrezzo("lanterna", 1);

	@Test
	public void testStanzaBuia() {
		assertEquals("qui c'è buio pesto", s.getDescrizione());
	}
	
	@Test
	public void testStanzaIlluminata(){
		s.addAttrezzo(luce);
		assertNotEquals("qui c'è buio pesto", s.getDescrizione());
	}

}
