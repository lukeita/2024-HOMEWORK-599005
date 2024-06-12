package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

public class ComandoPrendiTest {
	private AbstractComando vai = new ComandoVai();
	private AbstractComando prendi = new ComandoPrendi();
	private Labirinto bilocale;
	private Partita p;
	@Before
	public void setUp() throws Exception {
		vai.setIo(new IOConsole(new Scanner(System.in)));
		prendi.setIo(new IOConsole(new Scanner(System.in)));
		
		this.bilocale = Labirinto.newBuilder()
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
