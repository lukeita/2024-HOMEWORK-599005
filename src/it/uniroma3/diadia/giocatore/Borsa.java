package it.uniroma3.diadia.giocatore;

import java.util.*;

import it.uniroma3.diadia.Configuratore;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerNome;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;

/**
 * Classe che ha il compito di modellare e gestire l'inventario del giocatore.
 * La borsa può avere al suo interno soltanto attrezzi
 * 
 * @author 	docente di POO
 * @see 	Attrezzo
 * @version	base
 */
public class Borsa {
	final static private int DEFAULT_PESO_MAX_BORSA = Configuratore.getPesoMaxBorsa(); 
	private int pesoMax;
	private Map<String,Attrezzo> attrezzi;

	/**
	 * Crea la borsa con una sopportazione del peso di default
	 */
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int peso) {
		this.pesoMax = peso;
		this.attrezzi = new TreeMap<String,Attrezzo>();
	}

	/**
	 * verifica se la borsa è vuota 
	 * @return vero se la brosa è vuota
	 */
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}


	/**
	 * Restituisci il peso totale degli oggetti all'interno della borsa
	 * @return peso totale degli oggetti all'interno della borsa
	 */
	public int getPeso() {
		int peso = 0;
		for(Attrezzo a: this.attrezzi.values())
			peso += a.getPeso();
		return peso;
	}

	public int getPesoMax() {
		return this.pesoMax;
	}

	/**
	 * aggiungi un attrezzo all'interno della borsa
	 * @param attrezzo	attrezzo da aggiungere all'interno della borsa
	 * @return vero se l'attrezzo è stato aggiunto
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if((this.getPeso() + attrezzo.getPeso()) > this.getPesoMax())
			return false;
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}

	/**
	 * Verifica se l'attrezzo cercato è presente nella borsa
	 * @param nomeAttrezzo	attrezzo da cercare all'interno della borsa
	 * @return vero se l'attrezzo è presente all'interno della borsa
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		if(this.isEmpty())
			return false;
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisci l'attrezzo del nome che ho inserito
	 * @param nomeAttrezzo	nome dell'attrezzo che si deridera cercare
	 * @return l'oggetto dell'attrezzo cercato, altrimenti null
	 */
	public Attrezzo getAttrezzo(String aCercato) {
		return this.attrezzi.get(aCercato);
	}

	/**
	 * Restituisci l'attrezzo cercato e rimuovilo dalla borsa
	 * @param nomeAttrezzo	nome dell'attrezzo da rimuovere dalla borsa
	 * @return attrezzo rimosso, altrimenti null
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}

	List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> ordinata = new ArrayList<>(this.attrezzi.values());
		Collections.sort(ordinata, new ComparatoreAttrezziPerPeso());
		return ordinata;
	}

	SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> ordinata = new TreeSet<>(new ComparatoreAttrezziPerNome());
		ordinata.addAll(this.attrezzi.values());
		return ordinata;
	}

	SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> ordinata = new TreeSet<>(new ComparatoreAttrezziPerPeso());
		ordinata.addAll(this.attrezzi.values());
		return ordinata;
	}

	Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> gruppo = new HashMap<>();
		for(Attrezzo a : this.attrezzi.values()) {
			if(gruppo.containsKey(a.getPeso())) 
				gruppo.get(a.getPeso()).add(a);
			else {
				Set<Attrezzo> set = new TreeSet<>(new ComparatoreAttrezziPerNome());
				set.add(a);
				gruppo.put(a.getPeso(), set);
			}
		}
		return gruppo;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Contenuto borsa ("+this.getPeso()+"kg/" + this.getPesoMax()+"kg): \n");
		s.append(this.getContenutoRaggruppatoPerPeso().values().toString());
		return s.toString();
	}
}