package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().hasPersonaggio())
			this.getIo().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
		else
			this.getIo().mostraMessaggio("Chi dovrei salutare? Qui non c'è nessuno...");
	}

}
