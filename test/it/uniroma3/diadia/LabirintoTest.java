package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

public class LabirintoTest {
	private Labirinto labirinto;

	@Before
	public void setUp() {
		this.labirinto = new Labirinto();
		}

	@Test
	public void testGetStanzaIniziale_verificaEsistenzaStanza() {
		assertNotNull(this.labirinto.getStanzaIniziale());
	}
	
	public void testGetStanzaVincente_verificaEsistenzaStanza() {
		assertNotNull(this.labirinto.getStanzaVincente());
	}
}
