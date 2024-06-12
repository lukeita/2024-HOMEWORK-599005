package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoPosa extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		if(partita.getGiocatore().getBorsa().hasAttrezzo(this.getParametro()))
			partita.getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro()));
		else
			this.getIo().mostraMessaggio("Attrezzo non trovato");
	}
}
