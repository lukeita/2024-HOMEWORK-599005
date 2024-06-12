package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.StringReader;

import org.junit.Test;

public class CaricatoreLabirintoTest {
	private String nomeStanzaIniziale = "entrata";
	
	private final String monolocale = 
			"Stanze: entrata\n"+
			"Inizio: entrata\n"+
			"Vincente: entrata\n"+
			"Bloccata:\n"+
			"Buia:\n"+
			"Magica:\n"+
			"Cane:\n"+
			"Mago:\n"+
			"Strega:\n"+
			"Attrezzi: spada 1 entrata\n"+
			"Uscite:\n";
	
	private final String bilocale = 
			"Stanze: entrata, uscita\n"+
			"Inizio: entrata\n"+
			"Vincente: uscita\n"+
			"Bloccata:\n"+
			"Buia:\n"+
			"Magica:\n"+
			"Cane:\n"+
			"Mago:\n"+
			"Strega:\n"+
			"Attrezzi: spada 1 entrata\n"+
			"Uscite: entrata nord uscita\n";

	@Test
	public void testMonolocale() throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c = new CaricatoreLabirinto(new StringReader(monolocale));
		c.carica();
		assertEquals(nomeStanzaIniziale, c.getStanzaIniziale().getNome());
		assertTrue(c.getStanzaIniziale().hasAttrezzo("spada"));
		assertEquals(nomeStanzaIniziale, c.getStanzaVincente().getNome());		
	}

	@Test
	public void testBilocale() throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c = new CaricatoreLabirinto(new StringReader(bilocale));
		c.carica();
		assertEquals(c.getStanzaVincente(), c.getStanzaIniziale().getStanzaAdiacente("nord"));
	}
}
