package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.ComandoInteragisci;
import it.uniroma3.diadia.comandi.ComandoRegala;
import it.uniroma3.diadia.comandi.ComandoSaluta;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

public class StregaTest {

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
				.addStrega("Baba")
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
	public void testSalutaStrega() {
		comando = new ComandoSaluta();
		comando.setIo(simulator);
		comando.esegui(test);	
		assertEquals("Ciao, io sono Baba.Sei una persona educata vedo", simulator.getOutput().get(0));
		comando.esegui(test);
		assertEquals("Ciao, io sono Baba.Ci siamo gia' presentati!", simulator.getOutput().get(1));
	}
	
	@Test
	public void testInteragisciStrega_senzaSalutare() {
		comando = new ComandoInteragisci();
		comando.setIo(simulator);
		comando.esegui(test);
		assertEquals("Un maleducato che non saluta merita una punizione", simulator.getOutput().get(0));
		assertEquals(new Stanza("uscita"), test.getStanzaCorrente());
	}
	
	@Test
	public void testInteragisciStrega_salutando() {
		AbstractComando saluta = new ComandoSaluta();
		saluta.setIo(simulator);
		saluta.esegui(test);
		comando = new ComandoInteragisci();
		comando.setIo(simulator);
		comando.esegui(test);
		assertEquals("Ti porto dove puoi appesantire quel borsone che hai", simulator.getOutput().get(1));
		assertEquals(new Stanza("uscita"), test.getStanzaCorrente());
	}
	
	@Test
	public void testRegalaStrega() {
		comando = new ComandoRegala();
		comando.setIo(simulator);
		comando.setParametro("spazzatura");
		comando.esegui(test);
		assertEquals("AHAHAHAHAHAHAHAHAAH!", simulator.getOutput().get(0));
		assertFalse(test.getGiocatore().getBorsa().hasAttrezzo("spazzatura"));
		assertFalse(test.getStanzaCorrente().hasAttrezzo("spazzatura"));
	}

}
