package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

public class LabirintoTest {
	private Labirinto labirinto = new Labirinto();
	
	@Test
	public void testGetStanzaIniziale_verificaEsistenzaStanza() {
		assertNotNull(this.labirinto.getStanzaIniziale());
	}
	
	public void testGetStanzaVincente_verificaEsistenzaStanza() {
		assertNotNull(this.labirinto.getStanzaVincente());
	}
}
