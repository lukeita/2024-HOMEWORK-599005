package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerNome;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;

public class BorsaTest {
	private Borsa borsaDefault = new Borsa(10);
	private Borsa borsaMax = new Borsa(30);
	private Attrezzo piombo = new Attrezzo("piombo", 10);
	private Attrezzo piuma = new Attrezzo("piuma", 1);
	private Attrezzo ps = new Attrezzo("ps", 5);
	private Attrezzo libro = new Attrezzo("libro", 5);
	private List<Attrezzo> lista = new LinkedList<>();
	
	@Before
	public void setUp() {
		this.lista.add(libro);
		this.lista.add(piombo);
		this.lista.add(piuma);
		this.lista.add(ps);
		for(Attrezzo a : this.lista)
			borsaMax.addAttrezzo(a);
	}
	
	@Test
	public void testIsEmpty_borsaVuota() {
		assertTrue(this.borsaDefault.isEmpty());
	}
	
	@Test
	public void testIsEmpty_borsaNonVuota() {
		this.borsaDefault.addAttrezzo(this.piuma);
		assertFalse(this.borsaDefault.isEmpty());
	}
	
	@Test 
	public void testIsEmpty_borsaVuota_oggettoRimosso() {
		this.borsaDefault.addAttrezzo(this.piuma);
		this.borsaDefault.removeAttrezzo("piuma");
		assertTrue(this.borsaDefault.isEmpty());
	}
	
	@Test
	public void testGetPeso_borsaVuota() {
		assertEquals(0, this.borsaDefault.getPeso());
	}

	@Test
	public void testGetPeso_borsaNonVuota() {
		this.borsaDefault.addAttrezzo(this.piombo);
		assertEquals(10, this.borsaDefault.getPeso());
	}

	@Test
	public void testAddAttrezzo_borsaVuota() {
		assertTrue(this.borsaDefault.addAttrezzo(this.piombo));
	}
	
	@Test
	public void testAddAttrezzo_borsaNonPiena() {
		this.borsaDefault.addAttrezzo(piuma);
		assertTrue(this.borsaDefault.addAttrezzo(this.piuma));
	}
	
	@Test
	public void testAddAttrezzo_borsaPiena() {
		this.borsaDefault.addAttrezzo(piombo);
		assertFalse(this.borsaDefault.addAttrezzo(this.piuma));
	}

	@Test
	public void testHasAttrezzo_borsaVuota() {
		assertFalse(this.borsaDefault.hasAttrezzo("inesistente"));
	}
	
	@Test
	public void testHasAttrezzo_borsaNonVuota_attrezzoCercatoAssente() {
		this.borsaDefault.addAttrezzo(this.piombo);
		assertFalse(this.borsaDefault.hasAttrezzo("inesistente"));
	}
	
	@Test
	public void testHasAttrezzo_borsaNonVuota_attrezzoCercatoPresente() {
		this.borsaDefault.addAttrezzo(this.piombo);
		assertTrue(this.borsaDefault.hasAttrezzo("piombo"));
	}

	@Test
	public void testGetAttrezzo_borsaVuota() {
		assertNull(this.borsaDefault.getAttrezzo("inesistente"));
	}
	
	@Test
	public void testGetAttrezzo_borsaNonVuota_attrezzoCercatoAssente() {
		assertNull(this.borsaDefault.getAttrezzo("inesistente"));
	}
	
	@Test
	public void testGetAttrezzo_borsaNonVuota_attrezzoCercatoPresente() {
		this.borsaDefault.addAttrezzo(this.piombo);
		assertEquals(this.piombo, this.borsaDefault.getAttrezzo("piombo"));
	}
	
	@Test
	public void testRemoveAttrezzo_borsaVuota() {
		assertNull(this.borsaDefault.removeAttrezzo("inesistente"));
	}
	
	@Test
	public void testRemoveAttrezzo_borsaNonVuota_attrezzoDaRimuovereAssente() {
		this.borsaDefault.addAttrezzo(this.piombo);
		assertNull(this.borsaDefault.removeAttrezzo("inesistente"));
	}
	
	@Test
	public void testRemoveAttrezzo_borsaNonVuota_attrezzoDaRimuoverePresente() {
		this.borsaDefault.addAttrezzo(this.piombo);
		assertEquals(this.piombo, this.borsaDefault.removeAttrezzo("piombo"));
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso() {		
		ComparatoreAttrezziPerPeso cmp = new ComparatoreAttrezziPerPeso();
		Collections.sort(lista, cmp);
		assertEquals("piuma", lista.get(0).getNome());
		assertEquals("libro", lista.get(1).getNome());
		assertEquals("ps", lista.get(2).getNome());
		assertEquals("piombo", lista.get(3).getNome());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> ordinata = new TreeSet<Attrezzo>(new ComparatoreAttrezziPerNome());
		ordinata.addAll(lista);
		Iterator<Attrezzo> it = ordinata.iterator();
		assertEquals("libro", it.next().getNome());
		assertEquals("piombo", it.next().getNome());
		assertEquals("piuma", it.next().getNome());
		assertEquals("ps", it.next().getNome());
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> ordinata = new TreeSet<Attrezzo>(new ComparatoreAttrezziPerPeso());
		ordinata.addAll(lista);
		Iterator<Attrezzo> it = ordinata.iterator();
		assertEquals("piuma", it.next().getNome());
		assertEquals("libro", it.next().getNome());
		assertEquals("ps", it.next().getNome());
		assertEquals("piombo", it.next().getNome());
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso() {
		Map<Integer,Set<Attrezzo>> gruppo = this.borsaMax.getContenutoRaggruppatoPerPeso();
		assertEquals("piuma", gruppo.get(this.piuma.getPeso()).iterator().next().getNome());
		Iterator<Attrezzo> it = gruppo.get(libro.getPeso()).iterator();
		assertEquals("libro", it.next().getNome());
		assertEquals("ps", it.next().getNome());
		assertEquals("piombo", gruppo.get(this.piombo.getPeso()).iterator().next().getNome());
	}
}
