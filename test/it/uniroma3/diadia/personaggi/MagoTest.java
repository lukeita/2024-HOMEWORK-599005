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

public class MagoTest {

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
				.addMago("Merlino", "bacchetta", 1)
				.addStanzaVincente("uscita")
				.addAdiacenza("entrata", "uscita", "nord")
				.getLabirinto();
		test = new Partita(lab);
		player = test.getGiocatore();
		inventario = player.getBorsa();
		inventario.addAttrezzo(new Attrezzo("spazzatura", 4));
		simulator = new IOSimulator(new ArrayList<String>());
	}
	
	@Test
	public void testSalutaMago() {
		comando = new ComandoSaluta();
		comando.setIo(simulator);
		comando.esegui(test);	
		assertEquals("Ciao, io sono Merlino.Salve a te, giovane avventuriero", simulator.getOutput().get(0));
		comando.esegui(test);
		assertEquals("Ciao, io sono Merlino.Ci siamo gia' presentati!", simulator.getOutput().get(1));
	}
	
	@Test
	public void testInteragisciMago() {
		comando = new ComandoInteragisci();
		comando.setIo(simulator);
		comando.esegui(test);
		assertEquals("Sei un vero simpaticone, con una mia magica azione, troverai un nuovo oggetto per il tuo borsone!", simulator.getOutput().get(0));
		assertTrue(test.getStanzaCorrente().hasAttrezzo("bacchetta"));
		comando.esegui(test);
		assertEquals("Mi spiace, ma non ho piu' nulla...", simulator.getOutput().get(1));
	}
	
	@Test
	public void testRegalaMago() {
		comando = new ComandoRegala();
		comando.setIo(simulator);
		comando.setParametro("spazzatura");
		comando.esegui(test);
		assertEquals("Mmmm questo oggetto è molto pesante, ecco qua adesso è più leggero!", simulator.getOutput().get(0));
		assertFalse(test.getGiocatore().getBorsa().hasAttrezzo("spazzatura"));
		assertTrue(test.getStanzaCorrente().hasAttrezzo("spazzatura"));
		assertEquals(2, test.getStanzaCorrente().getAttrezzo("spazzatura").getPeso());
	}

}
