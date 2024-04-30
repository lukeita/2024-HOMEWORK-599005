package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {
	private Labirinto labirinto = new Labirinto();
	private Partita partita = new Partita(labirinto);
	private Stanza stanzaCasuale = new Stanza("casuale");
	
	@Test
	public void testGetStanzaCorrente_inizioPartita() {
		assertEquals(this.labirinto.getStanzaIniziale(), this.partita.getStanzaCorrente());
	}
	
	@Test
	public void testGetStanzaCorrente_stanzaCorrenteNuova() {
		this.partita.setStanzaCorrente(this.stanzaCasuale);
		assertEquals(this.stanzaCasuale, this.partita.getStanzaCorrente());
	}
	
	@Test
	public void testGetStanzaCorrente_stanzaInesistente() {
		this.partita.setStanzaCorrente(null);
		assertNull(this.partita.getStanzaCorrente());
	}

	@Test
	public void testVinta__inizioPartita() {
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testVinta_stanzaCorrente_diversaStanzaVincente() {
		this.partita.setStanzaCorrente(this.stanzaCasuale);
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testVinta_stanzaCorrente_ugualeStanzaVincente() {
		this.partita.setStanzaCorrente(this.labirinto.getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
}
