package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.List;

//import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che modella e gestisce un labrinto che sarà la mappa di gioco.
 * Il labirinto è un insieme di più stanze collegate, con una stanza di entrata e una di uscita
 * 
 * @author 	Luca Italiano
 * @see		Stanza
 * @version	0.1
 */
public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private List<String> direzioni;
	
	public Labirinto() {
		this.direzioni = new ArrayList<>();
		direzioni.add("nord");
		direzioni.add("sud");
		direzioni.add("ovest");
		direzioni.add("est");
	}
	
	/*private void init() {

    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
    	
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

        this.stanzaIniziale = atrio;  
		this.stanzaVincente = biblioteca;
	}*/
	
	/**
	 * Restituisce l'entrata del labirinto
	 * @return entrata labirinto
	 */
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	
	/**
	 * Restituisce l'uscita del labirinto
	 * @return uscita labirinto
	 */
	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
	
	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}
	
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	
	public List<String> getDirezioni() {
		return direzioni;
	}
}
