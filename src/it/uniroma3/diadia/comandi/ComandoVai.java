package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		if(this.getParametro() == null) {
			this.getIo().mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			return;
		}
		
		Stanza prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(this.getParametro());
		if(prossimaStanza == null) {
			this.getIo().mostraMessaggio("Direzione inesistente");
			return;
		}
		
		partita.setStanzaCorrente(prossimaStanza);
		this.getIo().mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}
}