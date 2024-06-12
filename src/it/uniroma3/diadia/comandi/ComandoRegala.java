package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoRegala extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		if(!partita.getStanzaCorrente().hasPersonaggio())
			this.getIo().mostraMessaggio("Qui non ci sta nessuno...");
		
		if(partita.getGiocatore().getBorsa().hasAttrezzo(this.getParametro()))
			this.getIo().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().riceviRegalo(partita.getGiocatore().getBorsa().removeAttrezzo(getParametro()), partita));
		else
			this.getIo().mostraMessaggio("Non ho questo oggetto");
	}	

}
