package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {
	Comando c = new ComandoPosa();
	Labirinto l = new Labirinto();
	Partita p = new Partita(l);
	Attrezzo a = new Attrezzo("a", 1);
	
	@Before
	public void setUp() {
		this.p.getGiocatore().getBorsa().addAttrezzo(a);
		this.c.setParametro("a");
	}

	@Test
	public void testComandoPosa_borsaSvoutata() {
		this.c.esegui(this.p);
		assertTrue(this.p.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	public void testComandoPosa_borsaNonVuota() {
		this.p.getGiocatore().getBorsa().addAttrezzo(a);
		this.c.esegui(p);
		assertFalse(this.p.getGiocatore().getBorsa().isEmpty());
	}

}
