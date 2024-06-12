package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.ComandoInteragisci;
import it.uniroma3.diadia.comandi.ComandoRegala;
import it.uniroma3.diadia.comandi.ComandoSaluta;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

public class CaneTest {
	private Labirinto lab;
	private Partita test;
	private Giocatore player;
	private Borsa inventario;
	private AbstractComando comando;
	private IOSimulator simulator;

	@Before
	public void setUp() {
		lab = Labirinto.newBuilder()
				.addStanzaIniziale("entrata")
				.addCane("fido", "scatolame", "osso")
				.addStanzaVincente("uscita")
				.addAdiacenza("entrata", "uscita", "nord")
				.getLabirinto();
		test = new Partita(lab);
		player = test.getGiocatore();
		inventario = player.getBorsa();
		inventario.addAttrezzo(new Attrezzo("scatolame", 0));
		inventario.addAttrezzo(new Attrezzo("spazzatura", 0));
		simulator = new IOSimulator(new ArrayList<String>());
	}
	
	@Test
	public void testSalutaCane() {
		comando = new ComandoSaluta();
		comando.setIo(simulator);
		comando.esegui(test);	
		assertEquals("Ciao, io sono fido.Woof!", simulator.getOutput().get(0));
		comando.esegui(test);
		assertEquals("Ciao, io sono fido.Ci siamo gia' presentati!", simulator.getOutput().get(1));
	}
	
	@Test
	public void testInteragisciCane() {
		comando = new ComandoInteragisci();
		comando.setIo(simulator);
		comando.esegui(test);
		assertEquals("GRRRRRR WOOOF WOOOF!", simulator.getOutput().get(0));
		assertEquals(9, test.getGiocatore().getCfu());
	}
	
	@Test
	public void testRegalaCane() {
		comando = new ComandoRegala();
		comando.setIo(simulator);
		comando.setParametro("spazzatura");
		comando.esegui(test);
		assertEquals("Grrrrrrrrrr!", simulator.getOutput().get(0));
		assertFalse(test.getGiocatore().getBorsa().hasAttrezzo("spazzatura"));
		assertTrue(test.getStanzaCorrente().hasAttrezzo("spazzatura"));
		comando.setParametro("scatolame");
		comando.esegui(test);
		assertEquals("Arf Arf!", simulator.getOutput().get(1));
		assertTrue(test.getStanzaCorrente().hasAttrezzo("osso"));;
	}

}
