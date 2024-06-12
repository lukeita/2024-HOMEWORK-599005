/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Stanza {

	private String nome;
	private Map<String,Attrezzo> nome2attrezzo; 
	private Map<Direzione,Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.nome2attrezzo = new TreeMap<>();
		this.stanzeAdiacenti = new HashMap<>();
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}
	
	public boolean hasPersonaggio() {
		if(this.personaggio == null)
			return false;
		return true;
	}

	public boolean isEmpty() {
		return this.nome2attrezzo.isEmpty();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		if(this.isEmpty())
			return false;
		return this.nome2attrezzo.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.nome2attrezzo.get(nomeAttrezzo);
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {
		return new ArrayList<>(this.nome2attrezzo.values());
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.nome2attrezzo.put(attrezzo.getNome(), attrezzo) == null)
			return true;
		return false;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(String nomeAttrezzo) {
		if(this.nome2attrezzo.remove(nomeAttrezzo) == null)
			return false;
		return true;
	}

	public List<Direzione> getDirezioni() {
		return new ArrayList<>(this.stanzeAdiacenti.keySet());
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanzaAdiacente stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanzaAdiacente) {
		try {
			this.stanzeAdiacenti.put(Direzione.valueOf(direzione), stanzaAdiacente);
		}catch(IllegalArgumentException e) {
			return;
		}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		try {
			return this.stanzeAdiacenti.get(Direzione.valueOf(direzione));
		}catch(IllegalArgumentException e) {
			return null;
		}
	}

	public Map<Direzione,Stanza> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: " + this.getDirezioni().toString());
		risultato.append("\nAttrezzi nella stanza: " + this.nome2attrezzo.values().toString());
		return risultato.toString();
	}

	@Override
	public boolean equals(Object o) {
		Stanza that = (Stanza)o;
		return this.getNome().equals(that.getNome());
	}

	@Override 
	public int hashCode() {
		return this.getNome().hashCode() + this.getClass().hashCode();
	}
}