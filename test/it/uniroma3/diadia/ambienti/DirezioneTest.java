package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;
import static it.uniroma3.diadia.ambienti.Direzione.*;

public class DirezioneTest {

	@Test
	public void testValueOf() {
		assertEquals(nord, Direzione.valueOf("nord"));
	}
	
	@Test
	public void testOpposta() {
		assertEquals(sud, nord.opposta());
	}

}
