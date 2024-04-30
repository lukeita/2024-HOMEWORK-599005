package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Test;
import it.uniroma3.diadia.*;

public class FabbricaDiComandiFisarmonicaTest {
	IO io = new IOConsole();
	private FabbricaDiComandiFisarmonica test = new FabbricaDiComandiFisarmonica(this.io);

	@Test
	public void testComandoNonValido() {
		assertEquals("", test.costruisciComando("").getNome());
	}
	
	@Test
	public void testComandoVai() {
		assertEquals("vai", test.costruisciComando("vai").getNome());
	}
	
	@Test
	public void testComandoPrendi() {
		assertEquals("prendi", test.costruisciComando("prendi").getNome());
	}
	
	@Test
	public void testComandoPosa() {
		assertEquals("posa", test.costruisciComando("posa").getNome());
	}
	
	@Test
	public void testComandoAiuto() {
		assertEquals("aiuto", test.costruisciComando("aiuto").getNome());
	}
	
	@Test
	public void testComandoFine() {
		assertEquals("fine", test.costruisciComando("fine").getNome());
	}
	
	@Test
	public void testComandoGuarda() {
		assertEquals("guarda", test.costruisciComando("guarda").getNome());
	}

}
