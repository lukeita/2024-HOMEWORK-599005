package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

public class GiocatoreTest {
	private Giocatore giocatore;

	@Before
	public void setUp() {
		this.giocatore = new Giocatore();
	}

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
