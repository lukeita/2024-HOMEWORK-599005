package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {
	Comando posa = new ComandoPosa();
	Labirinto monolocale;
	Partita p;
	
	@Before
	public void setUp() {
		this.posa.setIo(new IOConsole());
		this.monolocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("salotto")
				.getLabirinto();
		this.p = new Partita(this.monolocale);
	}

	@Test
	public void testPosa_borsaVuota() {
		this.posa.esegui(this.p);
		assertTrue(this.p.getGiocatore().getBorsa().isEmpty());
		assertTrue(this.p.getStanzaCorrente().isEmpty());
	}
	
	@Test
	public void testComandoPosa_borsaNonVuota() {
		this.p.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("martello"));
		this.posa.setParametro("martello");
		this.posa.esegui(p);
		assertFalse(this.p.getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertTrue(this.p.getStanzaCorrente().hasAttrezzo("martello"));
	}

}
