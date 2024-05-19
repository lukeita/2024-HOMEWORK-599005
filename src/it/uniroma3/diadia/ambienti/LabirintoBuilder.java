package it.uniroma3.diadia.ambienti;


import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder{
	private Labirinto labirinto;
	private Stanza ultima;
	private Map<String,Stanza> stanza; 
	
	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.ultima = null;
		this.stanza = new HashMap<String,Stanza>();
	}
	
	public LabirintoBuilder addStanzaIniziale(String stanzaIniziale){
		Stanza i = new Stanza(stanzaIniziale);
		this.stanza.put(stanzaIniziale, i);
		this.labirinto.setStanzaIniziale(i);
		this.ultima = i;
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
		Stanza v  = new Stanza(stanzaVincente);
		this.stanza.put(stanzaVincente, v);
		this.labirinto.setStanzaVincente(v);
		this.ultima = v;
		return this;
	}
	
	public LabirintoBuilder addStanza(String nomeStanza) {
		Stanza s = new Stanza(nomeStanza);
		this.stanza.put(nomeStanza, s);
		this.ultima = s;
		return this;
	}

	public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int sogliaMagica) {
		StanzaMagica m = new StanzaMagica(nomeStanzaMagica, sogliaMagica);
		this.stanza.put(nomeStanzaMagica, m);
		this.ultima = m;
		return this;
	}

	public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata, String direzione, String chiave) {
		StanzaBloccata b = new StanzaBloccata(nomeStanzaBloccata, direzione, chiave);
		this.stanza.put(nomeStanzaBloccata, b);
		this.ultima = b;
		return this;
	}

	public LabirintoBuilder addStanzaBuia(String nomeStanzaBuia, String illuminazione) {
		StanzaBuia d = new StanzaBuia(nomeStanzaBuia, illuminazione);
		this.stanza.put(nomeStanzaBuia, d);
		this.ultima = d;
		return this;
	}

	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int pesoAttrezzo) {
		Attrezzo a = new Attrezzo(nomeAttrezzo, pesoAttrezzo);
		this.ultima.addAttrezzo(a);
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String stanzaA, String stanzaB, String direzione) {
		if(this.labirinto.getDirezioni().contains(direzione)) {
			this.stanza.get(stanzaA).impostaStanzaAdiacente(direzione, this.stanza.get(stanzaB));
			if((this.labirinto.getDirezioni().indexOf(direzione)+1) % 2 == 0)
				this.stanza.get(stanzaB).impostaStanzaAdiacente(this.labirinto.getDirezioni().get(this.labirinto.getDirezioni().indexOf(direzione)-1), this.stanza.get(stanzaA));
			else
				this.stanza.get(stanzaB).impostaStanzaAdiacente(this.labirinto.getDirezioni().get(this.labirinto.getDirezioni().indexOf(direzione)+1), this.stanza.get(stanzaA));
		}
		return this;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	public Map<String, Stanza> getListaStanze() {
		return this.stanza; 
	}
}
