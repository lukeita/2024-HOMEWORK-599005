package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.*;

public class ComandoVaiTest {
	private IO io = new IOConsole();
	private Labirinto l = new Labirinto();
	private Partita p = new Partita(l);
	private Comando c = new ComandoVai();
	
	@Before
	public void setUp() {
		c.setIo(io);
	}

	@Test
	public void testEsegui_direzioneNulla() {
		c.esegui(p);
		assertEquals("Atrio", p.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testEsegui_direzioneNonNulla() {
		c.setParametro("nord");
		c.esegui(p);
		assertEquals("Biblioteca", p.getStanzaCorrente().getNome());
	}

}
