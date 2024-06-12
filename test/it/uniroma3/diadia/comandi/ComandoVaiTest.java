package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

public class ComandoVaiTest {
	private AbstractComando vai = new ComandoVai();
	private Labirinto monolocale = Labirinto.newBuilder()
			.addStanzaIniziale("salotto")
			.addStanzaVincente("camera")
			.addAttrezzo("letto",10)
			.addAdiacenza("salotto", "camera", "nord")
			.getLabirinto(); 
	private Partita partita = new Partita(monolocale);
	
	@Before
	public void setUp() {
		vai.setIo(new IOConsole(new Scanner(System.in)));
	}

	@Test
	public void testVai_direzioneInesistente() {
		vai.setParametro("sud");
		vai.esegui(partita);
		assertSame(monolocale.getStanzaIniziale(), partita.getStanzaCorrente());
	}
	
	@Test
	public void testVai_direzioneEsistente() {
		vai.setParametro("nord");
		vai.esegui(partita);
		assertSame(monolocale.getStanzaVincente(), partita.getStanzaCorrente());
	}
	
	@Test
	public void testVai_tornaIndietro() {
		vai.setParametro("nord");
		vai.esegui(partita);
		vai.setParametro("sud");
		vai.esegui(partita);
		assertSame(monolocale.getStanzaIniziale(), partita.getStanzaCorrente());
	}

}
