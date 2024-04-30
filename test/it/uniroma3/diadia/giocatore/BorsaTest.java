package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	private Borsa borsa = new Borsa();
	private Attrezzo pesante = new Attrezzo("pesante", 6);
	private Attrezzo leggero = new Attrezzo("leggero", 0);
	
	@Test
	public void testIsEmpty_borsaVuota() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	public void testIsEmpty_borsaNonVuota() {
		this.borsa.addAttrezzo(this.leggero);
		assertFalse(this.borsa.isEmpty());
	}
	
	@Test 
	public void testIsEmpty_borsaVuota_oggettoRimosso() {
		this.borsa.addAttrezzo(this.leggero);
		this.borsa.removeAttrezzo("leggero");
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	public void testHasAttrezzo_borsaVuota() {
		assertFalse(this.borsa.hasAttrezzo("inesistente"));
	}
	
	@Test
	public void testHasAttrezzo_borsaNonVuota_attrezzoCercatoAssente() {
		this.borsa.addAttrezzo(this.pesante);
		assertFalse(this.borsa.hasAttrezzo("inesistente"));
	}
	
	@Test
	public void testHasAttrezzi_borsaNonVuota_attrezzoCercatoPresente() {
		this.borsa.addAttrezzo(this.pesante);
		assertTrue(this.borsa.hasAttrezzo("pesante"));
	}

	@Test
	public void testGetAttrezzo_borsaVuota() {
		assertNull(this.borsa.getAttrezzo("inesistente"));
	}
	
	@Test
	public void testGetAttrezzo_borsaNonVuota_attrezzoCercatoAssente() {
		assertNull(this.borsa.getAttrezzo("inesistente"));
	}
	
	@Test
	public void testGetAttrezzo_borsaNonVuota_attrezzoCercatoPresente() {
		this.borsa.addAttrezzo(this.pesante);
		assertEquals(this.pesante, this.borsa.getAttrezzo("pesante"));
	}
	
	@Test
	public void testAddAttrezzo_borsaNonPiena() {
		assertTrue(this.borsa.addAttrezzo(this.pesante));
	}
	
	@Test
	public void testAddAttrezzo_borsaPiena_pesoEccessivo() {
		this.borsa.addAttrezzo(this.pesante);
		assertFalse(this.borsa.addAttrezzo(this.pesante));
	}
	
	@Test
	public void testAddAttrezzo_borsaPiena_spazioEsaurito() {
		for(int i=0; i<10; i++)
			this.borsa.addAttrezzo(this.leggero);
		assertFalse(this.borsa.addAttrezzo(this.leggero));
	}
	
	@Test
	public void testRemoveAttrezzo_borsaVuota() {
		assertNull(this.borsa.removeAttrezzo("inesistente"));
	}
	
	@Test
	public void testRemoveAttrezzo_borsaNonVuota_attrezzoDaRimuovereAssente() {
		this.borsa.addAttrezzo(this.pesante);
		assertNull(this.borsa.removeAttrezzo("inesistente"));
	}
	
	@Test
	public void testRemoveAttrezzo_borsaNonVuota_attrezzoDaRimuoverePresente() {
		this.borsa.addAttrezzo(this.pesante);
		assertEquals(this.pesante, this.borsa.removeAttrezzo("pesante"));
	}
	
	@Test
	public void testGetPeso_borsaVuota() {
		assertEquals(0, this.borsa.getPeso());
	}
	
	@Test
	public void testGetPeso_borsaNonVuota() {
		this.borsa.addAttrezzo(this.pesante);
		assertEquals(6, this.borsa.getPeso());
	}
}
