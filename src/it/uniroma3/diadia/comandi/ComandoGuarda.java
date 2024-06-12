package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		this.getIo().mostraMessaggio(partita.getStanzaCorrente().getDescrizione() + 
						   "\nHai ancora " + partita.getGiocatore().getCfu() + " cfu\n" +
						   partita.getGiocatore().getBorsa().toString());
	}
	
}
