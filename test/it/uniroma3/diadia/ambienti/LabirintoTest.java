package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

public class LabirintoTest {
	private Labirinto labirinto = Labirinto.newBuilder().getLabirinto();
	
	@Test
	public void testGetStanzaIniziale_verificaEsistenzaStanza() {
		assertNull(this.labirinto.getStanzaIniziale());
	}
	
	public void testGetStanzaVincente_verificaEsistenzaStanza() {
		assertNull(this.labirinto.getStanzaVincente());
	}
}
