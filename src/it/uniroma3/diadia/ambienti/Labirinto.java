package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

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

	private Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
	}

	private Labirinto(){}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	public static LabirintoBuilder newBuilder(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		String urlFile = new StringBuilder().append(Labirinto.class.getResource(nomeFile)).substring(0);
		return new LabirintoBuilder(urlFile.substring(urlFile.indexOf("/")));
	}

	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}

	public static class LabirintoBuilder{
		private Labirinto labirinto;
		private Stanza ultima;
		private Map<String,Stanza> nome2stanza; 

		public LabirintoBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
			this.labirinto = new Labirinto(labirinto);
			this.ultima = null;
			this.nome2stanza = new HashMap<String,Stanza>();
		}

		public LabirintoBuilder(){
			this.labirinto = new Labirinto();
			this.ultima = null;
			this.nome2stanza = new HashMap<String,Stanza>();
		}


		public LabirintoBuilder addStanzaIniziale(String stanzaIniziale){
			Stanza i = new Stanza(stanzaIniziale);
			this.nome2stanza.put(stanzaIniziale, i);
			this.labirinto.setStanzaIniziale(i);
			this.ultima = i;
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
			Stanza v  = new Stanza(stanzaVincente);
			this.nome2stanza.put(stanzaVincente, v);
			this.labirinto.setStanzaVincente(v);
			this.ultima = v;
			return this;
		}

		public LabirintoBuilder addStanza(String nomeStanza) {
			Stanza s = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, s);
			this.ultima = s;
			return this;
		}

		public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int sogliaMagica) {
			StanzaMagica m = new StanzaMagica(nomeStanzaMagica, sogliaMagica);
			this.nome2stanza.put(nomeStanzaMagica, m);
			this.ultima = m;
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata, String direzione, String chiave) {
			StanzaBloccata b = new StanzaBloccata(nomeStanzaBloccata, direzione, chiave);
			this.nome2stanza.put(nomeStanzaBloccata, b);
			this.ultima = b;
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nomeStanzaBuia, String illuminazione) {
			StanzaBuia d = new StanzaBuia(nomeStanzaBuia, illuminazione);
			this.nome2stanza.put(nomeStanzaBuia, d);
			this.ultima = d;
			return this;
		}

		public LabirintoBuilder addCane(String nome, String nomeCibo, String nomeRegalo) {
			Attrezzo cibo = new Attrezzo(nomeCibo, 1);
			Attrezzo regalo = new Attrezzo(nomeRegalo, 1);
			AbstractPersonaggio cane = new Cane(nome, "Woof!", cibo, regalo);
			this.ultima.setPersonaggio(cane);
			return this;
		}

		public LabirintoBuilder addMago(String nome, String nomeRegalo, int pesoRegalo) {
			Attrezzo regalo = new Attrezzo(nomeRegalo, pesoRegalo);
			AbstractPersonaggio mago = new Mago(nome, "Salve a te, giovane avventuriero", regalo);
			this.ultima.setPersonaggio(mago);
			return this;
		}

		public LabirintoBuilder addStrega(String nome) {
			AbstractPersonaggio strega = new Strega(nome, "Sei una persona educata vedo");
			this.ultima.setPersonaggio(strega);
			return this;
		}

		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int pesoAttrezzo) {
			Attrezzo a = new Attrezzo(nomeAttrezzo, pesoAttrezzo);
			this.ultima.addAttrezzo(a);
			return this;
		}

		public LabirintoBuilder addAdiacenza(String stanzaA, String stanzaB, String direzione) {
			if(direzione.equals("nord") || direzione.equals("sud") || direzione.equals("ovest") || direzione.equals("est")) {
				this.nome2stanza.get(stanzaA).impostaStanzaAdiacente(direzione, this.nome2stanza.get(stanzaB));
				this.nome2stanza.get(stanzaB).impostaStanzaAdiacente(Direzione.valueOf(direzione).opposta().verso(), this.nome2stanza.get(stanzaA));
			}
			return this;
		}

		public void setUltima(String nomeStanza) {
			this.ultima = this.nome2stanza.get(nomeStanza);
		}

		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		public Map<String, Stanza> getListaStanze() {
			return this.nome2stanza; 
		}
	}
}
