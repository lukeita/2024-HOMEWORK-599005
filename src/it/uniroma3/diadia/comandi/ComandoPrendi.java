package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoPrendi extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().hasAttrezzo(this.getParametro())){
			partita.getGiocatore().getBorsa().addAttrezzo(partita.getStanzaCorrente().getAttrezzo(this.getParametro()));
			partita.getStanzaCorrente().removeAttrezzo(this.getParametro());
		}
		else
			this.getIo().mostraMessaggio("Attrezzo non trovato");
	}

}
