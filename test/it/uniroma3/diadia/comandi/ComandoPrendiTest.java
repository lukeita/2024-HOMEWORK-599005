package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class ComandoPrendiTest {
	private Comando vai = new ComandoVai();
	private Comando prendi = new ComandoPrendi();
	private Labirinto bilocale;
	private Partita p;
	@Before
	public void setUp() throws Exception {
		vai.setIo(new IOConsole());
		prendi.setIo(new IOConsole());
		
		this.bilocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto",10)
				.addAdiacenza("salotto", "camera", "nord")
				.getLabirinto(); 
		
		this.p = new Partita(this.bilocale);
	}

	@Test
	public void testPrendi_nessunOggetto() {
		prendi.esegui(p);
		assertTrue(p.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	public void testPrendi_oggettoEsistente() {
		vai.setParametro("nord");
		vai.esegui(p);
		prendi.setParametro("letto");
		prendi.esegui(p);
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo("letto"));
	}
}
