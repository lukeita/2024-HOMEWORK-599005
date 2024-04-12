package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che ha il compito di modellare e gestire l'inventario del giocatore.
 * La borsa può avere al suo interno soltanto attrezzi
 * 
 * @author 	docente di POO
 * @see 	Attrezzo
 * @version	base
 */
public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	
	/**
	 * Crea la borsa con una sopportazione del peso di default
	 */
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	/**
	 * Crea la borsa che ha una capienza di massimo 10 oggetti. Può sopportare un peso massimo 
	 * equivalente a pesoMax
	 * @param pesoMax	peso massimo che la borsa può sopportare
	 */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}
	
	/**
	 * aggiungi un attrezzo all'interno della borsa
	 * @param attrezzo	attrezzo da aggiungere all'interno della borsa
	 * @return vero se l'attrezzo è stato aggiunto
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}
	
	/**
	 * Restituisci il peso massimo che può sopportare la borsa
	 * @return peso massimo che può sopportare la borsa
	 */
	public int getPesoMax() {
		return pesoMax;
	}
	
	/**
	 * Restituisci l'attrezzo del nome che ho inserito
	 * @param nomeAttrezzo	nome dell'attrezzo che si deridera cercare
	 * @return l'oggetto dell'attrezzo cercato, altrimenti null
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i] != null && this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];
		return a;
	}
	
	/**
	 * Restituisci il peso totale degli oggetti all'interno della borsa
	 * @return peso totale degli oggetti all'interno della borsa
	 */
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.attrezzi.length && this.numeroAttrezzi != 0; i++)
			if(this.attrezzi[i] != null)
				peso += this.attrezzi[i].getPeso();
		return peso;
	}
	
	/**
	 * verifica se la borsa è vuota 
	 * @return vero se la brosa è vuota
	 */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	/**
	 * Verifica se l'attrezzo cercato è presente nella borsa
	 * @param nomeAttrezzo	attrezzo da cercare all'interno della borsa
	 * @return vero se l'attrezzo è presente all'interno della borsa
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	/**
	 * Restituisci l'attrezzo cercato e rimuovilo dalla borsa
	 * @param nomeAttrezzo	nome dell'attrezzo da rimuovere dalla borsa
	 * @return attrezzo rimosso, altrimenti null
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if(this.hasAttrezzo(nomeAttrezzo))
			for(int i=0; i<this.attrezzi.length; i++)
				if(this.attrezzi[i] != null && this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
					a = this.attrezzi[i];
					this.attrezzi[i] = null;
				}
		return a;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}