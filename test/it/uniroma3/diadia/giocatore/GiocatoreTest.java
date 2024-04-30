package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Test;

public class GiocatoreTest {
	private Giocatore giocatore = new Giocatore();

	@Test
	public void testGetCfu_cfuIniziali() {
		assertEquals(20, this.giocatore.getCfu());
	}
	
	@Test
	public void testGetCfu_cfuModificati() {
		giocatore.setCfu(0);
		assertEquals(0, this.giocatore.getCfu());
	}
}
