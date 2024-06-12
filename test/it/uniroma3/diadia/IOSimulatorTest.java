package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

public class IOSimulatorTest {
	private DiaDia gioco;
	private IOSimulator io;
	private Labirinto labirinto;
	
	@Test
	public void testSimulazioneBilocale() {
		this.labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAdiacenza("salotto", "camera", "nord")
				.getLabirinto();
		
		List<String> in = new ArrayList<>();
		in.add("vai nord");
		
		this.io = new IOSimulator(in);
		
		this.gioco = new DiaDia(labirinto, io);
		
		this.gioco.gioca();
		
		assertEquals("Hai vinto!", this.io.getOutput().get(this.io.getOutput().size()-1));
	}
	
	@Test
	public void testSimulazioneHomeWork3() {
		this.labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("stanza iniziale")
				.addAttrezzo("aicrot", 2)
				.addStanzaMagica("stanza magica", 2)
				.addStanzaBuia("stanza buia", "torcia")
				.addAttrezzo("chiave", 0)
				.addStanzaBloccata("stanza bloccata", "ovest", "chiave")
				.addStanzaVincente("stanza vincente")
				.addAdiacenza("stanza iniziale", "stanza magica", "sud")
				.addAdiacenza("stanza iniziale", "stanza buia", "est")
				.addAdiacenza("stanza iniziale", "stanza bloccata", "nord")
				.addAdiacenza("stanza bloccata", "stanza vincente", "ovest")
				.getLabirinto();
		
		List<String> in = new ArrayList<>();
		in.add("prendi aicrot");
		in.add("vai est");
		in.add("vai ovest");
		in.add("vai nord");
		in.add("vai sud");
		in.add("vai sud");
		in.add("posa aicrot");
		in.add("prendi aicrot");
		in.add("posa aicrot");
		in.add("prendi torcia");
		in.add("vai nord");
		in.add("vai est");
		in.add("posa torcia");
		in.add("prendi chiave");
		in.add("vai ovest");
		in.add("vai nord");
		in.add("posa chiave");
		in.add("vai ovest");
		
		this.io = new IOSimulator(in);
		
		this.gioco = new DiaDia(labirinto, io);
		this.gioco.gioca();
		assertEquals("Hai esaurito i CFU...", this.io.getOutput().get(this.io.getOutput().size()-1));
	}
	
	@Test
	public void testSimulazione1() throws FileNotFoundException, FormatoFileNonValidoException {
		labirinto = Labirinto.newBuilder("resources/labirinto1.txt").getLabirinto();
		List<String> in = new ArrayList<>();
		in.add("prendi pinza");
		in.add("vai sud");
		in.add("prendi martello");
		in.add("vai sud");
		
		io = new IOSimulator(in);
		gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		assertEquals("Hai vinto!", io.getOutput().get(io.getOutput().size()-1));
	}

}
